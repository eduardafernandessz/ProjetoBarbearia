package teste;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import modelo.Cliente;
import utils.CRUDGenerico;

/**
 * Classe contendo métodos utilizados para resolver questões relacionadas
 * ao uso de Iterator, Comparator, ordenação, busca linear e busca binária
 * aplicadas a uma lista de clientes carregada a partir de um arquivo JSON.
 *
 * <p>As questões demonstram:</p>
 *     Leitura de clientes usando CRUDGenérico
 *     Iteração com Iterator e foreach
 *     Ordenação com Comparator
 *     Busca personalizada usando método FIND
 *     Busca binária usando Collections.binarySearch
 */
public class questao {
    


    /**
     * Questão 15: Demonstra duas formas de percorrer uma lista:
     * <p>
     *     1. {@link Iterator}: usando hasNext() e next()
     *     2. Foreach: forma simplificada
     * </p>
     * Os clientes são carregados de um arquivo JSON utilizando {@link CRUDGenerico}.
     */
    public void questao15() {
        CRUDGenerico<Cliente> crudClientes =
            new CRUDGenerico<>("src/main/java/repositorio/clientes.json", Cliente.class);

        List<Cliente> lista = crudClientes.listar();
        Iterator<Cliente> iterator = lista.iterator();

        System.out.println("=== LISTANDO CLIENTES COM ITERATOR ===");

        while (iterator.hasNext()) {
            Cliente c = iterator.next();
            System.out.println("ID: " + c.getId() + " | Nome: " + c.getNome());
        }

        System.out.println("\n=== LISTANDO CLIENTES COM FOREACH ===");

        for (Cliente c : lista) {
            System.out.println("ID: " + c.getId() + " | Nome: " + c.getNome());
        }
    }

    /**
     * Questão 16: Demonstra a utilização da interface {@link Comparator}
     * para ordenar uma lista de clientes, carregada do JSON.
     *
     * <p>O método realiza duas ordenações:</p>
     *     Ordenação por nome (ordem alfabética)
     *     Ordenação por ID
     *
     * É utilizado {@link Collections#sort(List, Comparator)}.
     */
    public void questao16() {

        CRUDGenerico<Cliente> crudClientes =
                new CRUDGenerico<>("src/main/java/repositorio/clientes.json", Cliente.class);

        List<Cliente> lista = crudClientes.listar();

        Comparator<Cliente> ordenarPorNome =
                (a, b) -> a.getNome().compareToIgnoreCase(b.getNome());

        Comparator<Cliente> ordenarPorId =
                (a, b) -> Integer.compare(a.getId(), b.getId());

        System.out.println("\n=== ORDENANDO CLIENTES POR NOME ===");

        Collections.sort(lista, ordenarPorNome);
        for (Cliente c : lista)
            System.out.println("ID: " + c.getId() + " | Nome: " + c.getNome());

        System.out.println("\n=== ORDENANDO CLIENTES POR ID ===");

        Collections.sort(lista, ordenarPorId);
        for (Cliente c : lista)
            System.out.println("ID: " + c.getId() + " | Nome: " + c.getNome());
    }

    /**
     * Questão 17: Demonstra como realizar buscas personalizadas em listas.
     *
     * <p>O método executa três passos:</p>
     *     Lê clientes do JSON
     *     Solicita ao usuário um nome para buscar
     *     Realiza duas buscas:
     *             Busca personalizada usando o método {@link #find(List, Cliente, Comparator)}
     *             Busca binária usando {@link Collections#binarySearch(List, Object, Comparator)}
     *
     * Antes das buscas a lista é ordenada pelo nome, requisito para a binarySearch.
     */
    public void questao17() {

        CRUDGenerico<Cliente> crudClientes =
            new CRUDGenerico<>("src/main/java/repositorio/clientes.json", Cliente.class);

        List<Cliente> lista = crudClientes.listar();
        Scanner sc = new Scanner(System.in);

        System.out.println("\n=== BUSCA DE CLIENTE POR NOME ===");
        System.out.print("Digite o nome que deseja buscar: ");
        String Busca = sc.nextLine();

        // Cliente usado como "chave de busca"
        Cliente chaveBusca = new Cliente();
        chaveBusca.setNome(Busca);

        Comparator<Cliente> compNome =
                (a, b) -> a.getNome().compareToIgnoreCase(b.getNome());

        Collections.sort(lista, compNome);

        System.out.println("\n=== TESTE DO METODO FIND ===");
        Cliente encontrado = find(lista, chaveBusca, compNome);

        if (encontrado != null)
            System.out.println("Encontrado com FIND: ID " + encontrado.getId()
                    + " | Nome: " + encontrado.getNome());
        else
            System.out.println("FIND: Não encontrado");

        System.out.println("\n=== TESTE DO BINARYSEARCH ===");
        int pos = Collections.binarySearch(lista, chaveBusca, compNome);

        if (pos >= 0)
            System.out.println("Encontrado com binarySearch: ID "
                    + lista.get(pos).getId() + " | Nome: " + lista.get(pos).getNome());
        else
            System.out.println("binarySearch: Não encontrado");
    }

    /**
     * Método de busca personalizada (equivalente a uma busca linear).
     *
     * <p>Ele percorre a lista usando um {@link Iterator} e utiliza um
     * {@link Comparator} para comparar o item atual com o alvo.</p>
     *
     * @param lista Lista onde será feita a busca
     * @param alvo Cliente contendo o nome a ser buscado
     * @param comparator Comparator utilizado para comparar os nomes
     * @return Cliente encontrado ou {@code null} caso não exista
     */
    public Cliente find(List<Cliente> lista, Cliente alvo, Comparator<Cliente> comparator) {

        Iterator<Cliente> it = lista.iterator();

        while (it.hasNext()) {
            Cliente atual = it.next();
            if (comparator.compare(atual, alvo) == 0)
                return atual;
        }

        return null;
    }
}
