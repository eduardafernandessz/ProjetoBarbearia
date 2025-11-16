package com.mycompany.barbearia;

import gerenciadores.GerenciadorAgendamentos;
import gerenciadores.GerenciadorPessoas;
import gerenciadores.GerenciadorProdutos;
import gerenciadores.GerenciadorVendas;
import menu.MenuPrincipal;
import menu.TelaLogin;
import utils.Login;
import modelo.Funcionario;
import modelo.Gerente;
import utils.CRUDGenerico;

public class Barbearia {

    public static void main(String[] args) {
        
    GerenciadorPessoas gp = new GerenciadorPessoas();
    GerenciadorProdutos gprod = new GerenciadorProdutos();
    GerenciadorAgendamentos ga = new GerenciadorAgendamentos();
    GerenciadorVendas gv = new GerenciadorVendas(gp, gprod);

        // CRUDs para login
        CRUDGenerico<Funcionario> crudFuncionarios =
                new CRUDGenerico<>("src/main/java/repositorio/funcionarios.json", Funcionario.class);

        CRUDGenerico<Gerente> crudGerentes =
                new CRUDGenerico<>("src/main/java/repositorio/gerente.json", Gerente.class);

        // LOGIN
        Login<Funcionario> loginFuncionario = new Login<>(crudFuncionarios);
        Login<Gerente> loginGerente = new Login<>(crudGerentes);

        Object usuarioLogado = null;

        while (usuarioLogado == null) {
            usuarioLogado = TelaLogin.iniciar(loginFuncionario, loginGerente);
        }
        // CHAMA MENU PRINCIPAL
    MenuPrincipal menu = new MenuPrincipal(gp, gprod, ga, gv);      
        menu.exibir(usuarioLogado);
    }
}
