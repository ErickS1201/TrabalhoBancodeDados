package modelos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
	public void salvar(Categoria categoria) {
		String sql = "INSERT INTO CATEGORIA (nome_categoria, descricao) VALUES ('" + categoria.getNome_categoria()
				+ "','" + categoria.getDescricao() + "')";
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = ConexaoSGBD.getConnection();
			stmt = conn.createStatement();
			stmt.execute(sql);
			System.out.println("Categoria salva com sucesso");
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public Categoria buscarPorCod(int cod) {
		String sql = "select cod_categoria, nome_categoria, descricao from categoria where cod_categoria =  "+cod;
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		Categoria categoria = new Categoria();
		try {
			con = ConexaoSGBD.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			
			if(rs.next()) {
				categoria.setCod_categoria(rs.getInt("cod_categoria"));
				categoria.setNome_categoria(rs.getString("nome_categoria"));
				categoria.setDescricao(rs.getString("descricao"));
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			fecharRecursos(con, stm, rs);
		}
		return categoria;
	}
	public List<Categoria> listarCategorias(){
		String sql = "select cod_categoria, nome_categoria, descricao from categoria";
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		List<Categoria> cats = new ArrayList<Categoria>();
		try {
			con = ConexaoSGBD.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			
			while(rs.next()) {
				Categoria c = new Categoria(rs.getInt("cod_categoria"), rs.getString("nome_categoria"), rs.getString("descricao"));
				cats.add(c);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			fecharRecursos(con, stm, rs);
		}
		return cats;
	}
	public void deletarCategoria(int cod) {
		String sql = "DELETE FROM Categoria WHERE cod_categoria = "+cod;
		Connection con = null;
		Statement stm = null;
		try {
			con = ConexaoSGBD.getConnection();
			stm = con.createStatement();
			int linhas = stm.executeUpdate(sql);
			if(linhas >0) {
				System.out.println("Categoria removida com sucesso");
			}else {
				System.out.println("Nenhuma categoria correspondente encontrada");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			fecharRecursos(con, stm, null);
		}
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
