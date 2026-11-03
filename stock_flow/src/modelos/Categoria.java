package modelos;

public class Categoria {
	private int cod_categoria;
	private String nome_categoria;
	private String descricao;
	public Categoria(String nome_categoria, String descricao) {
		super();
		this.nome_categoria = nome_categoria;
		this.descricao = descricao;
	}
	public Categoria(int cod_categoria, String nome_categoria, String descricao) {
		super();
		this.cod_categoria = cod_categoria;
		this.nome_categoria = nome_categoria;
		this.descricao = descricao;
	}
	public Categoria() {
		super();
	}
	public int getCod_categoria() {
		return cod_categoria;
	}
	public void setCod_categoria(int cod_categoria) {
		this.cod_categoria = cod_categoria;
	}
	public String getNome_categoria() {
		return nome_categoria;
	}
	public void setNome_categoria(String nome_categoria) {
		this.nome_categoria = nome_categoria;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String toString() {
		return "Categoria [cod_categoria=" + cod_categoria + ", nome_categoria=" + nome_categoria + ", descricao="
				+ descricao + "]";
	}
	
	
	
}
