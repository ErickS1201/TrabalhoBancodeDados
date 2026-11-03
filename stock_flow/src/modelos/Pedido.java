package modelos;

import java.util.ArrayList;
import java.util.Date;

public class Pedido {
	private long cod_pedido;
	private Cliente cliente;
	private Pagamento pagamento;
	private Date data_pedido;
	private double valor;
	private StatusPedido status;
	private ArrayList <PedidoProduto> itens = new ArrayList<>();
	
	
	public Pedido() {
		super();
	}
	
	
	
	public Pedido(long cod_pedido, Cliente cliente, Pagamento pagamento, Date data_pedido, double valor,
			StatusPedido status) {
		super();
		this.cod_pedido = cod_pedido;
		this.cliente = cliente;
		this.pagamento = pagamento;
		this.data_pedido = data_pedido;
		this.valor = valor;
		this.status = status;
	}

	

	public ArrayList<PedidoProduto> getItens() {
		return itens;
	}



	public void setItens(ArrayList<PedidoProduto> itens) {
		this.itens = itens;
	}



	public Pedido( Cliente cliente, Pagamento pagamento, Date data_pedido, double valor,
			StatusPedido status) {
		super();
		this.cliente = cliente;
		this.pagamento = pagamento;
		this.data_pedido = data_pedido;
		this.valor = valor;
		this.status = status;
	}



	public long getCod_pedido() {
		return cod_pedido;
	}



	public void setCod_pedido(long cod_pedido) {
		this.cod_pedido = cod_pedido;
	}



	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Pagamento getPagamento() {
		return pagamento;
	}
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	public Date getData_pedido() {
		return data_pedido;
	}
	public void setData_pedido(Date data_pedido) {
		this.data_pedido = data_pedido;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public StatusPedido getStatus() {
		return status;
	}
	public void setStatus(StatusPedido status) {
		this.status = status;
	}
	
	
	
}

enum StatusPedido{
	CRIADO, AGUARDANDO_PAGAMENTO, PAGO, EM_PREPARACAO, ENVIADO, CONCLUIDO, CANCELADO,
}
