package modelos;

import java.util.ArrayList;
import java.util.List;

import DAL.CategoriaDAO;
import DAL.ClienteDAO;
import DAL.ProdutoDAO;

public class Testadora {
	public static void main(String[] args) {
//		Categoria test = new Categoria("Testando", "Testando novamente");
		CategoriaDAO Catdao = new CategoriaDAO();
//		Catdao.salvar(test);
		Categoria cat = Catdao.buscarPorCod(1);
//		System.out.println(cat.getNome_categoria());
//		cat.setNome_categoria("Testando update nome categoria");
//		Catdao.atualizarCategoria(1, cat);
//		List<Categoria> cats = new ArrayList<Categoria>();
//		cats = Catdao.listarCategorias();
//		for (Categoria categoria : cats) {
//			System.out.println(categoria.getNome_categoria());
//		}
//		ClienteDAO dao = new ClienteDAO();
//		dao.deletarCliente(3);
//		Cliente cl = dao.buscarPorCpf("33311122245");
//		Cliente cl2 = dao.buscarPorCod(1);
//		System.out.println(cl.getNome());
//		cl2.setTel("7199887766");
//		dao.atualizarCliente(1, cl2);
//		
//		Cliente cliente = new Cliente("Erick", "98765432122", "erick@email", "9988776655");
//		dao.salvar(cliente);
//		Cliente cl3 = dao.buscarPorCod(2);
//		System.out.println("NOME"+ cl2.getNome()+"Email"+cl2.getEmail());
		ProdutoDAO proddao = new ProdutoDAO();
		Produto produto = proddao.buscarPorCod(1);
		//proddao.salvarProduto(produto);
//		ArrayList<Produto> produtos = proddao.listarProdutos();
//		for (Produto prod : produtos) {
//			System.out.println(prod);
//		}
		produto.setNome_produto("testando update produto");
		proddao.atualizarProduto(1, produto);
		produto = proddao.buscarPorCod(1);
		System.out.println(produto);
		
	}
}
