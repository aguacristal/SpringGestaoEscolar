/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pi.GerenciamentoEscolar.Repository;
import com.pi.GerenciamentoEscolar.Model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);
    List<Usuario> findByNomeContainingIgnoreCase(String nome);

    List<Usuario> findByTipo(String tipo);

    List<Usuario> findByNomeContainingIgnoreCaseAndTipo(String nome, String tipo);
}