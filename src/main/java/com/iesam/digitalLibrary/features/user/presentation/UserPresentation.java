package com.iesam.digitalLibrary.features.user.presentation;

import com.iesam.digitalLibrary.features.user.data.UserDataRepository;
import com.iesam.digitalLibrary.features.user.data.local.UserFileDataSource;
import com.iesam.digitalLibrary.features.user.domain.SaveUserUseCase;
import com.iesam.digitalLibrary.features.user.domain.User;

import java.util.Scanner;

public class UserPresentation {

    private static Scanner sc;
    private static final UserDataRepository dataRepository =  new UserDataRepository(new UserFileDataSource());

    public UserPresentation(Scanner sc) { UserPresentation.sc = sc; }

    public void displayUserMenu() {
        int choice;
        do {
            System.out.println();
            System.out.println("+--------Menu de Usuario--------+");
            System.out.println("1. Crear usuario");
            System.out.println("2. Volver a menú principal");
            System.out.println("+-------------------------------+");
            System.out.print("> Ingrese su elección: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consumir la nueva línea después del entero

            switch (choice) {
                case 1:
                    saveUser();
                    break;
                case 2:
                    System.out.println("<Info> Volviendo a menu principal...");
                    break;
                default:
                    System.err.println("<!> Opción no valida. Vuelva a intentarlo");
            }
        } while (choice != 2);
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
}
