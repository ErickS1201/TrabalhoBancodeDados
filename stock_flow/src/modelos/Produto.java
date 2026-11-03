package modelos;

public class Produto {
	private long cod_produto;
	private Categoria categoria;
	private String nome_produto;
	private double preco_produto;
	private int qtd_estoque;

	public Produto() {
		super();
	}

	public Produto(Categoria categoria, String nome_produto, double preco_produto, int qtd_estoque) {
		super();
		this.categoria = categoria;
		this.nome_produto = nome_produto;
		this.preco_produto = preco_produto;
		this.qtd_estoque = qtd_estoque;
	}

	public Produto(long cod_produto, Categoria categoria, String nome_produto, double preco_produto, int qtd_estoque) {
		super();
		this.cod_produto = cod_produto;
		this.categoria = categoria;
		this.nome_produto = nome_produto;
		this.preco_produto = preco_produto;
		this.qtd_estoque = qtd_estoque;
	}

	public long getCod_produto() {
		return cod_produto;
	}

	public void setCod_produto(long cod_produto) {
		this.cod_produto = cod_produto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getNome_produto() {
		return nome_produto;
	}

	public void setNome_produto(String nome_produto) {
		this.nome_produto = nome_produto;
	}

	public double getPreco_produto() {
		return preco_produto;
	}

	public void setPreco_produto(double preco_produto) {
		this.preco_produto = preco_produto;
	}

	public int getQtd_estoque() {
		return qtd_estoque;
	}

	public void setQtd_estoque(int qtd_estoque) {
		this.qtd_estoque = qtd_estoque;
	}

}
