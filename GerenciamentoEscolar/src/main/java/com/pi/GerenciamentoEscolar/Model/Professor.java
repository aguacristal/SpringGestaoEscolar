/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pi.GerenciamentoEscolar.Model;
import jakarta.persistence.*;

@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer usuario;
    private String materia;

    public Professor() {}

    public Professor(Integer usuario, String materia) {
        this.usuario = usuario;
        this.materia = materia;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getUsuario() { return usuario; }
    public void setUsuario(Integer usuario) { this.usuario = usuario; }

    public String getMateria() { return materia; }
    public void setMateria(String materia) { this.materia = materia; }
}
