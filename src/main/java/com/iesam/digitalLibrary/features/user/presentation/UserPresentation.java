package com.iesam.digitalLibrary.features.user.presentation;

import com.iesam.digitalLibrary.features.user.data.UserDataRepository;
import com.iesam.digitalLibrary.features.user.data.local.UserFileDataSource;
import com.iesam.digitalLibrary.features.user.domain.*;

import java.util.List;
import java.util.Scanner;

public class UserPresentation {

    private final Scanner sc;
    private final UserDataRepository dataRepository;

    public UserPresentation(Scanner sc) {
        this.sc = sc;
        this.dataRepository = new UserDataRepository(new UserFileDataSource());
    }

    public void displayUserMenu() {
        int choice;
        do {
            UserView.printUserMenu();
            UserView.printConsoleRequest("Ingrese su elección");
            choice = getValidatedIntegerInput();

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

    private int getValidatedIntegerInput() {
        while (!sc.hasNextInt()) {
            UserView.printErrorMessage("Por favor, ingresa un número válido.");
            sc.next(); // Consumir la entrada no válida
        }
        int input = sc.nextInt();
        sc.nextLine(); // Consumir la nueva línea
        return input;
    }

    private void saveUser() {
        User newUser = UserView.captureUserDetails(sc);
        saveUser(newUser);
        UserView.printUserInfo(newUser);
        UserView.printSuccessMessage("Usuario creado correctamente");
    }

    public void saveUser(User model) {
        try {
            SaveUserUseCase saveUserUseCase = new SaveUserUseCase(dataRepository);
            saveUserUseCase.execute(model);
        } catch (Exception e) {
            UserView.printErrorMessage("Error al guardar el usuario: " + e.getMessage());
        }
    }

    private void getUser() {
        UserView.printConsoleRequest("Ingresa el ID del Usuario que deseas recuperar");
        int id = getValidatedIntegerInput();
        User user = getUser(id);

        if (user == null) {
            UserView.printErrorMessage("Usuario no encontrado");
        } else {
            UserView.printUserInfo(user);
        }
    }

    public User getUser(Integer id) {
        GetUserUseCase getUserUseCase = new GetUserUseCase(dataRepository);
        return getUserUseCase.execute(id);
    }

    private void deleteUser() {
        UserView.printConsoleRequest("Ingresa el ID del Usuario que deseas eliminar");
        int id = getValidatedIntegerInput();

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

    public void deleteUser(Integer id) {
        try {
            DeleteUserUseCase deleteUserUseCase = new DeleteUserUseCase(dataRepository);
            deleteUserUseCase.execute(id);
        } catch (Exception e) {
            UserView.printErrorMessage("Error al eliminar el usuario: " + e.getMessage());
        }
    }

    public List<User> getUsers() {
        GetUsersUseCase getUsersUseCase = new GetUsersUseCase(dataRepository);
        return getUsersUseCase.execute();
    }

    private void updateUser() {
        UserView.printConsoleRequest("Introduce el id del usuario que deseas actualizar");
        int id = getValidatedIntegerInput();
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
    }

    public void updateUser(User model) {
        try {
            UpdateUserUseCase updateUserUseCase = new UpdateUserUseCase(dataRepository);
            updateUserUseCase.execute(model);
        } catch (Exception e) {
            UserView.printErrorMessage("Error al actualizar el usuario: " + e.getMessage());
        }
    }
}