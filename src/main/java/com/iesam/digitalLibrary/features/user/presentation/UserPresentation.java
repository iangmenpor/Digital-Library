package com.iesam.digitalLibrary.features.user.presentation;

import com.iesam.digitalLibrary.features.user.data.UserDataRepository;
import com.iesam.digitalLibrary.features.user.data.local.UserFileDataSource;
import com.iesam.digitalLibrary.features.user.data.local.UserMemLocalDataSource;
import com.iesam.digitalLibrary.features.user.domain.*;

import java.util.ArrayList;
import java.util.List;
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
            System.out.println("3. Borrar un usuario");
            System.out.println("4. Consultar lista de usuarios.");
            System.out.println("5. Volver a menú principal");
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
                    deleteUser();
                    break;
                case 4:
                    getUsers();
                case 5:
                    System.out.println("<Info> Volviendo a menu principal...");
                    break;
                default:
                    System.err.println("<!> Opción no valida. Vuelva a intentarlo");
            }
        } while (choice != 5);
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
    private static void deleteUser(){
        System.out.print("> Ingresa el ID del Usuario que deseas eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();
        GetUserUseCase getUserUseCase = new GetUserUseCase(dataRepository);
        User userDeleted = getUserUseCase.execute(id);
        if (userDeleted != null) {
            System.out.println("- El usuario que estás a punto de eliminar es: " + userDeleted);
            System.out.println("  <Warning> Eliminar a un usuario es una acción permanente.");
            System.out.print("- ¿Estás seguro de que deseas eliminar a este usuario? (Y|N): ");
            char conf = sc.next().charAt(0);
            char confirmation = Character.toUpperCase(conf);
            sc.nextLine(); //consumo
            switch (confirmation) {
                case 'Y':
                    DeleteUserUseCase deleteUserUseCase = new DeleteUserUseCase(dataRepository);
                    deleteUserUseCase.execute(id);
                    System.out.println("<OK> Usuario eliminado.");
                    break;
                case 'N':
                    System.out.println("<Info> Se ha cancelado la eliminación. Volviendo...");
                    break;
                default:
                    System.err.println("<!> Opción no válida. Por favor introduce Y o N.");
            }
        } else {
            System.err.println("<!> No se ha encontrado un Usuario con ese ID");
        }
    }
    private static List<User> getUsers(){
        GetUsersUseCase getUsersUseCase = new GetUsersUseCase(dataRepository);
        List<User> allUsers = new ArrayList<>(getUsersUseCase.execute());
        System.out.println("<Info> Imprimiendo listado de Usuarios...");
        if (!allUsers.isEmpty()){
            for (User x : allUsers) {
                System.out.println(x);
            }
            System.out.println("<OK> Usuarios en total: "+ allUsers.size());
        } else {
            System.out.println("<Info> No se han encontrado usuarios de alta.");
        }
        return allUsers;
    }
}

