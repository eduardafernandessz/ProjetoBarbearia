package gerenciadores;

import modelo.ControlePonto;
import utils.CRUDGenerico;

import java.time.LocalDate;
import java.util.List;

/**
 * Classe responsável por gerenciar os registros de ponto dos funcionários.
 *
 * <p>Permite listar, buscar, adicionar, atualizar e remover registros de ponto,
 * além de salvar as alterações no arquivo JSON.</p>
 */
public class GerenciadorControlePonto {

    /** CRUD genérico para armazenar os registros de ponto */
    private final CRUDGenerico<ControlePonto> crudPontos;

    /**
     * Construtor que inicializa o CRUD genérico com o caminho do arquivo JSON.
     * 
     * <p>O arquivo JSON será usado para persistir os registros de ponto,
     * garantindo que as informações sejam mantidas entre execuções do programa.</p>
     */
    public GerenciadorControlePonto() {
        crudPontos = new CRUDGenerico<>("src/main/java/repositorio/ControlePonto.json", ControlePonto.class);
    }

    /**
     * Lista todos os registros de ponto.
     *
     * @return Lista contendo todos os pontos cadastrados
     */
    public List<ControlePonto> listar() {
        return crudPontos.listar();
    }

    /**
     * Busca um ponto específico de um funcionário em uma determinada data.
     *
     * <p>Usa uma stream para percorrer todos os registros de ponto e
     * retorna o primeiro ponto que coincida com o ID do funcionário
     * e a data informada.</p>
     *
     * @param idFuncionario ID do funcionário
     * @param data Data do ponto
     * @return O ponto encontrado ou null se não existir nenhum registro
     */
    public ControlePonto buscarPorData(int idFuncionario, LocalDate data) {
        return crudPontos.listar()
                .stream()
                .filter(p -> p.getIdFuncionario() == idFuncionario && p.getData().equals(data))
                .findFirst() // Retorna o primeiro registro que atenda ao filtro
                .orElse(null); // Retorna null se não houver nenhum registro
    }

    /**
     * Adiciona um novo ponto ou atualiza um ponto existente.
     *
     * <p>Se já existir um ponto para o funcionário na mesma data,
     * atualiza os horários de entrada e saída. Caso contrário,
     * adiciona o novo ponto à lista de registros.</p>
     *
     * @param ponto Ponto a ser adicionado ou atualizado
     */
    public void adicionarOuAtualizar(ControlePonto ponto) {
        ControlePonto existente = buscarPorData(ponto.getIdFuncionario(), ponto.getData());
        if (existente != null) {
            // Atualiza horários do registro existente
            existente.setHorarioEntrada(ponto.getHorarioEntrada());
            existente.setHorarioSaida(ponto.getHorarioSaida());
        } else {
            // Adiciona novo registro
            crudPontos.adicionar(ponto);
        }
    }

    /**
     * Remove um ponto de um funcionário em uma determinada data.
     *
     * @param idFuncionario ID do funcionário
     * @param data Data do ponto
     * @return true se o ponto foi removido, false se não foi encontrado
     */
    public boolean remover(int idFuncionario, LocalDate data) {
        ControlePonto ponto = buscarPorData(idFuncionario, data);
        if (ponto != null) {
            crudPontos.remover(ponto);
            return true;
        }
        return false;
    }

    /**
     * Salva todos os pontos no arquivo JSON.
     *
     * <p>Chama o método salvar do CRUD genérico, que escreve todos
     * os registros de ponto na estrutura JSON para persistência.</p>
     */
    public void salvar() {
        crudPontos.salvar();
    }
}
