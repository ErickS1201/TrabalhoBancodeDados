package modelos;

public class PedidoProduto {
	private Pedido pedido;
	private Produto produto;
	private int qtdComprada;
	private double valor_venda;
	public PedidoProduto(Pedido pedido, Produto produto, int qtdComprada, double valor_venda) {
		super();
		this.pedido = pedido;
		this.produto = produto;
		this.qtdComprada = qtdComprada;
		this.valor_venda = valor_venda;
	}
	public PedidoProduto() {
		super();
	}
	public Pedido getPedido() {
		return pedido;
	}
	
	@Override
	public String toString() {
		return "PedidoProduto [pedido=" + pedido + ", produto=" + produto + ", qtdComprada=" + qtdComprada
				+ ", valor_venda=" + valor_venda + "]";
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQtdComprada() {
		return qtdComprada;
	}
	public void setQtdComprada(int qtdComprada) {
		this.qtdComprada = qtdComprada;
	}
	public double getValor_venda() {
		return valor_venda;
	}
	public void setValor_venda(double valor_venda) {
		this.valor_venda = valor_venda;
	}
	
	
	
}
