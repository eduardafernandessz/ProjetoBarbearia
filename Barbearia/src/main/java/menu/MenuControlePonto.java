package menu;

import gerenciadores.GerenciadorControlePonto;
import modelo.ControlePonto;
import modelo.Funcionario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * Menu de controle de ponto para funcionários.
 *
 * <p>Permite registrar entrada e saída, consultar registros de ponto
 * e salvar alterações no arquivo JSON.</p>
 */
public class MenuControlePonto {

    /** Gerenciador responsável por controlar os registros de ponto */
    private GerenciadorControlePonto gerenciador;

    /** Scanner para ler entradas do usuário */
    private Scanner sc = new Scanner(System.in);

    /** Construtor que inicializa o gerenciador de ponto */
    public MenuControlePonto() {
        gerenciador = new GerenciadorControlePonto();
    }

    /**
     * Exibe o menu principal de controle de ponto para o funcionário.
     *
     * <p>Permite escolher entre registrar entrada, registrar saída,
     * consultar ponto por data ou salvar alterações.</p>
     *
     * @param idFuncionario ID do funcionário logado
     */
    public void exibirMenu(int idFuncionario) {
        int opc;
        do {
            System.out.println("\n--- MENU CONTROLE DE PONTO ---");
            System.out.println("1 - Registrar Entrada");
            System.out.println("2 - Registrar Saída");
            System.out.println("3 - Consultar Ponto por Data");
            System.out.println("4 - Salvar alterações");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1 -> registrarEntrada(idFuncionario);
                case 2 -> registrarSaida(idFuncionario);
                case 3 -> consultarPonto(idFuncionario);
                case 4 -> {
                    gerenciador.salvar();
                    System.out.println("Alterações salvas com sucesso!");
                }
                case 0 -> System.out.println("Voltando ao menu anterior...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opc != 0);
    }

    /**
     * Registra a entrada do funcionário no dia atual.
     *
     * <p>Se ainda não existir registro de ponto para a data,
     * cria um novo objeto ControlePonto. Depois define o horário de entrada
     * como o horário atual e atualiza/adiciona no gerenciador.</p>
     *
     * @param idFuncionario ID do funcionário
     */
    private void registrarEntrada(int idFuncionario) {
        LocalDate data = LocalDate.now();
        LocalTime hora = LocalTime.now();

        // Busca o ponto do funcionário pela data
        ControlePonto ponto = gerenciador.buscarPorData(idFuncionario, data);

        if (ponto == null) {
            // Cria novo registro se não existir
            Funcionario funcionario = new Funcionario(); // Aqui você preencheria os dados reais
            ponto = new ControlePonto(idFuncionario, data, funcionario);
        }

        // Define horário de entrada
        ponto.setHorarioEntrada(hora);

        // Adiciona ou atualiza o ponto no gerenciador
        gerenciador.adicionarOuAtualizar(ponto);

        System.out.println("Entrada registrada às " + hora);
    }

    /**
     * Registra a saída do funcionário no dia atual.
     *
     * <p>Se não houver registro de entrada para o dia, exibe mensagem de erro.
     * Caso contrário, define o horário de saída como o horário atual
     * e atualiza o registro.</p>
     *
     * @param idFuncionario ID do funcionário
     */
    private void registrarSaida(int idFuncionario) {
        LocalDate data = LocalDate.now();
        LocalTime hora = LocalTime.now();

        // Busca o ponto do funcionário pela data
        ControlePonto ponto = gerenciador.buscarPorData(idFuncionario, data);

        if (ponto == null) {
            System.out.println("Erro: Não há registro de entrada para hoje.");
            return;
        }

        // Define horário de saída
        ponto.setHorarioSaida(hora);

        // Atualiza o registro no gerenciador
        gerenciador.adicionarOuAtualizar(ponto);

        System.out.println("Saída registrada às " + hora);
    }

    /**
     * Consulta o ponto de um funcionário em uma data específica.
     *
     * <p>O usuário digita a data no formato AAAA-MM-DD.
     * Se existir registro para essa data, exibe todos os detalhes do ponto.
     * Caso contrário, informa que não há ponto registrado.</p>
     *
     * @param idFuncionario ID do funcionário
     */
    private void consultarPonto(int idFuncionario) {
        System.out.print("Digite a data para consulta (AAAA-MM-DD): ");
        String dataStr = sc.nextLine();

        try {
            LocalDate data = LocalDate.parse(dataStr);

            // Busca ponto pelo ID do funcionário e data
            ControlePonto ponto = gerenciador.buscarPorData(idFuncionario, data);

            if (ponto == null) {
                System.out.println("Nenhum ponto registrado para essa data.");
            } else {
                // Chama o toString() do ControlePonto
                System.out.println(ponto);
            }
        } catch (Exception e) {
            System.out.println("Data inválida!");
        }
    }
}
