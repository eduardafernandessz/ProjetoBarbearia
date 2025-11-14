/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.barbearia;
import controle.GerenciarPessoas;
import java.util.Scanner;

/**
 *
 * @author KEL
 */
public class Barbearia {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        GerenciarPessoas gerenciar = new GerenciarPessoas(true);

        boolean rodando = true;

        while (rodando) {
            System.out.println("\n====== MENU PRINCIPAL ======");
            System.out.println("1 - Gerenciar Pessoas");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1: 
                    // Chama o menu interno de GerenciarPessoas
                    gerenciar.menuPrincipal();
                
                case 0: 
                    rodando = false;
                    System.out.println("Saindo do sistema...");
                
                default: System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}
