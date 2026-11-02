package modelos;

public class Fornecedor {
	private long id;
	private long razao_social;
	private long telefone;
	private long email;
	public Fornecedor(long id, long razao_social, long telefone, long email) {
		super();
		this.id = id;
		this.razao_social = razao_social;
		this.telefone = telefone;
		this.email = email;
	}
	public Fornecedor() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getRazao_social() {
		return razao_social;
	}
	public void setRazao_social(long razao_social) {
		this.razao_social = razao_social;
	}
	public long getTelefone() {
		return telefone;
	}
	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}
	public long getEmail() {
		return email;
	}
	public void setEmail(long email) {
		this.email = email;
	}
	
}
