package DAL;


    

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.ConexaoSGBD;
import modelos.Funcionario;

public class FuncionarioDAO {

    public void salvar(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (nome_funcionario, cpf, cargo, senha) VALUES (?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = ConexaoSGBD.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, funcionario.getNome_funcionario());
            pst.setString(2, funcionario.getCpf());
            pst.setString(3, funcionario.getCargo());
            pst.setString(4, funcionario.getSenha());
            pst.executeUpdate();
            System.out.println("Funcionario cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar funcionario (verifique se o CPF ja existe).");
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Funcionario login(String cpf, String senha) {
        String sql = "SELECT * FROM funcionario WHERE cpf = ? AND senha = ?";
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Funcionario func = null;

        try {
            con = ConexaoSGBD.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, cpf);
            pst.setString(2, senha);
            rs = pst.executeQuery();

            if (rs.next()) {
                func = new Funcionario(
                    rs.getInt("cod_funcionario"),
                    rs.getString("nome_funcionario"),
                    rs.getString("cpf"),
                    rs.getString("cargo"),
                    rs.getString("senha")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return func;
    }
}

