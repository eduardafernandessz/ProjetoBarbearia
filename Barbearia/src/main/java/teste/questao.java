package teste;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import modelo.Cliente;
import utils.CRUDGenerico;

public class questao{
    
    //questao 15
    public void questao15(){
    CRUDGenerico<Cliente> crudClientes =
        new CRUDGenerico<>("src/main/java/repositorio/clientes.json", Cliente.class);

    List<Cliente> lista = crudClientes.listar(); // lista carregada do JSON

    Iterator<Cliente> iterator = lista.iterator();


    System.out.println("=== LISTANDO CLIENTES COM ITERATOR ===");

    while (iterator.hasNext()) {          // enquanto existir próximo elemento
        Cliente c = iterator.next();      // pega o elemento atual e avança
        System.out.println("ID: " + c.getId() + " | Nome: " + c.getNome());
    }

    System.out.println("\n=== LISTANDO CLIENTES COM FOREACH ===");

    for (Cliente c : lista) {
        System.out.println("ID: " + c.getId() + " | Nome: " + c.getNome());
    }
    }
    
    //questao 16
    public void questao16() {

        CRUDGenerico<Cliente> crudClientes =
                new CRUDGenerico<>("src/main/java/repositorio/clientes.json", Cliente.class);

        List<Cliente> lista = crudClientes.listar();

        //Comparator para ordenar por Nome
        Comparator<Cliente> ordenarPorNome = (a, b) -> a.getNome().compareToIgnoreCase(b.getNome());

        //Comparator para ordenar por ID
        Comparator<Cliente> ordenarPorId = (a, b) -> Integer.compare(a.getId(), b.getId());

        System.out.println("\n=== ORDENANDO CLIENTES POR NOME ===");
        Collections.sort(lista, ordenarPorNome);

        for (Cliente c : lista) {
            System.out.println("ID: " + c.getId() + " | Nome: " + c.getNome());
        }

        System.out.println("\n=== ORDENANDO CLIENTES POR ID ===");
        Collections.sort(lista, ordenarPorId);

        for (Cliente c : lista) {
            System.out.println("ID: " + c.getId() + " | Nome: " + c.getNome());
        }
    }
    
   //questao 17
    public void questao17() {

    CRUDGenerico<Cliente> crudClientes =
        new CRUDGenerico<>("src/main/java/repositorio/clientes.json", Cliente.class);

    List<Cliente> lista = crudClientes.listar();

    Scanner sc = new Scanner(System.in);

    System.out.println("\n=== BUSCA DE CLIENTE POR NOME ===");
    System.out.print("Digite o nome que deseja buscar: ");
    String Busca = sc.nextLine(); 

    // Criar um CLIENTE só com o nome para ser usado como chave
    Cliente chaveBusca = new Cliente();
    chaveBusca.setNome(Busca);

    Comparator<Cliente> compNome =
            (a, b) -> a.getNome().compareToIgnoreCase(b.getNome());
    Collections.sort(lista, compNome);

    //FIND PERSONALIZADO
    System.out.println("\n=== TESTE DO METODO FIND ===");

    Cliente encontrado = find(lista, chaveBusca, compNome);

    if (encontrado != null)
        System.out.println("Encontrado com FIND: ID " + encontrado.getId() +
                           " | Nome: " + encontrado.getNome());
    else
        System.out.println("FIND: Não encontrado");


    //BINARY SEARCH
    System.out.println("\n=== TESTE DO BINARYSEARCH ===");

    int pos = Collections.binarySearch(lista, chaveBusca, compNome);

    if (pos >= 0)
        System.out.println("Encontrado com binarySearch: ID " +
                           lista.get(pos).getId() +
                           " | Nome: " + lista.get(pos).getNome());
    else
        System.out.println("binarySearch: Não encontrado");
    }


    //MÉTODO FIND USANDO ITERATOR + COMPARATOR 
    public Cliente find(List<Cliente> lista, Cliente alvo, Comparator<Cliente> comparator) {

    Iterator<Cliente> it = lista.iterator();

    while (it.hasNext()) {
        Cliente atual = it.next();
        if (comparator.compare(atual, alvo) == 0) {
            return atual;
        }
    }
    return null;
    }
}