package menu;

import gerenciadores.GerenciadorControlePonto;
import modelo.ControlePonto;
import modelo.Funcionario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class MenuControlePonto {

    private GerenciadorControlePonto gerenciador;
    private Scanner sc = new Scanner(System.in);

    public MenuControlePonto() {
        gerenciador = new GerenciadorControlePonto();
    }

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

    private void registrarEntrada(int idFuncionario) {
        LocalDate data = LocalDate.now();
        LocalTime hora = LocalTime.now();
        
        // Busca o ponto do funcionário pela data
        ControlePonto ponto = gerenciador.buscarPorData(idFuncionario, data);
        
        if (ponto == null) {
            // Se não existir, cria um novo registro de ponto
            Funcionario funcionario = new Funcionario(); // Aqui você deve preencher os dados do funcionário adequadamente
            ponto = new ControlePonto(idFuncionario, data, funcionario); // Assume que o funcionário já foi recuperado
        }
        
        ponto.setHorarioEntrada(hora);
        gerenciador.adicionarOuAtualizar(ponto); // Atualiza ou adiciona o ponto
        System.out.println("Entrada registrada às " + hora);
    }

    private void registrarSaida(int idFuncionario) {
        LocalDate data = LocalDate.now();
        LocalTime hora = LocalTime.now();
        
        // Busca o ponto do funcionário pela data
        ControlePonto ponto = gerenciador.buscarPorData(idFuncionario, data);
        
        if (ponto == null) {
            System.out.println("Erro: Não há registro de entrada para hoje.");
            return;
        }
        
        ponto.setHorarioSaida(hora);
        gerenciador.adicionarOuAtualizar(ponto); // Atualiza ou adiciona o ponto
        System.out.println("Saída registrada às " + hora);
    }

    private void consultarPonto(int idFuncionario) {
        System.out.print("Digite a data para consulta (AAAA-MM-DD): ");
        String dataStr = sc.nextLine();
        
        try {
            LocalDate data = LocalDate.parse(dataStr);
            ControlePonto ponto = gerenciador.buscarPorData(idFuncionario, data);
            
            if (ponto == null) {
                System.out.println("Nenhum ponto registrado para essa data.");
            } else {
                System.out.println(ponto);  // Aqui chama o toString() do ControlePonto
            }
        } catch (Exception e) {
            System.out.println("Data inválida!");
        }
    }
}  