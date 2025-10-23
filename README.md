Gerenciamento Escolar

Projeto de gerenciamento escolar desenvolvido em Spring Boot, utilizando Thymeleaf para front-end e Spring Data JPA para persistência em banco de dados MySQL. Permite o cadastro, pesquisa e gerenciamento de alunos, professores, matérias, notas, recuperações, responsáveis e usuários.

🔹 Funcionalidades

Cadastro e pesquisa de usuários

Cadastro de alunos, professores e responsáveis.

Pesquisa de usuários por nome e tipo.

Cadastro de professores e matérias

Associar professores a matérias já cadastradas.

Evita duplicidade de cadastro.

Cadastro de notas e recuperação

Cadastro de notas por aluno, com validação de valores.

Cadastro de recuperação de alunos.

Pesquisa de notas e recuperações por aluno.

Menu de navegação

Telas de menu para professores e administrador.

Botões de navegação entre cadastro, listagem e pesquisa.

🔹 Tecnologias utilizadas

Java 17+

Spring Boot 3.x

Spring Data JPA / Hibernate

Thymeleaf

MySQL

Maven

Bootstrap / CSS básico

🔹 Estrutura do Projeto
com.pi.GerenciamentoEscolar
│
├─ Controller/        -> Controladores Spring MVC para rotas e ações
├─ Model/             -> Entidades JPA
├─ Repository/        -> Interfaces Spring Data JPA
├─ templates/         -> Páginas HTML com Thymeleaf
├─ static/            -> CSS, JS, imagens
└─ GerenciamentoEscolarApplication.java -> Classe principal

🔹 Configuração do Banco de Dados

Exemplo application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/gerenciamento_escolar
spring.datasource.username=root
spring.datasource.password=1234
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.thymeleaf.cache=false


Crie o banco gerenciamento_escolar no MySQL antes de rodar a aplicação.

🔹 Como rodar o projeto

Clone o repositório:

git clone <URL_DO_REPOSITORIO>


Abra no IDE (IntelliJ, Eclipse ou NetBeans).

Configure o banco de dados no application.properties.

Execute a classe principal:

GerenciamentoEscolarApplication.java


Acesse no navegador:

http://localhost:8080/menu

🔹 Endpoints principais
Endpoint	Método	Descrição
/menu	GET	Página inicial do menu
/aluno	GET	Formulário de cadastro de aluno
/aluno/cadastrar	POST	Salva o aluno no banco
/usuario/cadastro	GET	Formulário de cadastro de usuário
/usuario/cadastrar	POST	Salva o usuário no banco
/usuario/pesquisa	GET	Página de pesquisa de usuários
/usuario/buscar	GET	Retorna resultados da pesquisa
/nota/cadastro	GET	Formulário de cadastro de nota
/nota/cadastrar	POST	Salva a nota
/nota/pesquisar	GET	Pesquisa notas por aluno
/professor/novo	GET	Formulário de cadastro de professor
/professor/salvar	POST	Salva o professor
/professor/listar	GET	Lista todos os professores
/professor/buscar	GET	Busca professor por matéria
/recuperacao/nova	GET	Formulário de cadastro de recuperação
/recuperacao/listar	GET	Lista todas as recuperações
/recuperacao/buscar	GET	Busca recuperação por aluno
🔹 Observações importantes

As telas usam Thymeleaf para binding de formulários.

Algumas entidades possuem validação básica de campos obrigatórios.

Ao cadastrar notas ou professores, é necessário que o aluno ou usuário já esteja previamente cadastrado.

Evite duplicidade de registros, pois algumas tabelas possuem validação de existência.
