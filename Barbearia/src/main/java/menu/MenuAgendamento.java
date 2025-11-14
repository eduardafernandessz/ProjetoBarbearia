package menu;

import servico.Agendamento;
import servico.GerenciadorAgendamentos;
import servico.Servico;
import servico.Estacao;
import usuarios.Cliente;
import usuarios.Funcionario;
import utils.CRUDGenerico;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class MenuAgendamento {

    private Scanner sc = new Scanner(System.in);

    private CRUDGenerico<Cliente> crudClientes =
            new CRUDGenerico<>("src/main/java/repositorio/clientes.json", Cliente.class);

    private CRUDGenerico<Funcionario> crudFuncionarios =
            new CRUDGenerico<>("src/main/java/repositorio/funcionarios.json", Funcionario.class);

    private CRUDGenerico<Servico> crudServicos =
            new CRUDGenerico<>("src/main/java/repositorio/servicos.json", Servico.class);

    private GerenciadorAgendamentos gerenciador = new GerenciadorAgendamentos();

    private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // --- MENU PRINCIPAL ---
    public void exibirMenu() {
        int opc;
        do {
            System.out.println("\n========= MENU AGENDAMENTO =========");
            System.out.println("1 - Criar Agendamento");
            System.out.println("2 - Listar Agendamentos");
            System.out.println("3 - Remover Agendamento");
            System.out.println("4 - Buscar Agendamento por ID");
            System.out.println("5 - Editar Agendamento");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1 -> criarAgendamento();
                case 2 -> listarAgendamentos();
                case 3 -> removerAgendamento();
                case 4 -> buscarAgendamento();
                case 5 -> editarAgendamento();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opc != 0);
    }

    // --- CRIAR AGENDAMENTO ---
    private void criarAgendamento() {
        Cliente cliente = selecionarCliente();
        if (cliente == null) return;

        Funcionario funcionario = selecionarFuncionario();
        if (funcionario == null) return;

        List<Servico> servicosEscolhidos = selecionarServicos();
        if (servicosEscolhidos == null || servicosEscolhidos.isEmpty()) return;

        System.out.print("Digite a data e hora do agendamento (dd/MM/yyyy HH:mm): ");
        LocalDateTime inicio;
        try {
            inicio = LocalDateTime.parse(sc.nextLine(), fmt);
        } catch (Exception e) {
            System.out.println(" Data inválida!");
            return;
        }

        Estacao estacao = escolherEstacaoParaServicos(servicosEscolhidos);
        if (estacao == null) {
            System.out.println(" Não há estação compatível com TODOS os serviços escolhidos.");
            return;
        }

        Agendamento novo = new Agendamento(0, cliente, funcionario, inicio, estacao);
        novo.setServicos(servicosEscolhidos);

        boolean deuCerto = gerenciador.adicionar(novo);
        System.out.println(deuCerto ? " Agendamento criado com sucesso!" : " Não foi possível criar. Verifique conflitos.");
    }

    // --- ESCOLHER ESTAÇÃO ---
    private Estacao escolherEstacaoParaServicos(List<Servico> servicos) {
        Set<Estacao> estacoesComuns = new HashSet<>(servicos.get(0).getEstacoesPossiveis());
        for (Servico s : servicos) {
            estacoesComuns.retainAll(s.getEstacoesPossiveis());
        }
        if (estacoesComuns.isEmpty()) return null;

        List<Estacao> lista = new ArrayList<>(estacoesComuns);
        System.out.println("\n--- Estações possíveis ---");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i).name());
        }

        System.out.print("Escolha o número da estação: ");
        int escolha = sc.nextInt(); sc.nextLine();
        if (escolha < 1 || escolha > lista.size()) return null;

        return lista.get(escolha - 1);
    }

    // --- LISTAR AGENDAMENTOS ---
    private void listarAgendamentos() {
        var lista = gerenciador.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum agendamento.");
            return;
        }
        System.out.println("\n===== Lista de Agendamentos =====");
        lista.forEach(this::imprimirResumo);
    }

    // --- REMOVER AGENDAMENTO ---
    private void removerAgendamento() {
        System.out.print("Digite o ID do agendamento: ");
        int id = sc.nextInt(); sc.nextLine();

        Agendamento ag = gerenciador.buscarPorId(id);
        if (ag == null) {
            System.out.println("⚠ Agendamento não encontrado.");
            return;
        }
        gerenciador.remover(id);
        System.out.println("✔ Agendamento removido!");
    }

    // --- BUSCAR AGENDAMENTO ---
    private void buscarAgendamento() {
        System.out.print("Digite o ID do agendamento: ");
        int id = sc.nextInt(); sc.nextLine();

        Agendamento ag = gerenciador.buscarPorId(id);
        if (ag == null) {
            System.out.println(" Agendamento não encontrado.");
            return;
        }

        System.out.println("\n===== Detalhes =====");
        imprimirResumo(ag);
    }

    // --- EDITAR AGENDAMENTO ---
    private void editarAgendamento() {
        System.out.print("Digite o ID do agendamento: ");
        int id = sc.nextInt(); sc.nextLine();

        Agendamento atual = gerenciador.buscarPorId(id);
        if (atual == null) {
            System.out.println(" Agendamento não encontrado.");
            return;
        }

        Agendamento novo = new Agendamento();
        novo.setId(atual.getId());
        novo.setCliente(atual.getCliente());
        novo.setFuncionario(atual.getFuncionario());
        novo.setServicos(new ArrayList<>(atual.getServicos()));
        novo.setHorarioInicio(atual.getHorarioInicio());
        novo.setEstacaoEscolhida(atual.getEstacaoEscolhida());

        System.out.print("Deseja trocar o cliente? (s/n): ");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            Cliente c = selecionarCliente();
            if (c != null) novo.setCliente(c);
        }

        System.out.print("Deseja trocar o funcionário? (s/n): ");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            Funcionario f = selecionarFuncionario();
            if (f != null) novo.setFuncionario(f);
        }

        System.out.print("Deseja alterar os serviços? (s/n): ");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            List<Servico> servs = selecionarServicos();
            if (servs != null && !servs.isEmpty()) {
                novo.setServicos(servs);
                novo.setEstacaoEscolhida(escolherEstacaoParaServicos(servs));
            }
        }

        System.out.print("Deseja alterar data/hora? (s/n): ");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            try {
                System.out.print("Digite a nova data e hora (dd/MM/yyyy HH:mm): ");
                novo.setHorarioInicio(LocalDateTime.parse(sc.nextLine(), fmt));
            } catch (Exception e) {
                System.out.println("Data inválida. Mantendo a original.");
            }
        }

        boolean ok = gerenciador.editar(id, novo);
        System.out.println(ok ? " Agendamento editado com sucesso!" : " Não foi possível editar. Verifique conflitos.");
    }

    // --- SELEÇÃO DE CLIENTE ---
    private Cliente selecionarCliente() {
        List<Cliente> clientes = crudClientes.listar();
        if (clientes.isEmpty()) {
            System.out.println(" Nenhum cliente cadastrado!");
            return null;
        }

        clientes.forEach(c -> System.out.println(c.getId() + " - " + c.getNome()));
        System.out.print("Digite o ID do cliente: ");
        Cliente cliente = crudClientes.buscarPorId(sc.nextInt()); sc.nextLine();

        if (cliente == null) System.out.println(" Cliente não encontrado!");
        return cliente;
    }

    // --- SELEÇÃO DE FUNCIONÁRIO ---
    private Funcionario selecionarFuncionario() {
        List<Funcionario> funcionarios = crudFuncionarios.listar();
        if (funcionarios.isEmpty()) {
            System.out.println(" Nenhum funcionário cadastrado!");
            return null;
        }

        funcionarios.forEach(f -> System.out.println(f.getId() + " - " + f.getNome()));
        System.out.print("Digite o ID do funcionário: ");
        Funcionario funcionario = crudFuncionarios.buscarPorId(sc.nextInt()); sc.nextLine();

        if (funcionario == null) System.out.println(" Funcionário não encontrado!");
        return funcionario;
    }

    // --- SELEÇÃO DE SERVIÇOS ---
    private List<Servico> selecionarServicos() {
        List<Servico> servicos = crudServicos.listar();
        if (servicos.isEmpty()) {
            System.out.println(" Nenhum serviço cadastrado!");
            return null;
        }

        servicos.forEach(s -> {
            String estacoes = s.getEstacoesPossiveis().stream()
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
                    escolhidos.add(s);
                    System.out.println(" Adicionado!");
                } else {
                    System.out.println(" Serviço não encontrado!");
                }
            }
        } while (id != 0);

        return escolhidos;
    }

    // --- IMPRIMIR RESUMO ---
    private void imprimirResumo(Agendamento a) {
        System.out.println("\nID: " + a.getId());
        System.out.println("Cliente: " + a.getCliente().getNome());
        System.out.println("Funcionário: " + a.getFuncionario().getNome());
        System.out.println("Estação escolhida: " + (a.getEstacaoEscolhida() != null ? a.getEstacaoEscolhida().name() : "Nenhuma"));
        System.out.println("Início: " + a.getHorarioInicio().format(fmt));
        System.out.println("Fim: " + a.getHorarioFim().format(fmt));
        System.out.println("Serviços:");
        a.getServicos().forEach(s -> {
            String estacoes = s.getEstacoesPossiveis().stream()
                    .map(Estacao::name)
                    .collect(Collectors.joining(", "));
            System.out.println(" - " + s.getNome() + " (Possíveis: " + estacoes + ")");
        });
        System.out.println("Total: R$ " + a.getPrecoTotal());
    }
}
