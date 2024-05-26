package com.iesam.digitalLibrary.features.loan.presentation;

import com.iesam.digitalLibrary.features.digitalResources.domain.DigitalResource;
import com.iesam.digitalLibrary.features.digitalResources.presentation.DigitalResourcePresentation;
import com.iesam.digitalLibrary.features.loan.domain.Loan;
import com.iesam.digitalLibrary.features.user.data.UserDataRepository;
import com.iesam.digitalLibrary.features.user.data.local.UserFileDataSource;
import com.iesam.digitalLibrary.features.user.domain.GetUserUseCase;
import com.iesam.digitalLibrary.features.user.domain.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class LoanView {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private static final String MENU = """

            +-----------------------------------------+
            |            Gestión de Préstamos         |
            |-----------------------------------------|
            |        [1] Dar de alta un Préstamo.     |
            |        [2] Dar de baja un Préstamo.     |
            |        [3] Consultar Préstamos.         |
            |        [4] Finalizar un Préstamo.       |
            |        [0]        Salir.                |
            +-------------------<~>-------------------+""";

    public static void printLoanMenu() {
        System.out.println(MENU);
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
        System.err.println("[CANCELED] " + message + ".");
    }

    public static void printConsoleRequest(String message) {
        System.out.print("-> " + message + ": ");
    }

    public static void printLoanDetails(Loan loan) {
        String resources = loan.digitalResources.stream()
                .map(DigitalResource::toString)
                .reduce("", (acc, resource) -> acc + "+ " + resource + "\n");
        String status = loan.isReturned() ? "Finalizado" : "En curso";
        System.out.printf("- ID     : %d\n- Usuario: %s\n- Recursos digitales:\n%s- Fecha de inicio : %s\n- Fecha de entrega: %s\n- Estado: %s\n",
                loan.id, loan.user, resources,
                dateFormat.format(loan.startDate), dateFormat.format(loan.endDate), status);
        System.out.println("+-----------------------------------------+");
    }

    public static Loan captureLoanDetails(Scanner sc) {
        User user = captureUser(sc);
        List<DigitalResource> digitalResources = captureDigitalResources(sc);
        return new Loan(user, digitalResources);
    }

    private static User captureUser(Scanner sc) {
        User user;
        do {
            printConsoleRequest("ID de usuario");
            int userId = getValidatedIntegerInput(sc);
            user = findUserById(userId);
            if (user == null) {
                printErrorMessage("El usuario no existe. Por favor, ingrese un ID válido");
            }
        } while (user == null);
        return user;
    }

    private static User findUserById(int id) {
        GetUserUseCase getUserUseCase = new GetUserUseCase(new UserDataRepository(new UserFileDataSource()));
        return getUserUseCase.execute(id);
    }

    private static List<DigitalResource> captureDigitalResources(Scanner sc) {
        List<DigitalResource> digitalResources = new ArrayList<>();
        boolean addMoreResources = true;

        while (addMoreResources) {
            printConsoleRequest("ID del recurso digital");
            int resourceId = getValidatedIntegerInput(sc);

            if (digitalResources.stream().anyMatch(resource -> resource.id.equals(resourceId))) {
                printWarningMessage("¡El recurso digital ya ha sido agregado!");
            } else {
                DigitalResource resource = new DigitalResourcePresentation(sc).getDigitalResource(resourceId);
                if (resource != null) {
                    digitalResources.add(resource);
                } else {
                    printErrorMessage("¡Recurso digital no encontrado!");
                }
            }

            System.out.print("¿Desea agregar otro recurso digital? (y/n): ");
            addMoreResources = sc.nextLine().equalsIgnoreCase("y");
        }

        return digitalResources;
    }

    public static void displayLoanListMenu() {
        System.out.println("\n+-----------------------------+");
        System.out.println("| [1] Préstamos en progreso.  |");
        System.out.println("| [2] Préstamos finalizados.  |");
        System.out.println("+-----------------------------+");
    }

    public static void printLoansInProgress(List<Loan> loans) {
        System.out.println("\n+-----------------------------------------+");
        System.out.println("|          Préstamos en Curso             |");
        System.out.println("+-----------------------------------------+");
        loans.stream().filter(loan -> !loan.isReturned()).forEach(LoanView::printLoanDetails);
    }

    public static void printCompletedLoans(List<Loan> loans) {
        System.out.println("\n+-----------------------------------------+");
        System.out.println("|           Préstamos Finalizados         |");
        System.out.println("+-----------------------------------------+");
        loans.stream().filter(Loan::isReturned).forEach(LoanView::printLoanDetails);
    }

    public static void deleteLoanConfirmation(Loan loan) {
        printInfoMessage("Recuperando Préstamo...");
        System.out.println(">       DETALLES SOBRE EL PRÉSTAMO:");
        System.out.println("+-----------------------------------------+");
        printLoanDetails(loan);
        printWarningMessage("Eliminar un Préstamo es una acción permanente.");
        System.out.print("- ¿Desea eliminar este Préstamo? (Y|N): ");
    }

    public static void finishLoanConfirmation(Loan loan) {
        printInfoMessage("Recuperando Préstamo...");
        System.out.println(">       DETALLES SOBRE EL PRÉSTAMO:");
        System.out.println("+-----------------------------------------+");
        printLoanDetails(loan);
        System.out.print("- ¿Desea finalizar este Préstamo hoy " + dateFormat.format(new Date()) + " ? (Y|N): ");
    }

    private static int getValidatedIntegerInput(Scanner sc) {
        while (!sc.hasNextInt()) {
            printErrorMessage("Por favor, ingresa un número válido.");
            sc.next(); // Consumir la entrada no válida
        }
        int input = sc.nextInt();
        sc.nextLine(); // Consumir la nueva línea
        return input;
    }
}
