package com.iesam.digitalLibrary.features.user.presentation;

import com.iesam.digitalLibrary.features.user.domain.User;

import java.util.List;
import java.util.Scanner;

public class UserView {

    public static void printUserMenu() {

        System.out.println("\n+-----------------------------+");
        System.out.println("|       Menu de Usuario       |");
        System.out.println("|-----------------------------|");
        System.out.println("|   [1] Alta a un usuario.    |");
        System.out.println("|   [2] Consultar usuario.    |");
        System.out.println("|   [3] Borrar un usuario.    |");
        System.out.println("|   [4] Lista de usuarios.    |");
        System.out.println("|   [5] Modificar usuario.    |");
        System.out.println("|   [0]      Salir            |");
        System.out.println("+-------------<~>-------------+");
    }

    public static void printInfoMessage(String message) {
        System.out.println("[INFO] " + message + ".");
    }

    public static void printWarningMessage(String message) {
        System.out.println("[WARNING] " + message + ".");
    }

    public static void printSuccessMessage(String message) {
        System.out.println("[OK] " + message + ".");
    }

    public static void printErrorMessage(String message) {
        System.err.println("[!] " + message);
    }

    public static void printCancelMessage(String message) {
        System.err.println("[CANCELED] "+ message + ".");
    }

    public static void printConsoleRequest(String message){
        System.out.print("-> "+ message + ": ");
    }

    public static void printUserInfo(User user){
        System.out.println("> INFORMACIÓN DE USUARIO:");
        System.out.println("----------------------------");
        System.out.println("- ID      : "+ user.id      );
        System.out.println("- DNI     : "+ user.dni     );
        System.out.println("- NOMBRE  : "+ user.name    );
        System.out.println("- APELLIDO: "+ user.surname );
        System.out.println("- EMAIL   : "+ user.email   );
        System.out.println("-----------------------------");

    }

    public static void printUsersInfo(List<User> userList){
        System.out.println("+------------------------------------------------------------------------+");
        System.out.println("| ID     | DNI          | Nombre        | Apellido       | Email         |");
        System.out.println("+------------------------------------------------------------------------+");

        for (User user : userList) {
            System.out.printf("| %06d | %-12s | %-13s | %-14s | %-13s |\n",
                    user.id, user.dni, user.name, user.surname, user.email);
        }

        System.out.println("+------------------------------------------------------------------------+");
        printInfoMessage("Usuarios en total: " + userList.size());
    }

    public static User captureUserDetails(Scanner sc, Integer id){
        System.out.print("-> Introduce el Nombre: ");
        String name = sc.nextLine();
        System.out.print("-> Introduce el Apellido: ");
        String surname = sc.nextLine();
        System.out.print("-> Introduce el DNI: ");
        String dni = sc.nextLine();
        System.out.print("-> Introduce el Email: ");
        String email = sc.nextLine();

        return new User(id, name, surname, dni, email);
    }

    public static void deleteConfirmation(User user){
        printInfoMessage("Recuperando usuario...");
        printUserInfo(user);
        printWarningMessage("Eliminar un usuario es una acción permanente.");
        System.out.print("- ¿Desea eliminar este usuario? (Y|N): ");
    }

    public static void updateConfirmation(User user) {
        printInfoMessage("Recuperando usuario...");
        printUserInfo(user);
        System.out.print("- ¿Desea modificar este usuario? (Y|N): ");
    }
}
