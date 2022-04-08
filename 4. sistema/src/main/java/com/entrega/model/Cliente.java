package com.entrega.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity   //objeto persistível no banco de dados
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente{
	
	
	
	//@NotNull(groups = ValidationGroups.ClienteId.class)
	@EqualsAndHashCode.Include
	@Id  //chave primária na tabela
	@GeneratedValue(strategy = GenerationType.IDENTITY) //chave primária deve ser populada pelo banco de dados auto_increment

	private Long id;
	
	@NotBlank //nome não pode ser nulo
	@Size(max = 60)
	private String nome;
	
	@NotBlank
	@Email  // e-mail deve ter configuração válida
	@Size(max = 255)
	private String email;
	
	@NotBlank
	@Size(max = 20)  // tamanho não pode exceder 20 caracteres
	private String telefone;
	
	
	
	

}
