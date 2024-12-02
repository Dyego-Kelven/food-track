package src;

import src.model.*;
import src.service.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Transacao pedido = new Pedido();
        Transacao venda = new Venda();
        Produto produto = new Produto();
        ItemPedido itemPedido = new ItemPedido();
        Estoque estoque = new Estoque();
        Fidelidade fidelidade = new Fidelidade();
        ClienteService cs = new ClienteService();
        PedidoService ps = new PedidoService();
        VendaService vs = new VendaService();
        EstoqueService es = new EstoqueService(estoque);
        FidelidadeService fs = new FidelidadeService();

        System.out.println("------------- Sistema de gereciamento de vendas -------------");

        int x,r;
        do {
            System.out.println("1 - Menu de Produto\n2 - Atualizar Estoque\n3 - Fazer Pedido\n4 - Consultar Pedidos\n5 - Finalizar Venda\n6 - Gerenciar Fidelidade\n7 - Relatório de Vendas");
            x = sc.nextInt();

            switch (x) {
                case 1:
                    int d, k;
                    do {
                        System.out.println("1 - Cadastrar Produto\n2 - Atualizar Quantidade de Produto\n3 - Ver Estoque\n4 - Apagar Produto\n5 - Ver Produtos Abaixo do Estoque Mínimo");
                        d = sc.nextInt();
                        switch (d) {
                            case 1:
                                System.out.println("Digite o nome do produto:");
                                String produtoCadastrar = sc.next();
                                System.out.println("Digite a quantidade inicial:");
                                int quantidadeInicial = sc.nextInt();
                                System.out.println("Digite a quantidade mínima:");
                                int quantidadeMinima = sc.nextInt();
                                es.adicionarIngrediente(produtoCadastrar, quantidadeInicial, quantidadeMinima);
                                break;

                            case 2:
                                System.out.println("Digite o nome do produto para atualizar:");
                                String produtoAtualizar = sc.next();
                                System.out.println("Digite a nova quantidade:");
                                int novaQuantidade = sc.nextInt();
                                es.atualizarQuantidade(produtoAtualizar, novaQuantidade);
                                break;

                            case 3:
                                es.exibirStatusEstoque();
                                break;

                            case 4:
                                System.out.println("Digite o nome do produto para remover:");
                                String produtoRemover = sc.next();
                                if (estoque.getEstoqueAtual().containsKey(produtoRemover)) {
                                    estoque.getEstoqueAtual().remove(produtoRemover);
                                    estoque.getEstoqueMinimo().remove(produtoRemover);
                                    System.out.println("Produto " + produtoRemover + " removido com sucesso.");
                                } else {
                                    System.out.println("Produto não encontrado no estoque.");
                                }
                                break;

                            case 5:
                                System.out.println("\n--- Produtos Abaixo do Estoque Mínimo ---");
                                for (String item : estoque.getEstoqueAtual().keySet()) {
                                    int quantidadeAtual = estoque.getEstoqueAtual().get(item);
                                    int quantidadeMin = estoque.getEstoqueMinimo().get(item);
                                    if (quantidadeAtual < quantidadeMin) {
                                        System.out.println("Produto: " + item);
                                        System.out.println("Quantidade Atual: " + quantidadeAtual);
                                        System.out.println("Quantidade Mínima: " + quantidadeMin);
                                    }
                                }
                                break;

                            default:
                                System.out.println("Opção inválida! Tente novamente.");
                        }
                        System.out.println("\nDigite 0 para exibir o menu novamente ou qualquer outro número para voltar ao menu principal.");
                        k = sc.nextInt();
                    } while (k == 0);
                    break;

                case 2:
                    System.out.println("Digite o nome do produto para atualizar:");
                    String produtoAtualizar = sc.next();
                    System.out.println("Digite a nova quantidade:");
                    int novaQuantidade = sc.nextInt();
                    es.atualizarQuantidade(produtoAtualizar, novaQuantidade);
                    break;

                case 3:
                    Pessoa funcionario = new Funcionario();
                    Pessoa cliente = new Cliente();

                    System.out.println("Digite o nome do cliente:");
                    ((Cliente)cliente).setNome(sc.next());
                    System.out.println("Digite o telefone do cliente");
                    ((Cliente)cliente).setNumeroTelefone(sc.nextLong());
                    cs.cadastrarCliente((Cliente)cliente);
                    System.out.println("Digite o nome do funcionário:");
                    ((Funcionario)funcionario).setNome(sc.next());
                    System.out.println(cs.listar());
                    break;
                case 4:
                    //Consultar Pedidos
                    break;
                case 5:
                    //Finalizar Venda
                    break;
                case 6:
                    //Gerenciar Fidelidade
                    break;
                case 7:
                    //Relatório de Vendas
                    break;
                default:
                    System.out.println("Opção Inválida!");
            }
            System.out.println("Para ver o menu novamente digite 0");
            r = sc.nextInt();
        }while (r==0);
    }
}
