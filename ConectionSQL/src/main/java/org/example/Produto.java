package org.example; // Seu pacote

public class Produto {
    private int id;
    private String nome;
    private String descricao;
    private int quantidade;
    private double preco;

    // Construtor Vazio (Boa prática para o DAO)
    public Produto() {
    }

    // Getters e Setters (Mantenha todos)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    // ... (outros getters e setters)

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    // Método para imprimir a lista
    @Override
    public String toString() {
        return "ID: " + id +
                " | Nome: " + nome +
                " | Preço: R$" + String.format("%.2f", preco) +
                " | Qtd: " + quantidade;
    }
}