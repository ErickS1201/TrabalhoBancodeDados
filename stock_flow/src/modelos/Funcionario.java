package modelos;

public class Funcionario {
    
    private int cod_funcionario;
    private String nome_funcionario;
    private String cpf;
    private String cargo;
    private String senha;

    public Funcionario(){}

    public Funcionario(int cod_funcionario, String  nome_funcionario, String cpf, String cargo, String senha){
        this.cod_funcionario = cod_funcionario;
        this.nome_funcionario = nome_funcionario;
        this.cpf = cpf;
        this.cargo = cargo;
        this.senha = senha;
    }

    public int getCod_funcionario() {
        return cod_funcionario;
    }
     public void setCod_funcionario(int cod_funcionario){
      this.cod_funcionario = cod_funcionario;
     }

    public String getNome_funcionario() {
        return nome_funcionario;
            }
            public void setNome_funcionario(String nome_funcionario){
                this.nome_funcionario = nome_funcionario;
                }
    public String getCpf(){
        return cpf;
                    }
             public void setCpf(String cpf){
                this.cpf = cpf;
                }
    public String getCargo(){
        return cargo;
             }
            public void setCargo(String cargo){
                this.cargo = cargo;
                                }
    public String getSenha(){
        return senha;
             }
        public void setSenha(String senha){
            this.senha = senha;
             }
}
