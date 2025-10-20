/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pi.GerenciamentoEscolar.Repository;
import java.util.List;

public interface IRepository<T, K> {
    void salvar(T entity);
    T buscarPorId(K id);
    List<T> buscarTodos();
    void remover(K id);
}