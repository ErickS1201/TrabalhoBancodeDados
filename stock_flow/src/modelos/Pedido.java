package modelos;

import java.util.Date;

public class Pedido {
	private int cod_cliente;
	private int cod_produto;
	private int cod_pagamento;
	
	private Date data_pedido;
	private double valor;
	private StatusPedido status;
}

enum StatusPedido{
	CRIADO, AGUARDANDO_PAGAMENTO, PAGO, EM_PREPARACAO, ENVIADO, CONCLUIDO, CANCELADO,
}
