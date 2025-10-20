/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pi.GerenciamentoEscolar.Model;
import java.time.LocalDateTime;

public class Nota {
    private int id;
    private int alunoId;
    private int materiaId;
    private double nota;
    private LocalDateTime lancamento;

    public Nota() {}

    public Nota(int id, int alunoId, int materiaId, double nota) {
        this.id = id;
        this.alunoId = alunoId;
        this.materiaId = materiaId;
        this.nota = nota;
        this.lancamento = LocalDateTime.now();
    }

    public Nota(int alunoId, int materiaId, double nota, LocalDateTime lancamento) {
        this.alunoId = alunoId; this.materiaId = materiaId; this.nota = nota; this.lancamento = lancamento;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getAlunoId() { return alunoId; }
    public void setAlunoId(int alunoId) { this.alunoId = alunoId; }

    public int getMateriaId() { return materiaId; }
    public void setMateriaId(int materiaId) { this.materiaId = materiaId; }

    public double getNota() { return nota; }
    public void setNota(double nota) { this.nota = nota; }

    public LocalDateTime getLancamento() { return lancamento; }
    public void setLancamento(LocalDateTime lancamento) { this.lancamento = lancamento; }

    @Override
    public String toString() {
        return "Nota{" + "alunoId=" + alunoId + ", materiaId=" + materiaId + ", nota=" + nota + ", lancamento=" + lancamento + '}';
    }
}
