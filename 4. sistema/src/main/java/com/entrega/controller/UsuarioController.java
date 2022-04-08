package com.entrega.controller;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.entrega.domain.repository.UsuarioRepository;
import com.entrega.domain.service.UsuarioService;
import com.entrega.model.Usuario;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3740815161058689511L;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private UsuarioService usuarioService;
	
	@GetMapping
	public List<Usuario> listar(){
		return usuarioRepository.findAll();
	}
	@GetMapping("/usuarioId")
	public ResponseEntity<Usuario> buscar(@PathVariable Long usuarioId){
		
		return usuarioRepository.findById(usuarioId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
		
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario adicionar(@Valid @RequestBody Usuario usuario) {
		return usuarioService.salvar(usuario);
	}
	@PutMapping("/usuarioId")
	public ResponseEntity<Usuario> atualizar(@Valid @PathVariable Long usuarioId, @RequestBody Usuario usuario){
		if(!usuarioRepository.existsById(usuarioId)) {
			return ResponseEntity.notFound().build();
		}
		usuario.setId(usuarioId);
		usuario = usuarioService.salvar(usuario);
		
		return ResponseEntity.ok(usuario);
	}
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long usuarioId){
		if(!usuarioRepository.existsById(usuarioId)) {
			return ResponseEntity.notFound().build();
		}
		usuarioService.excluir(usuarioId);
		return ResponseEntity.noContent().build();
	}
	@GetMapping("/{login}")
	public ResponseEntity<Usuario> login(@Valid @PathVariable String login,String senha){
		Long codigo = usuarioService.loginUsuario(login, senha);
		
		return buscar(codigo);
	}
	
}
