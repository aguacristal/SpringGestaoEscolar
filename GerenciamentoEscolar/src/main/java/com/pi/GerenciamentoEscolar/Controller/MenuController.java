package com.pi.GerenciamentoEscolar.Controller;

import com.pi.GerenciamentoEscolar.Model.Aluno;
import com.pi.GerenciamentoEscolar.Model.Aula;
import com.pi.GerenciamentoEscolar.Model.Materia;
import com.pi.GerenciamentoEscolar.Model.Nota;
import com.pi.GerenciamentoEscolar.Model.Professor;
import com.pi.GerenciamentoEscolar.Model.Recuperacao;
import com.pi.GerenciamentoEscolar.Model.Responsavel;
import com.pi.GerenciamentoEscolar.Model.Usuario;
import com.pi.GerenciamentoEscolar.Repository.AlunoRepository;
import com.pi.GerenciamentoEscolar.Repository.AulaRepository;
import com.pi.GerenciamentoEscolar.Repository.MateriaRepository;
import com.pi.GerenciamentoEscolar.Repository.NotaRepository;
import com.pi.GerenciamentoEscolar.Repository.ProfessorRepository;
import com.pi.GerenciamentoEscolar.Repository.RecuperacaoRepository;
import com.pi.GerenciamentoEscolar.Repository.ResponsavelRepository;
import com.pi.GerenciamentoEscolar.Repository.UsuarioRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class MenuController {

    @Autowired
private ResponsavelRepository responsavelRepository;

@Autowired
private UsuarioRepository usuarioRepository;

@Autowired
private AulaRepository aulaRepository;

@Autowired
private MateriaRepository materiaRepository;

@Autowired
private NotaRepository notaRepository;

@Autowired
private AlunoRepository alunoRepository;

@Autowired
private RecuperacaoRepository recuperacaoRepository;

@Autowired
private ProfessorRepository professorRepository;


    @GetMapping("/menu")
    public String exibirMenu() {
        return "Menu"; // templates/Menu.html
    }

    @GetMapping("/aluno")
public String exibirFormularioAluno(Model model) {
   model.addAttribute("aluno", new Aluno()); 
   List<Usuario> usuariosAluno = usuarioRepository.findByTipo("ALUNO");
   model.addAttribute("usuariosAluno", usuariosAluno); 
    return "CadAluno";
}

    @PostMapping("/aluno/cadastrar")
public String cadastrarAluno(@ModelAttribute Aluno aluno, RedirectAttributes redirectAttributes) {
    Usuario usuario = aluno.getUsuario();
    
    if (usuario == null || usuario.getId() == null) {
        redirectAttributes.addFlashAttribute("erro", "Usuário inválido");
        return "redirect:/aluno";
    }
    
    Usuario usuarioBanco = usuarioRepository.findById(aluno.getUsuario().getId()).orElse(null);
    if (usuarioBanco == null || !"Aluno".equalsIgnoreCase(usuarioBanco.getTipo())) {
        redirectAttributes.addFlashAttribute("erro", "usuario_invalido");
        return "redirect:/aluno";
    }
    aluno.setUsuario(usuarioBanco);
    alunoRepository.save(aluno);

    redirectAttributes.addAttribute("sucesso", true);
    return "redirect:/aluno";
}
     @GetMapping("/responsavel/cadastro")
    public String mostrarFormulario(Model model) {
        model.addAttribute("responsavel", new Responsavel());
        return "CadResponsavel";
    }

    @PostMapping("/responsavel/cadastrar")
    public String cadastrarResponsavel(Responsavel responsavel) {
        responsavelRepository.save(responsavel);
        return "redirect:/aluno/cadastro"; // ou página de confirmação
    }
    @GetMapping("/professor")
    public String mostrarMenuProfessor() {
        return "menuprof"; 
}
    @GetMapping("/usuario/cadastro")
    public String exibirFormulario() {
        return "CadUsuario";  // templates/cadastro-usuario.html
    }

    @PostMapping("/usuario/cadastrar")
    public String cadastrarUsuario(
            @RequestParam String data,
            @RequestParam String nome,
            @RequestParam String email,
            @RequestParam String senha,
            @RequestParam String tipo,
            RedirectAttributes redirectAttributes
    ) {
        try {
            // Verifica se email já existe
            if (usuarioRepository.existsByEmail(email)) {
                redirectAttributes.addAttribute("erro", "email");
                return "redirect:/usuario/cadastro";
            }

            Usuario usuario = new Usuario(
                    LocalDate.parse(data),
                    nome,
                    email,
                    senha,
                    tipo
            );

            usuarioRepository.save(usuario);

            redirectAttributes.addAttribute("sucesso", true);
            return "redirect:/usuario/cadastro";

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addAttribute("erro", "geral");
            return "redirect:/usuario/cadastro";
        }
    }
     @GetMapping("/usuario/pesquisa")
    public String exibirPaginaPesquisa() {
        return "PesUsu";
    }

    // Processa a pesquisa
    @GetMapping("/usuario/buscar")
    public String buscarUsuarios(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String tipo,
            Model model) {

        List<Usuario> resultados;

        if ((nome == null || nome.isEmpty()) && (tipo == null || tipo.isEmpty())) {
            resultados = usuarioRepository.findAll();
        } else if (nome != null && !nome.isEmpty() && tipo != null && !tipo.isEmpty()) {
            resultados = usuarioRepository.findByNomeContainingIgnoreCaseAndTipo(nome, tipo);
        } else if (nome != null && !nome.isEmpty()) {
            resultados = usuarioRepository.findByNomeContainingIgnoreCase(nome);
        } else {
            resultados = usuarioRepository.findByTipo(tipo);
        }

        model.addAttribute("resultados", resultados);
        return "PesUsu";
    }
    @GetMapping("/aulas/cadastrar")
    public String exibirFormulario(Model model) {
        model.addAttribute("aula", new Aula());
        return "CadAula"; // nome do arquivo HTML
    }

    @PostMapping("/aulas/salvar")
    public String salvar(Aula aula) {
        aulaRepository.save(aula);
        return "redirect:/professor"; // redireciona após salvar
    }
     @GetMapping("/materia/cadastro")
    public String mostrarFormulario(Model model,
                                   @RequestParam(required = false) String sucesso,
                                   @RequestParam(required = false) String erro) {
        if (!model.containsAttribute("materia")) {
            model.addAttribute("materia", new Materia());
        }
        if (sucesso != null) model.addAttribute("mensagemSucesso", "Matéria cadastrada com sucesso!");
        if (erro != null) model.addAttribute("mensagemErro", "Erro: matéria já cadastrada ou dados inválidos.");
        return "CadMateria"; // templates/CadMateria.html
    }

    // Salva a matéria
    @PostMapping("/materia/cadastrar")
    public String cadastrarMateria(@ModelAttribute Materia materia, RedirectAttributes redirectAttributes) {
        try {
            String nome = (materia.getNome() == null) ? "" : materia.getNome().trim();
            if (nome.isEmpty()) {
                redirectAttributes.addAttribute("erro", "true");
                return "redirect:/materia/cadastro";
            }

            if (materiaRepository.existsByNome(nome)) {
                redirectAttributes.addAttribute("erro", "true");
                return "redirect:/materia/cadastro";
            }

            materia.setNome(nome);
            materiaRepository.save(materia);
            redirectAttributes.addAttribute("sucesso", "true");
            return "redirect:/materia/cadastro";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addAttribute("erro", "true");
            return "redirect:/materia/cadastro";
        }
    }
 @GetMapping("/nota/cadastro")
public String mostrarFormularioNota(Model model) {
    model.addAttribute("nota", new Nota());
    // Assume que 'usuarioRepository' é injetado.
    // Busque todos os USUARIOS que são do TIPO "Aluno"
    model.addAttribute("alunos", alunoRepository.findByUsuario_Tipo("ALUNO")); 
    // ou "Aluno", dependendo de como você salvou o tipo
    
    return "CadNota";
}
// NO MenuController.java

@PostMapping("/nota/cadastrar")
public String cadastrarNota(
    @RequestParam("aluno") Long alunoId,
    @RequestParam("nota") String notaStr,
    String materia, 
    RedirectAttributes redirectAttributes
) {
    try {
        Aluno aluno = alunoRepository.findById(alunoId).orElse(null);
        
        // 1. Verificação de Aluno e Nota (ANTES de processar)
        if (aluno == null || notaStr == null || notaStr.isEmpty()) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro: Aluno ou nota inválidos.");
            return "redirect:/nota/cadastro";
        }

        Double valor;
        try {
            valor = Double.parseDouble(notaStr.replace(",", "."));
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro: nota inválida.");
            return "redirect:/nota/cadastro";
        }

        // 2. Cria e Salva a Nota
        Nota nota = new Nota();
        nota.setAluno(aluno); // Aqui você associa o objeto Aluno completo
        nota.setNota(valor);
        nota.setData(LocalDate.now());
        nota.setMateria(materia);
        
        notaRepository.save(nota);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Nota cadastrada com sucesso!");
        return "redirect:/nota/cadastro";
    } catch (Exception e) {

        e.printStackTrace();

        redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao cadastrar nota.");

        return "redirect:/nota/cadastro";

    }
        
    
}

    // Página de pesquisa de notas
 @GetMapping("/nota/pesquisar")
public String pesquisarNotas(@RequestParam(required = false) String aluno, Model model) {
    List<Nota> notas;
    if (aluno != null && !aluno.isEmpty()) {
        // Mude para o novo método
        notas = notaRepository.findByAluno_Usuario_NomeContainingIgnoreCase(aluno);
    } else {
        notas = notaRepository.findAll();
    }
    model.addAttribute("notas", notas);
    return "PesNota";
}
@GetMapping("/recuperacao/nova")
    public String novaRecuperacao(Model model) {
        model.addAttribute("recuperacao", new Recuperacao());
    // Assume que 'usuarioRepository' é injetado.
    // Busque todos os USUARIOS que são do TIPO "Aluno"
    model.addAttribute("alunos", alunoRepository.findByUsuario_Tipo("ALUNO")); 
    // ou "Aluno", dependendo de como você salvou o tipo
    
        return "CadRec";
    }

    @PostMapping("recuperacao/salvar")
    public String salvarRecuperacao(
    @RequestParam("aluno") Long alunoId,
    @RequestParam("nota") String notaStr,
    String materia, 
    RedirectAttributes redirectAttributes
) {
    try {
        Aluno aluno = alunoRepository.findById(alunoId).orElse(null);
        
        // 1. Verificação de Aluno e Nota (ANTES de processar)
        if (aluno == null || notaStr == null || notaStr.isEmpty()) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro: Aluno ou nota inválidos.");
            return "redirect:/recuperacao/nova";
        }

        Double valor;
        try {
            valor = Double.parseDouble(notaStr.replace(",", "."));
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro: nota inválida.");
            return "redirect:/recuperacao/nova";
        }

        // 2. Cria e Salva a Nota
        Recuperacao recuperacao = new Recuperacao();
        recuperacao.setAluno(aluno); // Aqui você associa o objeto Aluno completo
        recuperacao.setNota(valor);
        recuperacao.setData(LocalDate.now());
        recuperacao.setMateria(materia);
        
        recuperacaoRepository.save(recuperacao);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Nota cadastrada com sucesso!");
        return "redirect:/recuperacao/nova";
    } catch (Exception e) {

        e.printStackTrace();

        redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao cadastrar nota.");

        return "redirect:/recuperacao/nova";

    }
    }

    @GetMapping("/recuperacao/listar")
    public String listarRecuperacoes(Model model) {
        model.addAttribute("recuperacoes", recuperacaoRepository.findAll());
        return "listaRecuperacao";
    }

    @GetMapping("/recuperacao/buscar")
    public String buscarPorAluno(@RequestParam("aluno") String aluno, Model model) {
        model.addAttribute("recuperacoes", recuperacaoRepository.findByAluno_Usuario_NomeContainingIgnoreCase(aluno));
        return "listaRecuperacao";
    }
    @GetMapping("professor/novo")
public String novoProfessor(Model model) {
    model.addAttribute("professor", new Professor());
    // Busca apenas usuários cadastrados como Professor
    List<Usuario> usuariosProfessores = usuarioRepository.findByTipo("Professor");
    model.addAttribute("usuariosProfessores", usuariosProfessores);
    return "CadProf";
}

    @PostMapping("professor/salvar")
public String salvarProfessor(@ModelAttribute Professor professor, RedirectAttributes redirectAttributes) {
    // Obtém o usuário selecionado
    Usuario usuario = professor.getUsuario();

    // Verifica se o usuário é válido e do tipo correto
    if (usuario == null || usuario.getId() == null) {
        redirectAttributes.addFlashAttribute("erro", "Usuário inválido.");
        return "redirect:/professor/novo";
    }

    // Busca o usuário completo do banco
    Usuario usuarioBanco = usuarioRepository.findById(professor.getUsuario().getId()).orElse(null);

    if (usuarioBanco == null || !"Professor".equalsIgnoreCase(usuarioBanco.getTipo())) {
        redirectAttributes.addFlashAttribute("erro", "O usuário selecionado não é do tipo Professor.");
        return "redirect:/professor/novo";
    }

    professor.setUsuario(usuarioBanco);
    professorRepository.save(professor);

    redirectAttributes.addFlashAttribute("sucesso", "Professor cadastrado com sucesso!");
    return "redirect:/professor/listar";
}

    @GetMapping("professor/listar")
    public String listarProfessores(Model model) {
        model.addAttribute("professores", professorRepository.findAll());
        return "listaProfessor";
    }

    @GetMapping("professor/buscar")
    public String buscarPorMateria(@RequestParam("materia") String materia, Model model) {
        model.addAttribute("professores", professorRepository.findByMateriaContainingIgnoreCase(materia));
        return "listaProfessor";
    }

    @GetMapping("professor/excluir/{id}")
    public String excluirProfessor(@PathVariable Long id) {
        professorRepository.deleteById(id);
        return "redirect:/professor/listar";
    }
}

