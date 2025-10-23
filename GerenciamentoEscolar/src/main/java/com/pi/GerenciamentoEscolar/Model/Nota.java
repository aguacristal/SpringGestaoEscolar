/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pi.GerenciamentoEscolar.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

 @Entity
  @Table(name = "notas")
  public class Nota {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      @ManyToOne // Relação com Aluno
      @JoinColumn(name = "aluno_id", nullable = false) // Coluna FK no banco
      private Aluno aluno; // Agora é um objeto Aluno, não String

      private LocalDate data;
      private String materia;

      private Double nota;
    public Nota() {}

      public Nota(Aluno aluno, LocalDate data, String materia, Double nota) {
          this.aluno = aluno;
          this.data = data;
          this.materia = materia;
          this.nota = nota;
      }
    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public String getMateria() { return materia; }
    public void setMateria(String materia) { this.materia = materia; }

    public Double getNota() { return nota; }
    public void setNota(Double nota) { this.nota = nota; }
}
