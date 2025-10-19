package org.example; // Seu pacote

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private ConexaoBD conexaoDB;

    public ProdutoDAO(ConexaoBD conexaoDB) {
        this.conexaoDB = conexaoDB;
    }
    public List<Produto> obterListaDeProdutos() throws Exception {

        List<Produto> produtos = new ArrayList<>();

        String sql = "SELECT id, nome, descricao, quantidade_estoque, preco FROM produtos LIMIT 5";

        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = conexaoDB.obterConexao();

            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();

                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setQuantidade(rs.getInt("quantidade_estoque"));
                produto.setPreco(rs.getDouble("preco"));

                produtos.add(produto);
            }

        } finally {
          
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conexao != null) {
                conexaoDB.fecharConexao(conexao);
            }
        }

        return produtos;
    }

}
