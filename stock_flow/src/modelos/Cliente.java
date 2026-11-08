package modelos;

public class Cliente {
	private int cod_cliente;
	private String nome;
	private String cpf;
	private String email;
	private String tel;
	public Cliente(String nome, String cpf, String email, String tel) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.tel = tel;
	}
	public Cliente() {
		super();
	}
	
	public Cliente(int cod_cliente, String nome, String cpf, String email, String tel) {
		super();
		this.cod_cliente = cod_cliente;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.tel = tel;
	}
	public int getCod_cliente() {
		return cod_cliente;
	}
	public void setCod_cliente(int cod_cliente) {
		this.cod_cliente = cod_cliente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	
}
