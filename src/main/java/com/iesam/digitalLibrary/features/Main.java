package com.iesam.digitalLibrary.features;

import com.iesam.digitalLibrary.features.digitalResources.presentation.DigitalResourcePresentation;
import com.iesam.digitalLibrary.features.loan.presentation.LoanPresentation;
import com.iesam.digitalLibrary.features.user.presentation.UserPresentation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int choice;

        System.out.println("[SYSTEM] System status: OK");
        System.out.println("+++------------BIBLIOTECA VIRTUAL----------------+++");
        System.out.println("|                 BIENVENIDO/A :)                  |");
        System.out.println("+--------------------------------------------------+");
        do {
            System.out.println("\n+--------------------------------------------------+");
            System.out.println("|                  MENU PRINCIPAL                  |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|            [1]  Gestión de Usuarios.             |");
            System.out.println("|            [2]  Gestión de Recursos.             |");
            System.out.println("|            [3]  Gestión de Préstamos.            |");
            System.out.println("|            [0]        Cerrar.                    |");
            System.out.println("+------------------------<~>-----------------------+");
            System.out.print("-> Ingrese su elección: ");
            Scanner sc = new Scanner(System.in);
            choice=sc.nextInt();

            switch (choice){
                case 1:
                    UserPresentation UserMenu  = new UserPresentation(sc);
                    UserMenu.displayUserMenu();
                    break;
                case 2:
                    DigitalResourcePresentation digitalResourcePresentation = new DigitalResourcePresentation(sc);
                    digitalResourcePresentation.displayDigitalProductsMenu();
                    break;
                case 3:
                    LoanPresentation loanPresentation = new LoanPresentation(sc);
                    loanPresentation.displayLoanMenu();
                    break;
                case 0:
                    System.out.println("[INFO] Cerrando Programa...........");
                    System.out.println("[NOTE] Que tenga una buena día >u0.");
                    break;
                default:
                    System.err.println("[!] Opción no valida. Vuelva a intentarlo.");
            }
        } while (choice !=0);
    }
}