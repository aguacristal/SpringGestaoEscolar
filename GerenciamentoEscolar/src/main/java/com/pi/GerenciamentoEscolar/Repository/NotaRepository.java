/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pi.GerenciamentoEscolar.Repository;
import com.pi.GerenciamentoEscolar.Model.Nota;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class NotaRepository implements IRepository<Nota, Integer> {
    private final Map<Integer, Nota> db = new HashMap<>();
    private final AtomicInteger seq = new AtomicInteger(0);

    @Override
    public void salvar(Nota nota) {
        if (nota.getId() == 0) {
            nota.setId(seq.incrementAndGet());
        }
        db.put(nota.getId(), nota);
    }

    @Override
    public Nota buscarPorId(Integer id) {
        return db.get(id);
    }

    @Override
    public List<Nota> buscarTodos() {
        return new ArrayList<>(db.values());
    }

    @Override
    public void remover(Integer id) {
        db.remove(id);
    }

    // Métodos específicos
    public List<Nota> buscarPorAluno(int alunoId) {
        return db.values().stream()
                .filter(n -> n.getAlunoId() == alunoId)
                .collect(Collectors.toList());
    }

    public double calcularMedia(int alunoId) {
        List<Nota> notas = buscarPorAluno(alunoId);
        return notas.stream().mapToDouble(Nota::getNota).average().orElse(0.0);
    }
}
