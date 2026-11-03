package modelos;

import java.util.Date;

import java.util.Date;

public class Pagamento {
	private int cod_pagamento;
	private FormaPagamento forma;
	private StatusPagamento status;
	private double valor;
	private Date data_pagamento;
	
	
	public Pagamento(int cod_pagamento, FormaPagamento forma, StatusPagamento status, double valor,
			Date data_pagamento) {
		super();
		this.cod_pagamento = cod_pagamento;
		this.forma = forma;
		this.status = status;
		this.valor = valor;
		this.data_pagamento = data_pagamento;
	}
	public Pagamento() {
		super();
	}
	public Pagamento(FormaPagamento forma, StatusPagamento status, double valor, Date data_pagamento) {
		super();
		this.forma = forma;
		this.status = status;
		this.valor = valor;
		this.data_pagamento = data_pagamento;
	}
	public int getCod_pagamento() {
		return cod_pagamento;
	}
	public void setCod_pagamento(int cod_pagamento) {
		this.cod_pagamento = cod_pagamento;
	}
	public FormaPagamento getForma() {
		return forma;
	}
	public void setForma(FormaPagamento forma) {
		this.forma = forma;
	}
	public StatusPagamento getStatus() {
		return status;
	}
	public void setStatus(StatusPagamento status) {
		this.status = status;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Date getData_pagamento() {
		return data_pagamento;
	}
	public void setData_pagamento(Date data_pagamento) {
		this.data_pagamento = data_pagamento;
	}
	
	
	
}

enum FormaPagamento{
	DEBITO, CREDITO, PIX, CARTEIRA;
}
enum StatusPagamento{
	CRIADO, AGUARDANDO, PROCESSADO;
}
