package menu;

import modelo.Agendamento;
import modelo.Servico;
import modelo.Estacao;
import modelo.Cliente;
import modelo.Funcionario;
import gerenciadores.GerenciadorAgendamentos;
import utils.CRUDGenerico;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Menu responsável por todas as interações de Agendamentos no sistema.
 * Permite criar, listar, editar, remover e buscar agendamentos.
 */
public class MenuAgendamento {

    private Scanner sc = new Scanner(System.in);

    private CRUDGenerico<Cliente> crudClientes =
            new CRUDGenerico<>("src/main/java/repositorio/Clientes.json", Cliente.class);

    private CRUDGenerico<Funcionario> crudFuncionarios =
            new CRUDGenerico<>("src/main/java/repositorio/Funcionarios.json", Funcionario.class);

    private CRUDGenerico<Servico> crudServicos =
            new CRUDGenerico<>("src/main/java/repositorio/Servicos.json", Servico.class);

    private GerenciadorAgendamentos gerenciador = new GerenciadorAgendamentos();

    private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // =========================================================
    // MENU PRINCIPAL
    // =========================================================

    /**
     * Exibe o menu principal do sistema de agendamentos.
     */
    public void exibirMenu() {
        int opc;
        do {
            System.out.println("\n========= MENU AGENDAMENTO =========");
            System.out.println("1 - Criar Agendamento");
            System.out.println("2 - Listar Agendamentos");
            System.out.println("3 - Remover Agendamento");
            System.out.println("4 - Buscar Agendamento por ID");
            System.out.println("5 - Editar Agendamento");
            System.out.println("6 - Salvar Alterações");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opc = sc.nextInt(); sc.nextLine();

            switch (opc) {
                case 1 -> criarAgendamento();
                case 2 -> listarAgendamentos();
                case 3 -> removerAgendamento();
                case 4 -> buscarAgendamento();
                case 5 -> editarAgendamento();
                case 6 -> {
                    gerenciador.salvar();
                    System.out.println("✔ Alterações salvas!");
                }
                case 0 -> System.out.println("Saindo do menu...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opc != 0);
    }

    // =========================================================
    // CRIAR AGENDAMENTO
    // =========================================================

    /**
     * Cria um novo agendamento pedindo todas as informações ao usuário.
     */
    private void criarAgendamento() {

        Cliente cliente = selecionarCliente();
        if (cliente == null) return;

        Funcionario funcionario = selecionarFuncionario();
        if (funcionario == null) return;

        List<Servico> servicosEscolhidos = selecionarServicos();
        if (servicosEscolhidos.isEmpty()) return;

        System.out.print("Digite a data e hora do agendamento (dd/MM/yyyy HH:mm): ");

        LocalDateTime inicio;
        try {
            inicio = LocalDateTime.parse(sc.nextLine(), fmt);
        } catch (Exception e) {
            System.out.println("Data inválida!");
            return;
        }

        Estacao estacao = escolherEstacaoParaServicos(servicosEscolhidos);
        if (estacao == null) {
            System.out.println("Não há estação compatível com TODOS os serviços escolhidos.");
            return;
        }

        Agendamento novo = new Agendamento(0, cliente, funcionario, inicio, estacao);
        novo.setServicos(servicosEscolhidos);

        boolean sucesso = gerenciador.adicionar(novo);
        System.out.println(sucesso ? "Agendamento criado com sucesso!" : "Não foi possível criar. Verifique conflitos.");
    }

    // =========================================================
    // ESCOLHER ESTAÇÃO
    // =========================================================

    /**
     * Retorna uma estação compatível com TODOS os serviços escolhidos.
     */
    private Estacao escolherEstacaoParaServicos(List<Servico> servicos) {
        Set<Estacao> estacoesComuns = new HashSet<>(servicos.get(0).getEstacoesPossiveis());

        for (Servico s : servicos)
            estacoesComuns.retainAll(s.getEstacoesPossiveis());

        if (estacoesComuns.isEmpty()) return null;

        List<Estacao> lista = new ArrayList<>(estacoesComuns);

        System.out.println("\n--- Estações possíveis ---");
        for (int i = 0; i < lista.size(); i++)
            System.out.println((i + 1) + " - " + lista.get(i).name());

        System.out.print("Escolha a estação: ");
        int escolha = sc.nextInt(); sc.nextLine();

        return (escolha < 1 || escolha > lista.size()) ? null : lista.get(escolha - 1);
    }

    // =========================================================
    // LISTAR
    // =========================================================

    /**
     * Lista todos os agendamentos cadastrados.
     */
    private void listarAgendamentos() {
        List<Agendamento> lista = gerenciador.listar();

        if (lista.isEmpty()) {
            System.out.println("Nenhum agendamento encontrado.");
            return;
        }

        System.out.println("\n===== Lista de Agendamentos =====");
        lista.forEach(System.out::println);
    }

    // =========================================================
    // REMOVER
    // =========================================================

    /**
     * Remove um agendamento a partir do ID informado pelo usuário.
     */
    private void removerAgendamento() {

        System.out.print("Digite o ID do agendamento: ");
        int id = sc.nextInt(); sc.nextLine();

        Agendamento ag = gerenciador.buscarPorId(id);
        if (ag == null) {
            System.out.println("Agendamento não encontrado.");
            return;
        }

        gerenciador.remover(id);
        System.out.println("Agendamento removido!");
    }

    // =========================================================
    // BUSCAR
    // =========================================================

    /**
     * Busca e exibe um agendamento pelo ID.
     */
    private void buscarAgendamento() {

        System.out.print("Digite o ID do agendamento: ");
        int id = sc.nextInt(); sc.nextLine();

        Agendamento ag = gerenciador.buscarPorId(id);

        if (ag == null) {
            System.out.println("Agendamento não encontrado.");
            return;
        }

        System.out.println("\n===== Detalhes =====");
        System.out.println(ag);
    }

    // =========================================================
    // EDITAR
    // =========================================================

    /**
     * Edita um agendamento existente.
     */
    private void editarAgendamento() {

        System.out.print("Digite o ID do agendamento: ");
        int id = sc.nextInt(); sc.nextLine();

        Agendamento atual = gerenciador.buscarPorId(id);
        if (atual == null) {
            System.out.println("Agendamento não encontrado.");
            return;
        }

        // Criar cópia editável
        Agendamento novo = new Agendamento();
        novo.setId(atual.getId());
        novo.setCliente(atual.getCliente());
        novo.setFuncionario(atual.getFuncionario());
        novo.setServicos(new ArrayList<>(atual.getServicos()));
        novo.setHorarioInicio(atual.getHorarioInicio());
        novo.setEstacaoEscolhida(atual.getEstacaoEscolhida());

        // --- CLIENTE ---
        System.out.print("ID do cliente (" + atual.getCliente().getId() + "): ");
        String entradaCliente = sc.nextLine();
        if (!entradaCliente.isEmpty())
            novo.setCliente(selecionarClientePorId(Integer.parseInt(entradaCliente)));

        // --- FUNCIONÁRIO ---
        System.out.print("ID do funcionário (" + atual.getFuncionario().getId() + "): ");
        String entradaFuncionario = sc.nextLine();
        if (!entradaFuncionario.isEmpty())
            novo.setFuncionario(selecionarFuncionarioPorId(Integer.parseInt(entradaFuncionario)));

        // --- SERVIÇOS ---
        System.out.print("Deseja alterar os serviços? (s/n): ");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            List<Servico> servs = selecionarServicos();
            if (!servs.isEmpty()) {
                novo.setServicos(servs);
                novo.setEstacaoEscolhida(escolherEstacaoParaServicos(servs));
            }
        }

        // --- HORÁRIO ---
        System.out.print("Nova data/hora (" + atual.getHorarioInicio().format(fmt) + "): ");
        String entradaHorario = sc.nextLine();

        if (!entradaHorario.isEmpty())
            novo.setHorarioInicio(LocalDateTime.parse(entradaHorario, fmt));

        boolean ok = gerenciador.editar(id, novo);
        System.out.println(ok ? "Agendamento editado!" : "Não foi possível editar (conflito).");
    }

    // =========================================================
    // AUXILIARES
    // =========================================================

    private Cliente selecionarClientePorId(int id) {
        Cliente c = crudClientes.buscarPorId(id);
        if (c == null) System.out.println("Cliente não encontrado, mantendo o anterior.");
        return c != null ? c : crudClientes.listar().get(0);
    }

    private Funcionario selecionarFuncionarioPorId(int id) {
        Funcionario f = crudFuncionarios.buscarPorId(id);
        if (f == null) System.out.println("Funcionário não encontrado, mantendo o anterior.");
        return f != null ? f : crudFuncionarios.listar().get(0);
    }

    // =========================================================
    // SELEÇÃO DE CLIENTE
    // =========================================================

    private Cliente selecionarCliente() {
        List<Cliente> clientes = crudClientes.listar();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado!");
            return null;
        }

        clientes.forEach(c -> System.out.println(c.getId() + " - " + c.getNome()));

        System.out.print("Digite o ID do cliente: ");
        Cliente cliente = crudClientes.buscarPorId(sc.nextInt()); sc.nextLine();

        if (cliente == null) System.out.println("Cliente não encontrado!");
        return cliente;
    }

    // =========================================================
    // SELEÇÃO DE FUNCIONÁRIO
    // =========================================================

    private Funcionario selecionarFuncionario() {
        List<Funcionario> funcionarios = crudFuncionarios.listar();

        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado!");
            return null;
        }

        funcionarios.forEach(f -> System.out.println(f.getId() + " - " + f.getNome()));

        System.out.print("Digite o ID do funcionário: ");
        Funcionario funcionario = crudFuncionarios.buscarPorId(sc.nextInt()); sc.nextLine();

        if (funcionario == null) System.out.println("Funcionário não encontrado!");
        return funcionario;
    }

    // =========================================================
    // SELEÇÃO DE SERVIÇOS
    // =========================================================

    private List<Servico> selecionarServicos() {
        List<Servico> servicos = crudServicos.listar();

        if (servicos.isEmpty()) {
            System.out.println("Nenhum serviço cadastrado!");
            return Collections.emptyList();
        }

        servicos.forEach(s -> {
            String estacoes = s.getEstacoesPossiveis()
                    .stream()
                    .map(Estacao::name)
                    .collect(Collectors.joining(", "));

            System.out.println(s.getId() + " - " + s.getNome() + " (Estações: " + estacoes + ")");
        });

        List<Servico> escolhidos = new ArrayList<>();
        int id;

        do {
            System.out.print("Digite o ID do serviço (0 para finalizar): ");
            id = sc.nextInt(); sc.nextLine();

            if (id != 0) {
                Servico s = crudServicos.buscarPorId(id);

                if (s != null) {
                    if (!escolhidos.contains(s)) {
                        escolhidos.add(s);
                        System.out.println("Adicionado!");
                    } else {
                        System.out.println("Serviço já escolhido!");
                    }
                } else {
                    System.out.println("Serviço não encontrado!");
                }
            }

        } while (id != 0);

        return escolhidos;
    }

}
