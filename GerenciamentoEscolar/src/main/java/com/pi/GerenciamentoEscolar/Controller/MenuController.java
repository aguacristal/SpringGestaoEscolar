package com.pi.GerenciamentoEscolar.Controller;

import com.pi.GerenciamentoEscolar.Model.Aluno;
import com.pi.GerenciamentoEscolar.Model.Responsavel;
import com.pi.GerenciamentoEscolar.Model.Usuario;
import com.pi.GerenciamentoEscolar.Repository.AlunoRepository;
import com.pi.GerenciamentoEscolar.Repository.ResponsavelRepository;
import com.pi.GerenciamentoEscolar.Repository.UsuarioRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class MenuController {

    @Autowired
    private AlunoRepository alunoRepository;
    private ResponsavelRepository responsavelRepository;
    private UsuarioRepository usuarioRepository;

    @GetMapping("/menu")
    public String exibirMenu() {
        return "Menu"; // templates/Menu.html
    }

    @GetMapping("/aluno")
    public String exibirFormularioAluno(Model model) {
        return "CadAluno"; // templates/CadAluno.html
    }

    @PostMapping("/aluno/cadastrar")
    public String cadastrarAluno(
            @RequestParam String matricula,
            @RequestParam String responsavel,
            @RequestParam String turma
    ) {
        // Verifica matrícula duplicada
        if (alunoRepository.existsByMatricula(matricula)) {
            return "redirect:/aluno?erro=matricula";
        }

        Aluno aluno = new Aluno(matricula, responsavel, turma);
        alunoRepository.save(aluno);

        return "redirect:/aluno?sucesso";
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
    public String mostrarFormulario() {
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
        return "pesquisaUsuario";
    }
}
