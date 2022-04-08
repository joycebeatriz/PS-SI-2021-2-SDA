package com.entrega.domain.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entrega.domain.exception.NegocioException;
import com.entrega.domain.repository.UsuarioRepository;
import com.entrega.model.Usuario;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	
	public Usuario buscar(Long usuarioId) {
		return usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new NegocioException("Usuario nao encontrado"));
	}
	@Transactional
	public Usuario salvar(Usuario usuario) {
		boolean emailEmUso = usuarioRepository.findByEmail(usuario.getEmail())
				.stream()
				.anyMatch(usuarioExistente -> !usuarioExistente.equals(usuario));
		
		if(emailEmUso) {
			throw new NegocioException("Já existe um usuario cadastrado com este e-mail");
		}
		return usuarioRepository.save(usuario);
	}
	@Transactional
	public void excluir(Long usuarioId) {
		usuarioRepository.deleteById(usuarioId);
		
	}
	@Transactional
	public Long loginUsuario(@Valid String email, @Valid String senha) {
		Usuario usuario = new Usuario();
		System.out.println("email" + email);
		Long codigo;
	    boolean emailLogin = usuarioRepository.findByEmail(email)
	    		.stream()
	    		.anyMatch(emailExistente -> emailExistente.equals(usuario.getEmail()));
	    		
	    if(emailLogin) {
	    	System.out.println("teste senha");
	    	boolean senhaLogin = usuarioRepository.findBySenha(senha)
	    			.stream()
	    			.anyMatch(senhaExistente -> !senhaExistente.equals(usuario.getSenha()));
	    	if(senhaLogin) {
	    		
	    		codigo = usuario.getId();
	    		System.out.println(codigo);
	    		return codigo;
	    	}
	    	
	    }
	    
	    throw new NegocioException("Login ou senha invalidos");
	    
	    		
		
	}
	
	
}
