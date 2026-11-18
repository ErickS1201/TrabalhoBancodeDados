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
		} catch (SQLException e) {
    e.printStackTrace();

			} finally {
    		fecharRecursos(con, stm, null);
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

		public void realizarCompra(Cliente cliente, long codProduto, int quantidade, String formaPagamento) {
		Produto produto = buscarPorCod(codProduto);

		if (produto == null) {
			System.out.println("Produto nao encontrado!");
			return;
		}
		if (quantidade <= 0 || produto.getQtd_estoque() < quantidade) {
			System.out.println("Quantidade invalida ou estoque insuficiente! Disponivel: " + produto.getQtd_estoque());
			return;
		}

		double valorTotal = produto.getPreco_produto() * quantidade;
		Connection con = null;
		PreparedStatement pstPag = null;
		PreparedStatement pstPed = null;
		PreparedStatement pstItem = null;
		ResultSet rs = null;

		try {
			con = ConexaoSGBD.getConnection();

			// 1. Cria o pagamento e pega o ID gerado
			String sqlPag = "INSERT INTO pagamento (forma_pagamento, status_pagamento) VALUES (?, 'PROCESSADO') RETURNING cod_pagamento";
			pstPag = con.prepareStatement(sqlPag);
			pstPag.setString(1, formaPagamento);
			rs = pstPag.executeQuery();
			int codPagamento = 0;
			if (rs.next()) {
				codPagamento = rs.getInt("cod_pagamento");
			}
			rs.close();

			// 2. Cria o pedido vinculado ao cliente e ao pagamento
			String sqlPed = "INSERT INTO pedido (cod_cliente, cod_pagamento, valor_pedido, status_pedido) VALUES (?, ?, ?, 'PAGO') RETURNING cod_pedido";
			pstPed = con.prepareStatement(sqlPed);
			pstPed.setInt(1, cliente.getCod_cliente());
			pstPed.setInt(2, codPagamento);
			pstPed.setDouble(3, valorTotal);
			rs = pstPed.executeQuery();
			int codPedido = 0;
			if (rs.next()) {
				codPedido = rs.getInt("cod_pedido");
			}

			// 3. Vincula o produto ao pedido na tabela pedido_produto
			String sqlItem = "INSERT INTO pedido_produto (cod_pedido, cod_produto, quantidade) VALUES (?, ?, ?)";
			pstItem = con.prepareStatement(sqlItem);
			pstItem.setInt(1, codPedido);
			pstItem.setLong(2, codProduto);
			pstItem.setInt(3, quantidade);
			pstItem.executeUpdate();

			// 4. Atualiza o estoque do produto
			produto.setQtd_estoque(produto.getQtd_estoque() - quantidade);
			atualizarProduto(codProduto, produto);

			System.out.println("Compra realizada com sucesso! Pedido #" + codPedido + " - Total: R$ " + valorTotal);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstPag != null) pstPag.close();
				if (pstPed != null) pstPed.close();
				if (pstItem != null) pstItem.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
		public void listarPedidosCliente(int codCliente) {
		String sql = "SELECT p.cod_pedido, p.data_pedido, p.valor_pedido, p.status_pedido, " +
					 "pag.forma_pagamento, pr.nome_produto, pp.quantidade " +
					 "FROM pedido p " +
					 "INNER JOIN pagamento pag ON p.cod_pagamento = pag.cod_pagamento " +
					 "INNER JOIN pedido_produto pp ON p.cod_pedido = pp.cod_pedido " +
					 "INNER JOIN produto pr ON pp.cod_produto = pr.cod_produto " +
					 "WHERE p.cod_cliente = ? ORDER BY p.cod_pedido DESC";

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			con = ConexaoSGBD.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, codCliente);
			rs = pst.executeQuery();

			boolean temPedido = false;
			System.out.println("\n--- Seus Pedidos ---");
			while (rs.next()) {
				temPedido = true;
				System.out.println(
					"Pedido #" + rs.getInt("cod_pedido") +
					" | Produto: " + rs.getString("nome_produto") +
					" (Qtd: " + rs.getInt("quantidade") + ")" +
					" | Total: R$ " + rs.getDouble("valor_pedido") +
					" | Pagamento: " + rs.getString("forma_pagamento") +
					" | Status: " + rs.getString("status_pedido")
				);
			}

			if (!temPedido) {
				System.out.println("Voce ainda nao realizou nenhum pedido.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharRecursos(con, pst, rs);
		}
	}

}
