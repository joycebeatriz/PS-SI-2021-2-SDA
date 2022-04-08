package com.entrega.domain.service;

import java.time.OffsetDateTime;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entrega.domain.repository.EntregaRepository;
import com.entrega.model.Cliente;
import com.entrega.model.Entrega;
import com.entrega.model.StatusEntrega;

import lombok.AllArgsConstructor;

/**
 * @author delvo
 * que possui classes com as regras de negócio da aplicação. É considerada uma boa prática 
 * ter uma classe do tipo service para cada entidade ou dao do projeto, 
 * podendo diminuir assim, a dependência das classes de entidades em relação às classes de CRUD,
 * nos proporcionando maior facilidade de reuso e um código mais limpo
 *
 */
@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {
	
	private EntregaRepository entregaRepository;
	private ClienteService clienteService;
	//private ClienteRepository clienteRepository;
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
		
		/*
		 * Cliente cliente = clienteRepository.findById(entrega.getCliente().getId())
		 * .orElseThrow(()-> new NegocioException("Cliente não encontrado"));
		 */
		
		Cliente cliente = clienteService.buscar(entrega.getCliente().getId());
		
		entrega.setCliente(cliente);  
		entrega.setStatus(StatusEntrega.PENDENTE);
		//entrega.setDataPedido(LocalDateTime.now());
		entrega.setDataPedido(OffsetDateTime.now());
		
		return entregaRepository.save(entrega);
	}
	
	

}
