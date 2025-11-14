package com.mycompany.barbearia;
import java.util.Scanner;
import controle.GerenciarPessoas;

public class Barbearia {

public static void main(String[] args) {
        GerenciarPessoas gerenciar = new GerenciarPessoas();

        boolean rodando = true;

        while (rodando) {
            gerenciar.exibirMenu();
                    rodando = false;
                    System.out.println("\nSaindo do sistema...");
                }
        };
    
}