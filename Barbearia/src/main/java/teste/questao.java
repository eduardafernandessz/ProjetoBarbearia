package teste;

import java.util.Iterator;
import java.util.List;
import modelo.Cliente;
import utils.CRUDGenerico;

public class questao{
    
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
    
}
