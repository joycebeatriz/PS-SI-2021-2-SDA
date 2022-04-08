package com.entrega.modelOut;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.entrega.model.StatusEntrega;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaModel {
	
	private Long id;
	private ClienteResumoModel cliente;
	private BigDecimal taxa;
	private StatusEntrega status;
	private OffsetDateTime dataPedido;
	private OffsetDateTime dataFinalizacao;
	
	private DestinatarioModel destinatario;
	
	
}
