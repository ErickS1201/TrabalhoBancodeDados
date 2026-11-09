package DAL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modelos.*;
public class ClienteDAO {
	public void salvar(Cliente cliente) {
		String sql = "INSERT INTO CLIENTE (nome_cliente, cpf_cliente, email_cliente, telefone_cliente) VALUES ('"+
					cliente.getNome()+"', '"+cliente.getCpf()+"', '"+cliente.getEmail()+"', '"+cliente.getTel()+"')";
			Connection con = null;
			Statement stm = null;
			try {
				con = ConexaoSGBD.getConnection();
				stm = con.createStatement();
				stm.execute(sql);
				System.out.println("Cliente salvo com sucesso");
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(stm != null) stm.close();
					if(con != null) con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
	}
	public Cliente buscarPorCpf(String cpf) {
		String sql = "SELECT * FROM CLIENTE WHERE cpf_cliente = '"+cpf+"'";
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		Cliente cliente = new Cliente();
		try {
			con = ConexaoSGBD.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			
			if(rs.next()) {
				cliente.setCod_cliente(rs.getInt("cod_cliente"));
				cliente.setNome(rs.getString("nome_cliente"));
				cliente.setCpf(rs.getString("cpf_cliente"));
				cliente.setEmail(rs.getString("email_cliente"));
				cliente.setTel(rs.getString("telefone_cliente"));
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			fecharRecursos(con, stm, rs);
		}
		
		if(cliente.getCod_cliente() == 0)System.out.println("Cliente nao existente");
		return cliente;
	
	}
	public Cliente buscarPorCod(int cod) {
		String sql = "SELECT * FROM CLIENTE WHERE cod_cliente = ?";

		Connection con = null;
		PreparedStatement stn = null;
		ResultSet rs = null;

		Cliente cliente = new Cliente();

		try {
			con = ConexaoSGBD.getConnection();

			stn = con.prepareStatement(sql);

			stn.setInt(1, cod);

		if (rs.next()) {
            cliente.setCod_cliente(rs.getInt("cod_cliente"));
            cliente.setNome(rs.getString("nome_cliente"));
            cliente.setCpf(rs.getString("cpf_cliente"));
            cliente.setEmail(rs.getString("email_cliente"));
            cliente.setTel(rs.getString("telefone_cliente"));
        }

    } catch (SQLException e) {
        e.printStackTrace();

    } finally {
        fecharRecursos(con, stn, rs);
    }

    if (cliente.getCod_cliente() == 0) {
        System.out.println("Cliente nao encontrado");
    }

    return cliente;

	
	}
	public void atualizarCliente(int cod, Cliente cliente) {
		Cliente cl = buscarPorCod(cod);
		if(cl.getCod_cliente() != 0) {
			String sql = "UPDATE cliente SET nome_cliente ='"+cliente.getNome()+"', cpf_cliente = '"+cliente.getCpf()+"', email_cliente = '"+cliente.getEmail()+"', telefone_cliente = '"+cliente.getTel()+"' WHERE cod_cliente = "+cod;
			Connection con = null;
			Statement stm = null;
			try {
				con = ConexaoSGBD.getConnection();
				stm = con.createStatement();
				stm.execute(sql);
				System.out.println("Cliente atualizado com sucesso");
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				fecharRecursos(con, stm, null);
			}
		}
	}
	
	public List <Cliente> listarClientes(){
		String sql = "SELECT * FROM CLIENTE ";
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		try {
			con = ConexaoSGBD.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			
			while(rs.next()) {
				Cliente c = new Cliente(rs.getInt("cod_cliente"),rs.getString("nome_cliente"),rs.getString("cpf_cliente"),rs.getString("email_cliente"), rs.getString("telefone_cliente"));
				clientes.add(c);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			fecharRecursos(con, stm, rs);
		}
		return clientes;
	}
	public void deletarCliente(int cod) {
		String sql = "DELETE FROM cliente where cod_cliente = +"+cod;
		Connection con = null;
		Statement stm = null;
		try {
			con= ConexaoSGBD .getConnection();
			stm = con.createStatement();
			int linhas = stm.executeUpdate(sql);
			if(linhas >0) {
				System.out.println("Cliente removido com sucesso");
			}else {
				System.out.println("Nenhum cliente com o ID: "+cod+" encontrado");
				
			}
		}catch(SQLException e) {
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
