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

    private String aluno;
    private String materia;
    private Double nota;
    private LocalDate data;

    public Recuperacao() {}

    public Recuperacao(String aluno, String materia, Double nota, LocalDate data) {
        this.aluno = aluno;
        this.materia = materia;
        this.nota = nota;
        this.data = data;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAluno() { return aluno; }
    public void setAluno(String aluno) { this.aluno = aluno; }

    public String getMateria() { return materia; }
    public void setMateria(String materia) { this.materia = materia; }

    public Double getNota() { return nota; }
    public void setNota(Double nota) { this.nota = nota; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
}
