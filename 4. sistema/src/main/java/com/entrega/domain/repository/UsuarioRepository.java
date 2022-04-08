package com.entrega.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entrega.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	List<Usuario> findByNome(String nome);
	List<Usuario> findByNomeContaining(String nome);
	Optional<Usuario> findByEmail(String email);
	Optional<Usuario> findBySenha(String senha);
	
}
