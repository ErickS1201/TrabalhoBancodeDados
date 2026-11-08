package DAL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modelos.*;
public class ProdutoDAO {
	public void listarDisponiveis() {
		
	}
	public void salvarProduto(Produto produto) {
		String sql = "INSERT INTO produto (cod_categoria, nome_produto, preco_produto, qtd_estoque VALUES ("
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
	public Produto buscarPorCod(int cod) {
		String sql = "SELECT p.*, c* FROM produto p  INNER JOIN categoria c ON p.cod_categoria = c.cod_categoria where p.cod_produto = "+cod;
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
