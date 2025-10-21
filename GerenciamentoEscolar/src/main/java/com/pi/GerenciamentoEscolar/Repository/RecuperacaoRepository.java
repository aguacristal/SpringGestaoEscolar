/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pi.GerenciamentoEscolar.Repository;

import com.pi.GerenciamentoEscolar.Model.Recuperacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecuperacaoRepository extends JpaRepository<Recuperacao, Long> {
    List<Recuperacao> findByAlunoContainingIgnoreCase(String aluno);
}