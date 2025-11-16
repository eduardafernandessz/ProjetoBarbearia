package gerenciadores;

import modelo.ControlePonto;
import utils.CRUDGenerico;

import java.time.LocalDate;
import java.util.List;

public class GerenciadorControlePonto {

    private final CRUDGenerico<ControlePonto> crudPontos;

    public GerenciadorControlePonto() {
        crudPontos = new CRUDGenerico<>("src/main/java/repositorio/ControlePonto.json", ControlePonto.class);
    }

    // ==============================
    // LISTAR TODOS OS PONTOS
    // ==============================
    public List<ControlePonto> listar() {
        return crudPontos.listar();
    }

    // ==============================
    // BUSCAR PONTO DE UM FUNCIONÁRIO POR DATA
    // ==============================
    public ControlePonto buscarPorData(int idFuncionario, LocalDate data) {
        return crudPontos.listar()
                .stream()
                .filter(p -> p.getIdFuncionario() == idFuncionario && p.getData().equals(data))
                .findFirst()
                .orElse(null);
    }

    // ==============================
    // ADICIONAR OU ATUALIZAR
    // ==============================
    public void adicionarOuAtualizar(ControlePonto ponto) {
        ControlePonto existente = buscarPorData(ponto.getIdFuncionario(), ponto.getData());
        if (existente != null) {
            // atualiza horários
            existente.setHorarioEntrada(ponto.getHorarioEntrada());
            existente.setHorarioSaida(ponto.getHorarioSaida());
        } else {
            crudPontos.adicionar(ponto);
        }
    }

    // ==============================
    // REMOVER POR ID FUNCIONÁRIO + DATA
    // ==============================
    public boolean remover(int idFuncionario, LocalDate data) {
        ControlePonto ponto = buscarPorData(idFuncionario, data);
        if (ponto != null) {
            crudPontos.remover(ponto);
            return true;
        }
        return false;
    }

    // ==============================
    // SALVAR NO JSON
    // ==============================
    public void salvar() {
        crudPontos.salvar();
    }
}
