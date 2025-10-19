package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionSQL implements ConexaoBD {

    private static final String URL = "jdbc:mysql://localhost:3306/ecommerce";
    private static final String USUARIO = "root";
    private static final String SENHA = "Gas96815077@"; // troque pela sua senha do MySQL Workbench

    @Override
    public Connection obterConexao() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            // REMOVIDO: System.out.println("✅ Conexão com o banco realizada com sucesso!");
            return conexao;
        } catch (ClassNotFoundException e) {
            throw new Exception("Driver JDBC do MySQL não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            throw new Exception("Erro ao conectar ao banco: " + e.getMessage());
        }
    }

    @Override
    public void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
                // REMOVIDO: System.out.println("🔒 Conexão fechada com sucesso!");
            } catch (SQLException e) {
                // Manter o erro, caso o fechamento falhe
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}