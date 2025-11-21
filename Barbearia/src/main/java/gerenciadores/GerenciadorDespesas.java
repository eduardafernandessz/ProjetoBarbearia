package gerenciadores;

import modelo.Despesas;
import utils.CRUDGenerico;

import java.time.LocalDate;
import java.util.List;

    /**
     * Gerencia as despesas da barbearia.
     * <p>
     * Permite adicionar, editar, remover, listar e buscar despesas,
     * utilizando persistência em JSON através do CRUD genérico.
     * </p>
     */
public class GerenciadorDespesas {

    /** CRUD genérico para manipulação de despesas no arquivo JSON. */
    private final CRUDGenerico<Despesas> crud;

    /**
     * Construtor do gerenciador de despesas.
     * <p>
     * Inicializa o CRUD genérico apontando para o arquivo de despesas.
     * </p>
     */
    public GerenciadorDespesas() {
        crud = new CRUDGenerico<>("src/main/java/repositorio/Despesas.json", Despesas.class);
    }

    /**
     * Adiciona uma nova despesa.
     *
     * @param despesa Despesa a ser adicionada.
     */
    public void adicionarDespesa(Despesas despesa) {
        crud.adicionar(despesa);
    }

    /**
     * Retorna a lista de todas as despesas.
     *
     * @return Lista de despesas.
     */
    public List<Despesas> listarDespesas() {
        return crud.listar();
    }

    /**
     * Busca uma despesa pelo seu identificador.
     *
     * @param id Identificador da despesa.
     * @return Despesa encontrada ou null se não existir.
     */
    public Despesas buscarDespesaPorId(int id) {
        return crud.buscarPorId(id);
    }

    /**
     * Edita uma despesa existente.
     * <p>
     * Atualiza os campos da despesa original com os valores da despesa atualizada.
     * </p>
     *
     * @param id Identificador da despesa a ser editada.
     * @param atualizada Despesa com os dados atualizados.
     * @return true se a edição foi realizada; false se a despesa não foi encontrada.
     */
    public boolean editarDespesa(int id, Despesas atualizada) {
        Despesas original = crud.buscarPorId(id);
        if (original != null) {
            original.setDescricao(atualizada.getDescricao());
            original.setValor(atualizada.getValor());
            original.setData(atualizada.getData());
            original.setCategoria(atualizada.getCategoria());
            return true;
        }
        return false;
    }

    /**
     * Remove uma despesa pelo seu identificador.
     *
     * @param id Identificador da despesa a ser removida.
     * @return true se a despesa foi removida; false se não foi encontrada.
     */
    public boolean removerDespesa(int id) {
        Despesas d = crud.buscarPorId(id);
        if (d != null) {
            crud.remover(d);
            return true;
        }
        return false;
    }

    /**
     * Salva as despesas no arquivo JSON.
     */
    public void salvar() {
        crud.salvar();
    }
}
