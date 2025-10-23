/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pi.GerenciamentoEscolar.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // >> MUDANÇA CRUCIAL: RELAÇÃO COM USUARIO
    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false) 
    private Usuario usuario; // Aluno agora tem um Usuario associado.
    
    private String matricula;
    private String responsavel;
    private String turma;
    // Construtor padrão (necessário para JPA)
    public Aluno() {}

    // Construtor usado no Controller
    public Aluno(Usuario usuario, String matricula, String responsavel, String turma) {
        this.matricula = matricula;
        this.responsavel = responsavel;
        this.turma = turma;
        this.usuario = usuario;
    }

    // Getters e Setters (Necessários para o Spring/Thymeleaf)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }
    public String getNome(){
        return this.usuario != null ? this.usuario.getNome() : "Aluno Desconhecido";}
    }

