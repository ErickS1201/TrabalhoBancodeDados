package DAL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modelos.*;
public class ProdutoDAO {
	public ArrayList<Produto> listarProdutos(){
		String sql = "SELECT p.*, c.* from produto p INNER JOIN categoria c ON p.cod_categoria = c.cod_categoria";
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		ArrayList <Produto>produtos = new ArrayList<Produto>();
		
		try {
			con = ConexaoSGBD.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			
			while(rs.next()) {
				Categoria categoria = new Categoria(rs.getInt("cod_categoria"), rs.getString("nome_categoria"), rs.getString("descricao"));
				Produto produto = new Produto(rs.getLong("cod_produto"),categoria, rs.getString("nome_produto"), rs.getDouble("preco_produto"), rs.getInt("qtd_estoque"));
				produtos.add(produto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			fecharRecursos(con, stm, rs);
		}
		return produtos;
	}
	public void atualizarProduto(long cod, Produto produto) {
		Produto prod = buscarPorCod(cod);
		if(prod != null) {
			String sql = "UPDATE produto SET cod_categoria = "+prod.getCategoria().getCod_categoria()+", nome_produto = '"+produto.getNome_produto()+"', preco_produto = "+produto.getPreco_produto()+", qtd_estoque = "+produto.getQtd_estoque()+" WHERE cod_produto = "+cod;
			Connection con = null;
			Statement stm = null;
			
			try {
				con = ConexaoSGBD.getConnection();
				stm = con.createStatement();
				stm.execute(sql);
				System.out.println("Produto atualizado com sucesso");
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				fecharRecursos(con, stm, null);
			}
		}else {
			System.out.println("Produto nao encontrado");
		}
	}
	public void deletarProduto(long cod) {
		String sql = "DELETE FROM produto WHERE cod_produto ="+cod;
		Connection con = null;
		Statement stm = null;
		try {
			con = ConexaoSGBD.getConnection();
			stm = con.createStatement();
			int linhas = stm.executeUpdate(sql);
			if(linhas >0)System.out.println("Produto excluido com sucesso");
			else {
				System.out.println("Produto nao encontrado");
			}
		}
	}
	public void listarDisponiveis() {
		String sql = "SELECT p.*, c.* FROM produto p  INNER JOIN categoria c ON p.cod_categoria = c.cod_categoria where p.qtd_estoque >0 ";
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		ArrayList <Produto>produtos = new ArrayList<Produto>();
		try {
			con= ConexaoSGBD.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			
			while(rs.next()) {
				Categoria cat = new Categoria();
				cat.setCod_categoria(rs.getInt("cod_categoria"));
				cat.setNome_categoria(rs.getString("nome_categoria"));
				cat.setDescricao(rs.getString("descricao"));
				Produto produto = new Produto();
				produto.setCod_produto(rs.getLong("cod_produto"));
				produto.setCategoria(cat);
				produto.setNome_produto(rs.getString("nome_produto"));
				produto.setPreco_produto(rs.getDouble("preco_produto"));
				produto.setQtd_estoque(rs.getInt("qtd_estoque"));
				produtos.add(produto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			fecharRecursos(con, stm, rs);
		}
		for (Produto prod : produtos) {
			System.out.println(prod);
		}
		
		
		
		
	}
	public void salvarProduto(Produto produto) {
		String sql = "INSERT INTO produto (cod_categoria, nome_produto, preco_produto, qtd_estoque) VALUES ("
				+ produto.getCategoria().getCod_categoria()+","
				+ "'"+produto.getNome_produto()+"',"+produto.getPreco_produto()+","+produto.getQtd_estoque()+")";
		Connection con = null;
		Statement stm = null;
		try {
			con = ConexaoSGBD.getConnection();
			stm = con.createStatement();
			stm.executeUpdate(sql);
		}catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharRecursos(con, stm, null);
        }
	
	}
	public Produto buscarPorCod(long cod) {
		String sql = "SELECT p.*, c.* FROM produto p  INNER JOIN categoria c ON p.cod_categoria = c.cod_categoria where p.cod_produto = "+cod;
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		Produto produto = new Produto();
		try {
			con= ConexaoSGBD.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			
			if(rs.next()) {
				Categoria cat = new Categoria();
				cat.setCod_categoria(rs.getInt("cod_categoria"));
				cat.setNome_categoria(rs.getString("nome_categoria"));
				cat.setDescricao(rs.getString("descricao"));
				
				produto.setCod_produto(rs.getLong("cod_produto"));
				produto.setCategoria(cat);
				produto.setNome_produto(rs.getString("nome_produto"));
				produto.setPreco_produto(rs.getDouble("preco_produto"));
				produto.setQtd_estoque(rs.getInt("qtd_estoque"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			fecharRecursos(con, stm, rs);
		}
		if(produto.getCod_produto()==0) produto = null;
		return produto;
	}
	private void fecharRecursos(Connection con, Statement stm, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(stm != null) stm.close();
			if(con != null) con.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
