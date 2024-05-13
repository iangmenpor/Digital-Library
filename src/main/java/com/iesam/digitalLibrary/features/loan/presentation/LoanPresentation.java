package com.iesam.digitalLibrary.features.loan.presentation;


import com.iesam.digitalLibrary.features.digitalResources.domain.DigitalResource;
import com.iesam.digitalLibrary.features.digitalResources.domain.EBook;
import com.iesam.digitalLibrary.features.digitalResources.presentation.DigitalResourcePresentation;
import com.iesam.digitalLibrary.features.loan.data.LoanDataRepository;
import com.iesam.digitalLibrary.features.loan.data.local.LoanFileDataSource;
import com.iesam.digitalLibrary.features.loan.domain.*;
import com.iesam.digitalLibrary.features.user.domain.User;
import com.iesam.digitalLibrary.features.user.presentation.UserPresentation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class LoanPresentation {

    private static Scanner sc;
    private final static LoanDataRepository dataRepository = new LoanDataRepository(new LoanFileDataSource());

    public LoanPresentation(Scanner sc) {
        LoanPresentation.sc = sc;
    }

    public void displayLoanMenu() {
        int choice;
        do {
            System.out.println("\n+--------------------------------+");
            System.out.println("|        Menú de Prestamos.      |");
            System.out.println("+--------------------------------+");
            System.out.println("| 1. Dar de alta un Préstamo.    |");
            System.out.println("| 2. Dar de baja un Préstamo.    |");
            System.out.println("| 3. Consultar Préstamos.        |");
            System.out.println("| 0. Volver a Menú Principal.    |");
            System.out.println("+--------------------------------+");
            System.out.print("> Ingrese su elección:");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 0:
                    System.out.println("<Info> Volviendo a menú principal...");
                    break;
                case 1:
                    saveLoanPresentation();
                    break;
                case 2:
                    deleteLoanPresentation();
                    break;
                case 3:
                    getLoansPresentation();
                    break;
                default:
                    System.err.println("<!> Opción no válida. Vuelva a intentarlo.");
                    break;
            }
        } while (choice != 0);
    }

    private static void saveLoanPresentation() {
        System.out.print("-> ID de Préstamo: ");
        int id = sc.nextInt();
        sc.nextLine();

        ArrayList<DigitalResource> digitalResources = new ArrayList<>();
        User user = null;

        // Captura de usuario válido
        do {
            System.out.print("-> ID de usuario: ");
            int idUser = sc.nextInt();
            sc.nextLine();

            user = UserPresentation.getUser(idUser);

            if (user != null) {
                System.out.println("- Préstamo a nombre de: " + user);
            } else {
                System.err.println("<!> El usuario no existe.");
            }

        } while (user == null);

        // Agregar recursos digitales al préstamo
        int option;
        do {
            System.out.println("\n+--------------------------------------------+");
            System.out.println("|          Añadir Recursos Digitales         |");
            System.out.println("+--------------------------------------------+");
            DigitalResourcePresentation.availableResourcesMenu();
            System.out.print("> Ingresa tu elección: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 0:
                    System.out.println("<EXIT> Cancelado.");
                    break;
                case 1:
                    System.out.print("-> ID de Libro: ");
                    int idBook = sc.nextInt();
                    sc.nextLine();
                    EBook eBook = DigitalResourcePresentation.getEBook(idBook);
                    if (eBook != null) {
                        digitalResources.add(eBook);
                        System.out.println("<Info> Añadido: " + eBook);
                    } else {
                        System.err.println("<!> No se ha encontrado libro con ID = " + idBook);
                    }
                    break;
                case 2:
                    System.out.println("<Info> Esta opción aún no está disponible.");
                    break;
                default:
                    System.err.println("<!> Opción no válida. Por favor, intenta de nuevo.");
                    break;
            }
        } while (option != 0);

        // Captura de la fecha inicial del préstamo
        Date startDate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (;;) {
            try {
                System.out.print("-> Ingrese la fecha inicial del préstamo (YYYY-MM-DD): ");
                startDate = dateFormat.parse(sc.nextLine());
                break;
            } catch (ParseException e) {
                System.err.println("<!> Formato de fecha incorrecto. Use el formato año-mes-día.");
            }
        }

        // Captura de la devolución anticipada
        System.out.print("-> ¿Se ha devuelto antes de tiempo? (Y/N): ");
        boolean earlyReturn = sc.next().equalsIgnoreCase("Y");
        sc.nextLine();

        Date endDate = null;
        if (earlyReturn){
                try {
                    System.out.print("-> Ingrese la fecha de fin del préstamo (YYYY-MM-DD): ");
                    endDate = dateFormat.parse(sc.nextLine());
                } catch (ParseException e) {
                    System.err.println("<!> Formato de fecha incorrecto. Use el formato año-mes-día.");
                }
        }
        Loan newLoan = new Loan(id, user, digitalResources, startDate, endDate, earlyReturn);
        saveLoan(newLoan);
        System.out.println("> PRÉSTAMO:");
        System.out.println("> " + newLoan);
    }

    public static void saveLoan(Loan model) {
        SaveLoanUseCase saveLoanUseCase = new SaveLoanUseCase(dataRepository);
        saveLoanUseCase.execute(model);
    }


    private static void deleteLoanPresentation(){
        System.out.print("-> Introduce el ID del préstamo a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();
        Loan loan = getLoan(id);
        if (loan != null){
            deleteLoan(id);
            System.out.println("<OK> Préstamo Borrado.");
        } else {
            System.err.println("<!> No se ha encontrado préstamo con ese ID");
        }
    }

    private static void getLoansPresentation(){
        int option;
        do {
            System.out.println("\n+-----------------------------+");
            System.out.println("| [1] Préstamos en progreso.  |");
            System.out.println("| [0] Préstamos devueltos  .  |");
            System.out.println("+-----------------------------+");
            System.out.print(" -> Ingresa la lista que deseas ver: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option){
                case 0:
                    List<Loan> ongoingLoans = getOngoingLoans();
                    ongoingLoans.forEach(System.out::println);
                    break;
                case 1:
                    List<Loan> completedLoans = getCompletedLoans();
                    completedLoans.forEach(System.out::println);
                    break;
                default:
                    System.err.println("<!> Opción no valida. Vuelva a intentarlo.");
                    break;
            }
        } while (option != 0 );
    }

    public static void deleteLoan(Integer id){
        DeleteLoanUseCase deleteLoanUseCase = new DeleteLoanUseCase(dataRepository);
        deleteLoanUseCase.execute(id);
    }

    public static Loan getLoan(Integer id){
        GetLoanUseCase getLoanUseCase = new GetLoanUseCase(dataRepository);
        return getLoanUseCase.execute(id);
    }

    public static List<Loan> getOngoingLoans(){
        GetOngoingLoansUseCase getOngoingLoansUseCase = new GetOngoingLoansUseCase(dataRepository);
        return getOngoingLoansUseCase.execute();
    }

    public static List<Loan> getCompletedLoans(){
        GetCompletedLoansUseCase getCompletedLoansUseCase = new GetCompletedLoansUseCase(dataRepository);
        return  getCompletedLoansUseCase.execute();
    }
}