/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pi.GerenciamentoEscolar.Repository;

import com.pi.GerenciamentoEscolar.Model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    // ✅ CORRETO: Navega do campo 'aluno' (em Nota) até a propriedade 'nome' (em Aluno)
    List<Nota> findByAluno_Usuario_NomeContainingIgnoreCase(String nome); 
}