package org.example; // Seu pacote

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// Usa a sua interface para garantir que a implementação de conexão está correta
public class ProdutoDAO {

    private ConexaoBD conexaoDB;

    public ProdutoDAO(ConexaoBD conexaoDB) {
        this.conexaoDB = conexaoDB;
    }

    // O método agora lança 'Exception' para se alinhar ao seu obterConexao()
    public List<Produto> obterListaDeProdutos() throws Exception {

        List<Produto> produtos = new ArrayList<>();

        // LIMIT 5 para cumprir o requisito de imprimir 5 produtos, ou remova para todos
        String sql = "SELECT id, nome, descricao, quantidade_estoque, preco FROM produtos LIMIT 5";

        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // 1. CHAMA O SEU MÉTODO DE CONEXÃO
            conexao = conexaoDB.obterConexao();

            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            // 2. Mapeamento dos resultados para o objeto Produto
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
            // 3. Bloco finally para fechar recursos
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();

            // CHAMA O SEU MÉTODO DE FECHAMENTO.
            // Não precisa de try-catch aqui porque fecharConexao() já trata internamente
            if (conexao != null) {
                conexaoDB.fecharConexao(conexao);
            }
        }

        return produtos;
    }
}