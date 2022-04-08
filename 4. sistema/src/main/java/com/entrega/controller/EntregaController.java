package com.entrega.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.entrega.domain.repository.EntregaRepository;
import com.entrega.domain.service.SolicitacaoEntregaService;
import com.entrega.input.EntregaAssembler;
import com.entrega.model.Entrega;
import com.entrega.modelOut.EntregaModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private SolicitacaoEntregaService solicitacaoEntregaService;
	private EntregaRepository entregaRepository;
	//private ModelMapper modelMapper;
	private EntregaAssembler entregaAssembler;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaModel solicitar(@Valid @RequestBody Entrega entregaInput) {
		System.out.println("-------------------------------\n");
		Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);
		Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);
		return entregaAssembler.toModel(entregaSolicitada);
		
		
	}

	@GetMapping
	public List<EntregaModel> listar() {
		//return entregaRepository.findAll();
		return entregaAssembler.toCollectionModel(entregaRepository.findAll());
	}

	/*
	 * @GetMapping("/{entregaId}") public ResponseEntity<Entrega> buscar
	 * (@PathVariable Long entregaId){ return entregaRepository.findById(entregaId)
	 * .map(ResponseEntity::ok) .orElse(ResponseEntity.notFound().build());
	 * 
	 * }
	 */
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId) {
		return entregaRepository.findById(entregaId)				
				.map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
			//EntregaModel entregaModel = new EntregaModel();
			//EntregaModel entregaModel = modelMapper.map(entrega, EntregaModel.class);
			

			/*
			 * entregaModel.setId(entrega.getId());
			 * entregaModel.setNomeCliente(entrega.getCliente().getNome());
			 * entregaModel.setDestinatario(new DestinatarioModel());
			 * entregaModel.getDestinatario().setNome(entrega.getDestinatario().getNome());
			 * entregaModel.getDestinatario().setLogradouro(entrega.getDestinatario().
			 * getLogradouro());
			 * entregaModel.getDestinatario().setNumero(entrega.getDestinatario().getNumero(
			 * )); entregaModel.getDestinatario().setComplemento(entrega.getDestinatario().
			 * getComplemento());
			 * entregaModel.getDestinatario().setBairro(entrega.getDestinatario().getBairro(
			 * )); entregaModel.setTaxa(entrega.getTaxa());
			 * entregaModel.setStatus(entrega.getStatus());
			 * entregaModel.setDataPedido(entrega.getDataPedido());
			 * entregaModel.setDataFinalizacao(entrega.getDataFinalizacao());
			 */

			
		.orElse(ResponseEntity.notFound().build());

	}
}
