/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pi.GerenciamentoEscolar.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Recuperacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne // Relação com Aluno
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;
    private String materia;
    private Double nota;
    private LocalDate data;

    public Recuperacao() {}

    public Recuperacao(Aluno aluno, String materia, Double nota, LocalDate data) {
        this.aluno = aluno;
        this.materia = materia;
        this.nota = nota;
        this.data = data;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }

    public String getMateria() { return materia; }
    public void setMateria(String materia) { this.materia = materia; }

    public Double getNota() { return nota; }
    public void setNota(Double nota) { this.nota = nota; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
}
