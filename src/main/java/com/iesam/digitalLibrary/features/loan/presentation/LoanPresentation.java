package com.iesam.digitalLibrary.features.loan.presentation;


import com.iesam.digitalLibrary.features.digitalResources.domain.DigitalResource;
import com.iesam.digitalLibrary.features.digitalResources.domain.EBook;
import com.iesam.digitalLibrary.features.digitalResources.presentation.DigitalResourcePresentation;
import com.iesam.digitalLibrary.features.loan.data.LoanDataRepository;
import com.iesam.digitalLibrary.features.loan.data.local.LoanFileDataSource;
import com.iesam.digitalLibrary.features.loan.domain.*;
import com.iesam.digitalLibrary.features.user.domain.User;
import com.iesam.digitalLibrary.features.user.presentation.UserPresentation;


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
            System.out.println("| 4. Finalizar un Préstamo.      |");
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
                case 4:
                    endLoan();
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
        User user;

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


        Loan newLoan = new Loan(id, user, digitalResources,null);
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
            System.out.println("| [2] Préstamos finalizados.  |");
            System.out.println("| [0] Cancelar.               |");
            System.out.println("+-----------------------------+");
            System.out.print("> Ingresa la lista que deseas ver: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option){
                case 0:
                    System.out.println("<EXIT> Volviendo...");
                    break;
                case 1:
                    List<Loan> ongoingLoans = getOngoingLoans();
                    ongoingLoans.forEach(System.out::println);
                    System.out.println("- Prestamos en Proceso: " + ongoingLoans.size());
                    break;
                case 2:
                    List<Loan> completedLoans = getCompletedLoans();
                    completedLoans.forEach(System.out::println);
                    System.out.println("- Prestamos finalizados: " + completedLoans.size());
                    break;
                default:
                    System.err.println("<!> Opción no valida. Vuelva a intentarlo.");
                    break;
            }
        } while (option != 0 );
    }

    public static void endLoan(){
        Loan loan;
        do {
            System.out.print("\n-> Introduce el ID del préstamo a finalizar: ");
            int id = sc.nextInt();
            sc.nextLine();
            loan = getLoan(id);
            Date date = new Date();

            if (loan != null) {
                System.out.println("[INFO] Recuperado el préstamo: " + loan);
                System.out.print("- ¿Estas seguro de querer finalizar este préstamo hoy " + date + " ? [Y|N]: ");
                char choice = sc.next().charAt(0);
                choice = Character.toUpperCase(choice);
                switch (choice) {
                    case 'Y':
                        updateLoan(new Loan(loan.id, loan.user, loan.digitalResources, date));
                        System.out.println("[INFO] Se ha finalizado el préstamo: " + loan);
                        System.out.println("- Fecha de finalización :" + date);
                        break;

                    case 'N':
                        System.out.println("[Exit] Cancelado.");
                        break;
                    default:
                        System.err.println("[ERR] Opción no valida. Se ha cancelado por defecto.");
                        break;
                }
            } else {
                System.err.println("[!] No se ha encontrado un préstamo con ese ID. Vuelva a intentarlo");
            }

        } while (loan == null);

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
    public static void updateLoan(Loan model){
        UpdateLoanUseCase updateLoanUseCase = new UpdateLoanUseCase(dataRepository);
        updateLoanUseCase.execute(model);
    }
}
