package menu;

import java.io.File;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.JSONUtils;

import java.util.Scanner;

public class MenuGerente {
private static final String CAMINHO_CLIENTES = "src/main/java/repositorio/Clientes.json";
        private static final String CAMINHO_FUNCIONARIOS = "src/repositorio/Funcionarios.json";
        private static final String CAMINHO_GERENTES = "src/repositorio/Gerente.json";

        public void menuGerente() {
            Scanner scanner = new Scanner(System.in);
            int opcao = 0;

            do {
                System.out.println("\n------ MENU PRINCIPAL ------");
                System.out.println("1 - Cadastrar pessoa");
                System.out.println("2 - Listar pessoas");
                System.out.println("3 - Editar pessoa");
                System.out.println("4 - Excluir pessoa");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");

                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer

                switch (opcao) {
                    case 1 -> cadastrarPessoa(scanner);
                    case 2 -> listarPessoas(scanner);
                    case 3 -> editarPessoa(scanner);
                    case 4 -> excluirPessoa(scanner);
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }

            } while (opcao != 0);

            scanner.close();
        }

        /**
         * Gera um novo ID único com base nos dados já existentes no arquivo JSON.
         *
         * @param caminho Caminho do arquivo JSON (clientes, funcionários ou gerentes)
         * @return Um novo ID inteiro (último ID + 1)
         **/
        private int gerarId(String caminho) {
            // Lê os dados existentes do arquivo JSON
            JSONArray dados = JSONUtils.lerArquivo(caminho);

            int maiorId = 0;

            // Percorre todos os objetos do JSON para encontrar o maior ID já usado
            for (int i = 0; i < dados.length(); i++) {
                JSONObject obj = dados.getJSONObject(i);
                int idAtual = obj.getInt("id");

                if (idAtual > maiorId) {
                    maiorId = idAtual;
                }
            }

            // Retorna o próximo ID disponível
            return maiorId + 1;
        }


        /**
         * Exibe as opções de tipo de pessoa e retorna o tipo escolhido como String.
         *
         * @param scanner Scanner para leitura do teclado
         * @return String com o tipo de pessoa ("Cliente", "Funcionario", "Gerente") ou null se inválido
         */
        private String escolherTipoPessoa(Scanner scanner) {
            System.out.println("Escolha o tipo de pessoa:");
            System.out.println("1 - Cliente");
            System.out.println("2 - Funcionário");
            System.out.println("3 - Gerente");
            System.out.print("Opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpa buffer

            return switch (opcao) {
                case 1 -> "Cliente";
                case 2 -> "Funcionario";
                case 3 -> "Gerente";
                default -> {
                    System.out.println("Opção inválida!");
                    yield null; // retornar um valor do case.
                }
            };
        }

        /**
         * Cadastra uma nova pessoa (Cliente, Funcionário ou Gerente) e salva no arquivo JSON correspondente.
         * Este metodo já cria o objeto e salva, sem precisar retornar nada.
         *
         * @param scanner Scanner para leitura do teclado
         */
        public void cadastrarPessoa(Scanner scanner) {

            // Pede ao usuário para escolher o tipo de pessoa
            String tipo = escolherTipoPessoa(scanner);
            if (tipo == null) return; // Opção inválida, retorna ao menu

            JSONObject pessoa = new JSONObject();
            String caminhoArquivo = "";

            // Campos comuns para todos os tipos
            System.out.print("Digite o nome: ");
            pessoa.put("nome", scanner.nextLine());

            System.out.print("Digite o CPF: ");
            pessoa.put("cpf", scanner.nextLine());

            System.out.print("Digite o telefone: ");
            pessoa.put("telefone", scanner.nextLine());

            System.out.print("Digite o email: ");
            pessoa.put("email", scanner.nextLine());

            System.out.print("Digite o endereço: ");
            pessoa.put("endereco", scanner.nextLine());

            // Campos específicos de cada tipo
            switch (tipo) {
                case "Cliente" -> {
                    caminhoArquivo = CAMINHO_CLIENTES;
                    pessoa.put("tipo", "Cliente");
                }
                case "Funcionario" -> {
                    caminhoArquivo = CAMINHO_FUNCIONARIOS;
                    pessoa.put("tipo", "Funcionario");

                    System.out.print("Digite o cargo: ");
                    pessoa.put("cargo", scanner.nextLine());

                    System.out.print("Digite o salário: ");
                    pessoa.put("salario", scanner.nextDouble());
                    scanner.nextLine();

                    System.out.print("Digite o login: ");
                    pessoa.put("login", scanner.nextLine());

                    System.out.print("Digite a senha (número): ");
                    pessoa.put("senha", scanner.nextInt());
                    scanner.nextLine();
                }
                case "Gerente" -> {
                    caminhoArquivo = CAMINHO_GERENTES;
                    pessoa.put("tipo", "Gerente");

                    System.out.print("Digite o cargo: ");
                    pessoa.put("cargo", scanner.nextLine());

                    System.out.print("Digite o salário: ");
                    pessoa.put("salario", scanner.nextDouble());
                    scanner.nextLine();

                    System.out.print("Digite o login: ");
                    pessoa.put("login", scanner.nextLine());

                    System.out.print("Digite a senha (número): ");
                    pessoa.put("senha", scanner.nextInt());
                    scanner.nextLine();
                }
            }

            // Gera ID automaticamente com base no arquivo
            pessoa.put("id", gerarId(caminhoArquivo));

            // Lê arquivo existente, adiciona a nova pessoa e salva
            JSONArray pessoas = JSONUtils.lerArquivo(caminhoArquivo);
            pessoas.put(pessoa);
            JSONUtils.salvarArquivo(caminhoArquivo, pessoas);

            System.out.println("\n " + tipo + " cadastrado com sucesso!");
        }


        /**
         * Lista todas as pessoas de um tipo específico (Cliente, Funcionário ou Gerente).
         * Lê o arquivo JSON correspondente e imprime os dados na tela.
         *
         * @param scanner Scanner para leitura do teclado
         */
        public void listarPessoas(Scanner scanner) {
            // Escolhe qual tipo de pessoa o usuário quer listar
            String tipo = escolherTipoPessoa(scanner);
            if (tipo == null) return; // opção inválida

            // Define o arquivo JSON correto
            String caminhoArquivo;
            if (tipo.equals("Cliente")) caminhoArquivo = CAMINHO_CLIENTES;
            else if (tipo.equals("Funcionario")) caminhoArquivo = CAMINHO_FUNCIONARIOS;
            else caminhoArquivo = CAMINHO_GERENTES;
               System.out.println(new File(CAMINHO_CLIENTES).getAbsolutePath());

            // Lê o arquivo JSON
            JSONArray pessoas = JSONUtils.lerArquivo(caminhoArquivo);

            if (pessoas.isEmpty()) {
                System.out.println("\nNenhuma pessoa encontrada!");
                return;
            }

            System.out.println("\n====== Lista de " + tipo + "s ======");

            // Percorre todos os registros do arquivo
            for (int i = 0; i < pessoas.length(); i++) {
                JSONObject pessoa = pessoas.getJSONObject(i);

                System.out.println("---------------------------");
                System.out.println("ID: " + pessoa.getInt("id"));
                System.out.println("Nome: " + pessoa.getString("nome"));
                System.out.println("CPF: " + pessoa.getString("cpf"));
                System.out.println("Telefone: " + pessoa.getString("telefone"));
                System.out.println("Email: " + pessoa.getString("email"));
                System.out.println("Endereço: " + pessoa.getString("endereco"));

                // Campos específicos
                if (tipo.equals("Funcionario") || tipo.equals("Gerente")) {
                    System.out.println("Cargo: " + pessoa.getString("cargo"));
                    System.out.println("Salário: " + pessoa.getDouble("salario"));
                    System.out.println("Login: " + pessoa.getString("login"));
                    System.out.println("Senha: " + pessoa.getInt("senha"));
                }
            }

            System.out.println("=============================");
        }

        /**
         * Exclui uma pessoa (Cliente, Funcionário ou Gerente) pelo ID.
         * Lê o arquivo JSON correspondente, remove o objeto selecionado e salva o arquivo atualizado.
         *
         * @param scanner Scanner para leitura do teclado
         */
        public void excluirPessoa(Scanner scanner) {
            // Escolhe o tipo de pessoa
            String tipo = escolherTipoPessoa(scanner);
            if (tipo == null) return;

            // Define o arquivo correto
            String caminhoArquivo;
            if (tipo.equals("Cliente")) caminhoArquivo = CAMINHO_CLIENTES;
            else if (tipo.equals("Funcionario")) caminhoArquivo = CAMINHO_FUNCIONARIOS;
            else caminhoArquivo = CAMINHO_GERENTES;

            // Lê o arquivo JSON
            JSONArray pessoas = JSONUtils.lerArquivo(caminhoArquivo);

            if (pessoas.isEmpty()) {
                System.out.println("\nNenhuma pessoa encontrada!");
                return;
            }

            // Mostra os IDs e nomes
            System.out.println("\n===== Lista de " + tipo + "s =====");
            for (int i = 0; i < pessoas.length(); i++) {
                JSONObject pessoa = pessoas.getJSONObject(i);
                System.out.println("ID: " + pessoa.getInt("id") + " | Nome: " + pessoa.getString("nome"));
            }

            // Pede o ID a excluir
            System.out.print("\nDigite o ID da pessoa que deseja excluir: ");
            int idExcluir = scanner.nextInt();
            scanner.nextLine();

            // Remove a pessoa pelo ID
            boolean encontrado = false;
            for (int i = 0; i < pessoas.length(); i++) {
                JSONObject pessoa = pessoas.getJSONObject(i);
                if (pessoa.getInt("id") == idExcluir) {
                    pessoas.remove(i);
                    encontrado = true;
                    break;
                }
            }

            if (encontrado) {
                JSONUtils.salvarArquivo(caminhoArquivo, pessoas);
                System.out.println("\nPessoa excluída com sucesso!");
            } else {
                System.out.println("\nID não encontrado!");
            }
        }


        /**
         * Edita os dados de uma pessoa (Cliente, Funcionário ou Gerente) pelo ID.
         * O usuário escolhe qual campo quer editar e pode editar vários campos antes de salvar.
         *
         * @param scanner Scanner para leitura do teclado
         */

        public void editarPessoa(Scanner scanner) {
            // Escolhe o tipo de pessoa
            String tipo = escolherTipoPessoa(scanner);
            if (tipo == null) return;

            // Define o arquivo correto
            String caminhoArquivo;
            if (tipo.equals("Cliente")) caminhoArquivo = CAMINHO_CLIENTES;
            else if (tipo.equals("Funcionario")) caminhoArquivo = CAMINHO_FUNCIONARIOS;
            else caminhoArquivo = CAMINHO_GERENTES;

            // Lê o arquivo JSON
            JSONArray pessoas = JSONUtils.lerArquivo(caminhoArquivo);

            if (pessoas.isEmpty()) {
                System.out.println("\nNenhuma pessoa encontrada!");
                return;
            }

            // Mostra os IDs e nomes para o usuário escolher
            System.out.println("\n===== Lista de " + tipo + "s =====");
            for (int i = 0; i < pessoas.length(); i++) {
                JSONObject pessoa = pessoas.getJSONObject(i);
                System.out.println("ID: " + pessoa.getInt("id") + " | Nome: " + pessoa.getString("nome"));
            }

            // Pede o ID a ser editado
            System.out.print("\nDigite o ID da pessoa que deseja editar: ");
            int idEditar = scanner.nextInt();
            scanner.nextLine();

            // Procura pelo objeto
            JSONObject pessoaEditar = null;
            for (int i = 0; i < pessoas.length(); i++) {
                JSONObject pessoa = pessoas.getJSONObject(i);
                if (pessoa.getInt("id") == idEditar) {
                    pessoaEditar = pessoa;
                    break;
                }
            }

            if (pessoaEditar == null) {
                System.out.println("\nID não encontrado!");
                return;
            }

            boolean continuar = true;

            while (continuar) {
                System.out.println("\nEscolha o campo que deseja editar:");
                System.out.println("1 - Nome");
                System.out.println("2 - CPF");
                System.out.println("3 - Telefone");
                System.out.println("4 - Email");
                System.out.println("5 - Endereço");

                if (tipo.equals("Funcionario") || tipo.equals("Gerente")) {
                    System.out.println("6 - Cargo");
                    System.out.println("7 - Salário");
                    System.out.println("8 - Login");
                    System.out.println("9 - Senha");
                }

                System.out.println("0 - Finalizar edição");
                System.out.print("Opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> {
                        System.out.print("Novo nome: ");
                        pessoaEditar.put("nome", scanner.nextLine());
                    }
                    case 2 -> {
                        System.out.print("Novo CPF: ");
                        pessoaEditar.put("cpf", scanner.nextLine());
                    }
                    case 3 -> {
                        System.out.print("Novo telefone: ");
                        pessoaEditar.put("telefone", scanner.nextLine());
                    }
                    case 4 -> {
                        System.out.print("Novo email: ");
                        pessoaEditar.put("email", scanner.nextLine());
                    }
                    case 5 -> {
                        System.out.print("Novo endereço: ");
                        pessoaEditar.put("endereco", scanner.nextLine());
                    }
                    case 6 -> {
                        if (!tipo.equals("Cliente")) {
                            System.out.print("Novo cargo: ");
                            pessoaEditar.put("cargo", scanner.nextLine());
                        } else System.out.println("Opção inválida!");
                    }
                    case 7 -> {
                        if (!tipo.equals("Cliente")) {
                            System.out.print("Novo salário: ");
                            pessoaEditar.put("salario", scanner.nextDouble());
                            scanner.nextLine();
                        } else System.out.println("Opção inválida!");
                    }
                    case 8 -> {
                        if (!tipo.equals("Cliente")) {
                            System.out.print("Novo login: ");
                            pessoaEditar.put("login", scanner.nextLine());
                        } else System.out.println("Opção inválida!");
                    }
                    case 9 -> {
                        if (!tipo.equals("Cliente")) {
                            System.out.print("Nova senha: ");
                            pessoaEditar.put("senha", scanner.nextInt());
                            scanner.nextLine();
                        } else System.out.println("Opção inválida!");
                    }
                    case 0 -> continuar = false;
                    default -> System.out.println("Opção inválida!");
                }

                // Pergunta se quer continuar editando
                if (continuar) {
                    System.out.print("\nDeseja editar outro campo? (s/n): ");
                    String resposta = scanner.nextLine();
                    if (!resposta.equalsIgnoreCase("s")) continuar = false;
                }
            }

            // Salva o arquivo atualizado
            JSONUtils.salvarArquivo(caminhoArquivo, pessoas);
            System.out.println("\nEdição finalizada com sucesso!");
        }

}
