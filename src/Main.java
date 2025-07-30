import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Produto> produtos = new ArrayList<>();
        HashMap<String, Cliente> clientes = new HashMap<>();
        ArrayList<Produto> carrinho = new ArrayList<>();


        produtos.add(new Produto("Shampoo Hidratante", "Shampoos", 15.90));
        produtos.add(new Produto("Shampoo Anti-Caspa", "Shampoos", 18.50));
        produtos.add(new Produto("Sabonete Líquido Lavanda", "Sabonetes", 12.30));
        produtos.add(new Produto("Difusor Aromático Citrus", "Difusores Aromáticos", 8.90));
        produtos.add(new Produto("Sabonete Esfoliante", "Sabonetes", 25.00));
        produtos.add(new Produto("Perfume Floral 100ml", "Perfumes", 35.90));
        produtos.add(new Produto("Shampoo Nutritivo", "Shampoos", 22.70));
        produtos.add(new Produto("Perfume Masculino 50ml", "Perfumes", 89.90));
        produtos.add(new Produto("Sabonete Natural Mel", "Sabonetes", 14.50));
        produtos.add(new Produto("Difusor Aromático Relaxante", "Difusores Aromáticos", 28.90));

        System.out.println("\n()()()--- BEM-VINDO A LOJA VIVELIS ---()()()\n");


        System.out.print("Digite seu CPF, por favor: ");
        String cpf = sc.nextLine();

        Cliente cliente = clientes.get(cpf);
        if (cliente == null) {
            cliente = new Cliente(cpf);
            clientes.put(cpf, cliente);
        }

        int opcao = 1;
        while (opcao != 0) {
            System.out.println("\n    *** MENU ***");
            System.out.println("1 - Ver Produtos");
            System.out.println("2 - Filtrar Categoria");
            System.out.println("3 - Adicionar no Carrinho");
            System.out.println("4 - Ver Carrinho");
            System.out.println("5 - Comprar");
            System.out.println("6 - Suporte");
            System.out.println("7 - Sair");
            System.out.print("Opção escolhida: ");

            opcao = sc.nextInt();
            sc.nextLine();

            if (opcao == 1) {
                System.out.println("\n*** PRODUTOS ***");
                System.out.println("(Num - Descrição - Categoria - Valor)");
                for (int i = 0; i < produtos.size(); i++) {
                    System.out.println(i + " - " + produtos.get(i).nome + " | " + produtos.get(i).categoria + " | R$ " + produtos.get(i).preco);
                }
            } else if (opcao == 2) {
                System.out.println("\n*** CATEGORIAS ***");
                System.out.println("1 - Perfumes");
                System.out.println("2 - Sabonetes");
                System.out.println("3 - Shampoos");
                System.out.println("4 - Difusores Aromáticos");
                System.out.print("Escolha uma opção: ");

                int cat = sc.nextInt();
                String categoria = "";

                if (cat == 1) {
                    categoria = "Perfumes";
                } else if (cat == 2) {
                    categoria = "Sabonetes";
                } else if (cat == 3) {
                    categoria = "Shampoos";
                } else if (cat == 4) {
                    categoria = "Difusores Aromáticos";
                } else {
                    System.out.println("Categoria inválida!");
                    continue;
                }

                System.out.println("\n*** PRODUTOS - " + categoria + " - ***");
                for (int i = 0; i < produtos.size(); i++) {
                    if (produtos.get(i).categoria.equals(categoria)) {
                        System.out.println(i + " - " + produtos.get(i).nome + " | R$ " + produtos.get(i).preco);
                    }
                }
            } else if (opcao == 3) {
                System.out.print("Número do produto: ");
                int num = sc.nextInt();

                if (num >= 0 && num < produtos.size()) {
                    carrinho.add(produtos.get(num));
                    System.out.println("Produto adicionado!");
                } else {
                    System.out.println("Produto inválido!");
                }
            } else if (opcao == 4) {
                System.out.println("\n*** CARRINHO ***");
                if (carrinho.size() == 0) {
                    System.out.println("Carrinho vazio, adicione alguns produtos para continuar!");
                } else {
                    double total = 0;
                    for (int i = 0; i < carrinho.size(); i++) {
                        System.out.println(carrinho.get(i).nome + " | R$ " + carrinho.get(i).preco);
                        total = total + carrinho.get(i).preco;
                    }
                    System.out.println("TOTAL: R$ " + total);

                    if (total >= 29.90) {
                        System.out.println("FRETE GRÁTIS!");
                    } else {
                        double falta = 29.90 - total;
                        System.out.println("Falta R$ " + falta + " para frete grátis");
                    }
                }
            } else if (opcao == 5) {
                if (carrinho.size() == 0) {
                    System.out.println("Seu carrinho está vazio!");
                } else {
                    double total = 0;
                    for (int i = 0; i < carrinho.size(); i++) {
                        total = total + carrinho.get(i).preco;
                    }

                    System.out.println("\n=== FINALIZAR COMPRA ===");
                    System.out.println("Subtotal: R$ " + total);

                    if (total < 29.90) {
                        System.out.println("Frete: R$ 9.90");
                        total = total + 9.90;
                    } else {
                        System.out.println("Frete: GRÁTIS");
                    }

                    System.out.println("Pagamento:");
                    System.out.println("1 - Débito");
                    System.out.println("2 - Crédito");
                    System.out.println("3 - PIX (10% desconto)");
                    System.out.print("Escolha: ");

                    int pag = sc.nextInt();

                    if (pag == 1) {
                        System.out.println("Débito selecionado");
                    } else if (pag == 2) {
                        System.out.println("Crédito selecionado");
                    } else if (pag == 3) {
                        total = total * 0.9;
                        System.out.println("PIX selecionado - 10% desconto!");
                    } else {
                        System.out.println("Pagamento inválido!");
                        continue;
                    }

                    System.out.println("TOTAL FINAL: R$ " + total);
                    System.out.println("Compra realizada!");

                    cliente.compras = cliente.compras + 1;

                    if (cliente.compras % 10 == 0) {
                        System.out.println("PARABÉNS! Você ganhou um brinde!");
                    }

                    carrinho.clear();
                }
            } else if (opcao == 6) {
                System.out.println("\n=== SUPORTE ===");
                System.out.println("Atendimento: 8h às 18h de segunda a sexta-feira");
                System.out.println("Email: suportevivelis@vivelis.com");
                System.out.println("Nosso WhatsApp: (48) 99655-3129");
            } else if (opcao == 7) {
                System.out.println("Obrigado por visitar a Vivelis. Volte sempre!");
                break;
            } else {
                System.out.println("Opção inválida!");
            }
        }

        sc.close();
    }
}