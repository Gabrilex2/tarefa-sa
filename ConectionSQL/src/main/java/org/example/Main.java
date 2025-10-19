package org.example;

import java.util.List;
import java.util.Scanner; 

public class Main {
    public static void main(String[] args) {

        ConectionSQL conexaoDB = new ConectionSQL();
        ProdutoDAO produtoDAO = new ProdutoDAO(conexaoDB);

        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        System.out.println("Sistema de E-commerce - Operações");

        do {
            System.out.println("\n-------------------------------------");
            System.out.println("1 - Listar Produtos (e Fechar)");
            System.out.println("9 - Sair do Programa");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        System.out.println("\n--- Listando Produtos do Banco de Dados ---");

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
                        opcao = 9; 
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

        } while (opcao != 9); 

        scanner.close();
        System.out.println("Processo finalizado.");
    }
}
