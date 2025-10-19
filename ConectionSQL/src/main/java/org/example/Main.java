package org.example;

import java.util.List;
import java.util.Scanner; // Import necessário para ler a entrada do usuário

public class Main {
    public static void main(String[] args) {

        // Inicializa a conexão e o DAO
        ConectionSQL conexaoDB = new ConectionSQL();
        ProdutoDAO produtoDAO = new ProdutoDAO(conexaoDB);

        // Inicializa o Scanner
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        System.out.println("Sistema de E-commerce - Operações");

        // Loop principal do menu
        do {
            System.out.println("\n-------------------------------------");
            System.out.println("1 - Listar Produtos (e Fechar)");
            System.out.println("9 - Sair do Programa");
            System.out.print("Escolha uma opção: ");

            // Tenta ler a opção, tratando possíveis erros de entrada
            try {
                opcao = scanner.nextInt();

                // Estrutura switch para processar a opção
                switch (opcao) {
                    case 1:
                        System.out.println("\n--- Listando Produtos do Banco de Dados ---");

                        // Bloco de listagem de produtos (sua lógica anterior)
                        try {
                            List<Produto> listaDeProdutos = produtoDAO.obterListaDeProdutos();

                            if (listaDeProdutos.isEmpty()) {
                                System.out.println("Nenhum produto encontrado na base de dados.");
                            } else {
                                for (Produto produto : listaDeProdutos) {
                                    System.out.println(produto);
                                }
                            }
                        } catch (Exception e) {
                            System.err.println("ERRO: Ocorreu um problema ao buscar os produtos.");
                            e.printStackTrace();
                        }

                        System.out.println("\nListagem concluída. O programa será encerrado.");
                        opcao = 9; // Define a opção como 9 para sair do loop
                        break;

                    case 9:
                        System.out.println("Saindo do programa...");
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número.");
                scanner.next(); // Consome a entrada inválida para evitar loop infinito
                opcao = 0;
            }

        } while (opcao != 9); // Continua até que a opção 9 seja escolhida

        scanner.close(); // Fecha o scanner ao sair
        System.out.println("Processo finalizado.");
    }
}