package modelos;

import java.util.Date;

import java.util.Date;

public class Pagamento {
	private int cod_pagamento;
	private FormaPagamento forma;
	private StatusPagamento status;
	private double valor;
	private Date data_pagamento;
}

enum FormaPagamento{
	DEBITO, CREDITO, PIX, CARTEIRA;
}
enum StatusPagamento{
	CRIADO, AGUARDANDO, PROCESSADO;
}
