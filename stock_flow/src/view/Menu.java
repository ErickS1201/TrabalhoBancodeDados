package view;

import java.util.Scanner;

import DAL.*;
import modelos.*;

public class Menu {
	Scanner tc = new Scanner(System.in);
	int op;
	String nome, cpf;
	boolean logado = false;
	public void start() {
	do
	{
		System.out.println("-----------LOGIN-----------");
		System.out.println("Aperte 1 para Login e 2 para criar conta ou 0 para sair:");
		op = tc.nextInt();
		tc.nextLine();
		if (op == 1) {
			System.out.println("Digite seu nome: ");
			nome = tc.nextLine();
			System.out.println("Digite seu cpf");
			cpf = tc.nextLine();
			logado = login(nome, cpf) != null;
			Cliente cl = null;
			if (logado)
				cl = login(nome, cpf);
				menuInicial(cl);
			
		} else if (op == 2) {
			criarConta();
		} else if (op == 0) {
			System.out.println("Saindo do sistema");
			logado = true;
		}

	}while(!logado);
	}
	private void criarConta() {
		ClienteDAO dao = new ClienteDAO();
		String cpf="";
		Cliente cl = new Cliente();
		cl.setCod_cliente(1);
		
		System.out.println("Digite seu nome: ");
		String nome = tc.nextLine();
		while(cl.getCod_cliente()!=0) {
		System.out.println("Digite seu cpf: ");
		cpf = tc.nextLine();
		cl = dao.buscarPorCpf(cpf);
		if(cl.getCod_cliente()!=0)System.out.println("Cliente ja existe no sistema");
		}
		System.out.println("Digite seu email: ");
		String email = tc.nextLine();
		System.out.println("Digite seu telefone: ");
		String telefone = tc.nextLine();
		Cliente clientePersist = new Cliente(nome, cpf, email, telefone);
		if(!nome.trim().isEmpty() && !cpf.trim().isEmpty() && !email.trim().isEmpty() && !telefone.trim().isEmpty()) {
		dao.salvar(clientePersist);
		System.out.println("Cliente salvo com sucesso !!!\n Agora efetue o login");
		}else {
			System.out.println("Algo deu errado na criação de login");
		}
	}

	private void menuInicial(Cliente cliente) {
    System.out.println("Bem vindo, " + cliente.getNome());
    int opcaoMenu = -1;

    do {
        System.out.println("\nSelecione uma opcao: ");
        System.out.println("1: Comprar produto");
        System.out.println("2: Atualizar perfil");
        System.out.println("3: Mostrar pedidos");
        System.out.println("0: Sair");
        
        opcaoMenu = tc.nextInt();
        tc.nextLine(); 

        switch (opcaoMenu) {

            case 1:
                ProdutoDAO produtoDAO = new ProdutoDAO();
                produtoDAO.listarDisponiveis();

                System.out.println("\nDigite o COD do produto que deseja comprar (ou 0 para cancelar): ");
                long codProd = tc.nextLong();
                tc.nextLine();

                if (codProd != 0) {
                    System.out.println("Digite a quantidade: ");
                    int qtd = tc.nextInt();
                    tc.nextLine();

                    System.out.println("Forma de pagamento (PIX, CREDITO, DEBITO, CARTEIRA): ");
                    String forma = tc.nextLine().toUpperCase();

                    produtoDAO.realizarCompra(cliente, codProd, qtd, forma);
                }
                break;

            case 2:
                System.out.println("\n--- Atualizar Perfil ---");
                System.out.println("Nome atual: " + cliente.getNome() + " | Digite o novo nome: ");
                String novoNome = tc.nextLine();

                System.out.println("CPF atual: " + cliente.getCpf() + " | Digite o novo CPF (11 digitos): ");
                String novoCpf = tc.nextLine();

                System.out.println("Email atual: " + cliente.getEmail() + " | Digite o novo email: ");
                String novoEmail = tc.nextLine();

                System.out.println("Telefone atual: " + cliente.getTel() + " | Digite o novo telefone: ");
                String novoTel = tc.nextLine();

                cliente.setNome(novoNome);
                cliente.setCpf(novoCpf);
                cliente.setEmail(novoEmail);
                cliente.setTel(novoTel);

                ClienteDAO clienteDAO = new ClienteDAO();
                clienteDAO.atualizarCliente(cliente.getCod_cliente(), cliente);
                break;

            case 3:
                ProdutoDAO prodDAO = new ProdutoDAO();
                prodDAO.listarPedidosCliente(cliente.getCod_cliente());
                break;

            case 0:
                System.out.println("Saindo da conta...");
                break;
            default:
                System.out.println("Opcao invalida!");
                break;
        }
    } while (opcaoMenu != 0);
}
public Cliente login(String nome, String cpf) {
		ClienteDAO dao = new ClienteDAO();
		Cliente cl = dao.buscarPorCpf(cpf);
		if(cl.getNome()!=null && cl.getNome().equalsIgnoreCase(nome)) {
			System.out.println("Login efetuado com sucesso");
			return cl;
		}else {
			return null;
		}
}
}

	

