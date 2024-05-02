package com.iesam.digitalLibrary.features.user.presentation;

import com.iesam.digitalLibrary.features.user.data.UserDataRepository;
import com.iesam.digitalLibrary.features.user.data.local.UserFileDataSource;
import com.iesam.digitalLibrary.features.user.data.local.UserMemLocalDataSource;
import com.iesam.digitalLibrary.features.user.domain.GetUserUseCase;
import com.iesam.digitalLibrary.features.user.domain.SaveUserUseCase;
import com.iesam.digitalLibrary.features.user.domain.User;

import java.util.Scanner;

public class UserPresentation {

    private static Scanner sc;
    private static final UserDataRepository dataRepository =  new UserDataRepository(UserMemLocalDataSource.getInstance());

    public UserPresentation(Scanner sc) { UserPresentation.sc = sc; }

    public void displayUserMenu() {
        int choice;
        do {
            System.out.println();
            System.out.println("+--------Menu de Usuario--------+");
            System.out.println("1. Crear usuario");
            System.out.println("2. Consultar un usuario");
            System.out.println("3. Volver a menú principal");
            System.out.println("+-------------------------------+");
            System.out.print("> Ingrese su elección: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consumir la nueva línea después del entero

            switch (choice) {
                case 1:
                    saveUser();
                    break;
                case 2:
                    getUser();
                    break;
                case 3:
                    System.out.println("<Info> Volviendo a menu principal...");
                    break;
                default:
                    System.err.println("<!> Opción no valida. Vuelva a intentarlo");
            }
        } while (choice != 3);
    }
    private static void saveUser() {
        SaveUserUseCase saveUserUseCase = new SaveUserUseCase(dataRepository);
        int id;
        String name, surname, dni, email;

        System.out.print("-> Introduce un código de identificación (id): ");
        id = sc.nextInt();
        sc.nextLine(); //Consumo
        System.out.print("-> Introduce el Nombre: ");
        name = sc.nextLine();
        System.out.print("-> Introduce el Apellido: ");
        surname = sc.nextLine();
        System.out.print("-> Introduce el DNI: ");
        dni = sc.nextLine();
        System.out.print("-> Introduce el Email: ");
        email = sc.nextLine();

        User newUser = new User (id,name,surname, dni, email);
        saveUserUseCase.execute(newUser);

        System.out.println("<Info> Se ha guardado un usuario.\n> "+ newUser);

        sc.nextLine(); //Consumir nueva linea
    }

    private static void getUser(){
        System.out.print("> Ingresa el ID del Usuario que deseas recuperar: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consumir línea
        GetUserUseCase getUserUseCase = new GetUserUseCase(dataRepository);
        User recoveredUser = getUserUseCase.execute(id);
        if (recoveredUser != null) {
            System.out.println("<Info> Recuperando información sobre el usuario con ID " + id);
            System.out.println("  > " + recoveredUser);
        } else {
            System.err.println("\n<!> No se ha encontrado un Usuario con ese ID");
        }
    }
}

