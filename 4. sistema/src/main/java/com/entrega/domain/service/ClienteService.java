package com.entrega.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entrega.domain.exception.NegocioException;
import com.entrega.domain.repository.ClienteRepository;
import com.entrega.model.Cliente;

import lombok.AllArgsConstructor;

/*
 * Classe de regra de negócios. Concentra todas as particularidades necessárias para armazenar
 * informações relativas a entidade Cliente => CRUD CLIENTE
 */

@Service
@AllArgsConstructor
public class ClienteService {
	
	private ClienteRepository clienteRepository;
	
	public Cliente buscar(Long clienteId) {
		return clienteRepository.findById(clienteId)
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));
	}
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		
		if(emailEmUso) {
			throw new NegocioException("Já existe um cliente cadastrado com este e-mail");
		}
	
		return clienteRepository.save(cliente);
		
		
	}
	@Transactional
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
}
