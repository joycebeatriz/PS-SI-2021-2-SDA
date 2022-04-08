package com.entrega.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entrega.model.Entrega;
import com.entrega.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OcorrenciaService {
	
	//private EntregaRepository entregaRepository;
	private EntregaService buscaEntregaService;
	
	@Transactional
	public Ocorrencia registrar(Long entregaId, String descricao) {
		/*
		 * Entrega entrega = entregaRepository.findById(entregaId) .orElseThrow(()-> new
		 * NegocioException("Entrega não encontradas"));
		 * 
		 */
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		
		return entrega.adicionarOcorrencia(descricao);
		
	}

}
