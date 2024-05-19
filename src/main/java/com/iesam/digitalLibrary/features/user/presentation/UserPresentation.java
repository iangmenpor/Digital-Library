package com.iesam.digitalLibrary.features.user.presentation;

import com.iesam.digitalLibrary.features.user.data.UserDataRepository;
import com.iesam.digitalLibrary.features.user.data.local.UserMemLocalDataSource;
import com.iesam.digitalLibrary.features.user.domain.*;

import java.util.List;
import java.util.Scanner;

public class UserPresentation {

    private static Scanner sc;
    private static final UserDataRepository dataRepository =  new UserDataRepository(UserMemLocalDataSource.getInstance());

    public UserPresentation(Scanner sc) { UserPresentation.sc = sc; }

    public void displayUserMenu() {
        int choice;
        do {
            UserView.printUserMenu();
            UserView.printConsoleRequest("Ingrese su elección");
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
                    UserView.printUsersInfo(getUsers());
                    break;
                case 5:
                    updateUser();
                    break;
                case 0:
                    UserView.printInfoMessage("Volviendo a menu principal...");
                    break;
                default:
                    UserView.printErrorMessage("Opción no valida. Por favor, Intente de nuevo.");
            }
        } while (choice != 0);
    }

    private static void saveUser() {
        User newUser = UserView.captureUserDetails(sc, null);
        saveUser(newUser);

        UserView.printSuccessMessage("Usuario creado correctamente");
        sc.nextLine(); //Consumir nueva linea
    }

    public static void saveUser(User model){
        SaveUserUseCase saveUserUseCase = new SaveUserUseCase(dataRepository);
        saveUserUseCase.execute(model);
    }

    private static void getUser(){
        UserView.printConsoleRequest("Ingresa el ID del Usuario que deseas recuperar");
        int id = sc.nextInt();
        sc.nextLine(); // Consumir línea

        User user = getUser(id);

        if (user == null) {
            UserView.printErrorMessage("Usuario no encontrado");
        } else {
            UserView.printUserInfo(user);
        }
    }

    public static User getUser(Integer id){
        GetUserUseCase getUserUseCase = new GetUserUseCase(dataRepository);
        return getUserUseCase.execute(id);
    }

    private static void deleteUser(){
        UserView.printConsoleRequest("Ingresa el ID del Usuario que deseas eliminar");
        int id = sc.nextInt();
        sc.nextLine();

            User user = getUser(id);
            if (user == null) {
                UserView.printErrorMessage("Usuario no encontrado");
            } else {

                UserView.deleteConfirmation(user);

                char conf = sc.next().charAt(0);
                char confirmation = Character.toUpperCase(conf);
                sc.nextLine(); //consumo

                switch (confirmation) {
                    case 'Y':
                        deleteUser(id);
                        UserView.printSuccessMessage("Usuario eliminado");
                        break;
                    case 'N':
                        UserView.printCancelMessage("Eliminación cancelada");
                        break;
                    default:
                        UserView.printErrorMessage("Opción no valida. Orden cancelada por defecto");
                        break;
                }
            }
    }

    public static void deleteUser(Integer id) {
        DeleteUserUseCase deleteUserUseCase = new DeleteUserUseCase(dataRepository);
        deleteUserUseCase.execute(id);
    }

    public static List<User> getUsers(){
        GetUsersUseCase getUsersUseCase = new GetUsersUseCase(dataRepository);
        return getUsersUseCase.execute();
    }

    private static void updateUser(){
        UserView.printConsoleRequest("Introduce el id del usuario que deseas actualizar");
        int id = sc.nextInt();
        sc.nextLine();

            User oldUser = getUser(id);
            if (oldUser == null) {
                UserView.printErrorMessage("Usuario no encontrado");
            } else {
                UserView.updateConfirmation(oldUser);

                char conf = sc.next().charAt(0);
                char confirmation = Character.toUpperCase(conf);
                sc.nextLine();

                switch (confirmation) {
                    case 'Y':
                        updateUser(UserView.captureUserDetails(sc, id));
                        UserView.printSuccessMessage("Usuario Actualizado");
                        break;
                    case 'N':
                        UserView.printCancelMessage("Modificación cancelada");
                        break;
                    default:
                        UserView.printErrorMessage("Opción no valida. Orden cancelada por defecto");
                        break;
                }
            }
        sc.nextLine(); //consumo
    }

    public static void updateUser(User model){
        UpdateUserUseCase updateUserUseCase = new UpdateUserUseCase(dataRepository);
        updateUserUseCase.execute(model);
    }
}