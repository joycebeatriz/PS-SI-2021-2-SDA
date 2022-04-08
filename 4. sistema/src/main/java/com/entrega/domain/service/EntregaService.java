package com.entrega.domain.service;

import org.springframework.stereotype.Service;

import com.entrega.domain.exception.EntidadeNaoEncontradaException;
import com.entrega.domain.repository.EntregaRepository;
import com.entrega.model.Entrega;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EntregaService {
	
	private EntregaRepository entregaRepository;
	
	public Entrega buscar(Long entregaId) {
		return entregaRepository.findById(entregaId)
				.orElseThrow(()->  new EntidadeNaoEncontradaException("Entrega não encontrada"));
	}
}
