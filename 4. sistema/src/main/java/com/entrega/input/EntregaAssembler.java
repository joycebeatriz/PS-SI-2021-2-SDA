package com.entrega.input;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.entrega.model.Entrega;
import com.entrega.modelOut.EntregaModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EntregaAssembler {
	
	
	private ModelMapper modelMapper;
	
	
	public EntregaModel toModel (Entrega entrega) {
		return modelMapper.map(entrega, EntregaModel.class);
	}
	
	public List<EntregaModel> toCollectionModel(List<Entrega> entregas){
		return entregas.stream().map(this::toModel).collect(Collectors.toList());
	}
	
	public Entrega toEntity(@Valid Entrega entregaInput) {
		return modelMapper.map(entregaInput, Entrega.class);
	}
	
}
