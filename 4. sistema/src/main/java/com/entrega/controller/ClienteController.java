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

import com.entrega.domain.repository.ClienteRepository;
import com.entrega.domain.service.ClienteService;
import com.entrega.model.Cliente;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController  //Representation State Transfer especificação que define a forma de comunicação entre componentes de sw-us
                 // usa o protocolo HTTP - acessar dados de outra aplicação 
                 // utiliza URI para se conectar com uma interface de serviço. 
@RequestMapping("/clientes") //mapear requisições web para métodos spring controller vai atender solicitações dos métodos/verbos; GET POST PUT DELETE
public class ClienteController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2612924259664643648L;

	@Autowired
	private ClienteRepository clienteRepository;
	
	private ClienteService clienteService;
	

	//@GetMapping("/clientes")
	@GetMapping
	public List<Cliente> listar() {
		
		return clienteRepository.findAll();
	}
	/*
	 * ResponseEntity representa toda resposta HTTP: código de status, cabeçalhos e
	 * corpo. Como resultado, temos configuração da resposta HTTP
	 * Quando usar temos que devolvê-lo do endpoint, o spring cuida disso
	 * É um tipo genérico, pode ser usado em qualquer entidade como corpo da resposta
	 * Uma resposta com um corpo e um código de resposta HTTP 200
	 */
	
	  //@GetMapping("clientes/{clienteId}") 
	@GetMapping("/{clienteId}") 
	  public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) { 
		  	//Optional<cliente> cliente = clienteRepository.findById(clienteId);
	        //return cliente.orElse(null);
		  return clienteRepository.findById(clienteId)
				  //.map(cliente -> ResponseEntity.ok(cliente)
				  .map(ResponseEntity::ok)
				  .orElse(ResponseEntity.notFound().build());
	  }
	 

	/*
	 * @GetMapping("clientes/{clienteId}") public ResponseEntity<cliente>
	 * buscarCliente (@PathVariable Long clienteId) { Optional<cliente> cliente
	 * = clienteRepository.findById(clienteId);
	 * 
	 * if(cliente.isPresent()) { return ResponseEntity.ok(cliente.get()); }
	 * 
	 * 
	 * return ResponseEntity.notFound().build(); }
	 */
	
	/*
	 * a anotação @RequestBody mapeia o corpo HttpRequest para um objeto de transferência ou domínio, 
	 * permitindo a desserialização do JSON automática do corpo HttpRequest de entrada em um objeto java
	 * @RequestBody deve corresponder ao JSON enviado do nosso controller do lado do cliente
	 * 
	 * 
	*/
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	
	public Cliente adicionar(@Valid @RequestBody  Cliente cliente) {//
		//return clienteRepository.save(cliente);
		System.out.println("adicionando cliente");
		clienteService.salvar(cliente);
		System.out.println("Cliente salvo com sucesso");
		return cliente;
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar (@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente){
		
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId);
		//cliente = clienteRepository.save(cliente);
		cliente = clienteService.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
		
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover (@PathVariable Long clienteId){
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		//clienteRepository.deleteById(clienteId);
		clienteService.excluir(clienteId);
		return ResponseEntity.noContent().build();
	}

}
