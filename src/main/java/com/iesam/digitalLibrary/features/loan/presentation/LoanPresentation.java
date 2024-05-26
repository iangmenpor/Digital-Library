package com.iesam.digitalLibrary.features.loan.presentation;


import com.iesam.digitalLibrary.features.loan.data.LoanDataRepository;
import com.iesam.digitalLibrary.features.loan.data.local.LoanFileDataSource;
import com.iesam.digitalLibrary.features.loan.domain.*;



import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class LoanPresentation {

    private final Scanner sc;
    private final static LoanDataRepository dataRepository = new LoanDataRepository(new LoanFileDataSource());
    public LoanPresentation(Scanner sc) {
        this.sc = sc;
    }

    public void displayLoanMenu() {
        int choice;
        do {
            LoanView.printLoanMenu();
            LoanView.printConsoleRequest("Ingrese su elección");
            choice = getValidatedIntegerInput();

            switch (choice) {
                case 1:
                    saveLoan();
                    break;
                case 2:
                    deleteLoan();
                    break;
                case 3:
                    getLoansPresentation();
                    break;
                case 4:
                    endLoan();
                    break;
                case 0:
                    LoanView.printInfoMessage("Volviendo a menú principal");
                    break;
                default:
                    LoanView.printErrorMessage("Opción no válida. Vuelva a intentarlo");
                    break;
            }
        } while (choice != 0);
    }

    private int getValidatedIntegerInput() {
        while (!sc.hasNextInt()) {
            LoanView.printErrorMessage("Por favor, ingresa un número válido.");
            sc.next(); // Consumir la entrada no válida
        }
        int input = sc.nextInt();
        sc.nextLine(); // Consumir la nueva línea
        return input;
    }

    private void saveLoan() {
        Loan newLoan = LoanView.captureLoanDetails(sc);
        saveLoan(newLoan);
        System.out.println(">       DETALLES SOBRE EL PRÉSTAMO:");
        System.out.println("+-----------------------------------------+");
        LoanView.printLoanDetails(newLoan);
        LoanView.printSuccessMessage("Nuevo Préstamo creado");
    }

    public void saveLoan(Loan model) {
        try {
            SaveLoanUseCase saveLoanUseCase = new SaveLoanUseCase(dataRepository);
            saveLoanUseCase.execute(model);
        } catch (Exception e){
            LoanView.printErrorMessage("Error al guardar el préstamo: "+ e.getMessage());
        }
    }


    private void deleteLoan(){
        LoanView.printConsoleRequest("Introduce el ID del préstamo a eliminar");
        int id = getValidatedIntegerInput();

        Loan loan = getLoan(id);
        if (loan == null){
            LoanView.printErrorMessage("Préstamo no encontrado");
        } else {
            LoanView.deleteLoanConfirmation(loan);
            char conf = sc.next().charAt(0);
            char confirmation = Character.toUpperCase(conf);
            sc.nextLine(); //consumo

            switch (confirmation){
                case 'Y':
                    deleteLoan(id);
                    LoanView.printSuccessMessage("Préstamo Eliminado");
                    break;
                case 'N':
                    LoanView.printCancelMessage("Eliminación cancelada");
                    break;
                default:
                    LoanView.printErrorMessage("Opción no valida. Orden cancelada por defecto");
                    break;
            }
        }
    }

    public void deleteLoan(Integer id){
        try {
            DeleteLoanUseCase deleteLoanUseCase = new DeleteLoanUseCase(dataRepository);
            deleteLoanUseCase.execute(id);
        } catch (Exception e){
            LoanView.printErrorMessage("Error al eliminar el préstamo: " + e.getMessage());
        }
    }

    private void getLoansPresentation(){
        int choice;
        LoanView.displayLoanListMenu();
        LoanView.printConsoleRequest("Ingrese su elección");

        choice = sc.nextInt();
        sc.nextLine(); // Consumir la nueva línea

        switch (choice){
            case 1:
                LoanView.printLoansInProgress(getOngoingLoans());
                break;
            case 2:
                LoanView.printCompletedLoans(getCompletedLoans());
                break;
            default:
                LoanView.printErrorMessage("Opción no valida. Orden cancelada por defecto");
                break;
        }
    }


    public List<Loan> getOngoingLoans(){
        GetOngoingLoansUseCase getOngoingLoansUseCase = new GetOngoingLoansUseCase(dataRepository);
        return getOngoingLoansUseCase.execute();
    }

    public List<Loan> getCompletedLoans(){
        GetCompletedLoansUseCase getCompletedLoansUseCase = new GetCompletedLoansUseCase(dataRepository);
        return  getCompletedLoansUseCase.execute();
    }

    public void endLoan(){
        Loan loan;
        do {
            System.out.print("\n-> Introduce el ID del préstamo a finalizar: ");
            int id = sc.nextInt();
            sc.nextLine();
            loan = getLoan(id);
            Date date = new Date();

            if (loan != null) {
                LoanView.finishLoanConfirmation(loan);
                char choice = sc.next().charAt(0);
                choice = Character.toUpperCase(choice);
                switch (choice) {
                    case 'Y':
                        updateLoan(new Loan(loan.id, loan.user, loan.digitalResources, date));
                        LoanView.printInfoMessage("Se ha finalizado el préstamo: ");
                        LoanView.printLoanDetails(loan);
                        LoanView.printInfoMessage("Fecha de finalización :" + date);
                        break;
                    case 'N':
                        LoanView.printCancelMessage("Finalización Cancelada");
                        break;
                    default:
                        LoanView.printErrorMessage("Opción no valida. Orden cancelada por defecto");
                        break;
                }
            } else {
                LoanView.printErrorMessage("No se ha encontrado un préstamo con ese ID. Vuelva a intentarlo");
            }

        } while (loan == null);

    }

    public Loan getLoan(Integer id){
        GetLoanUseCase getLoanUseCase = new GetLoanUseCase(dataRepository);
        return getLoanUseCase.execute(id);
    }
    public void updateLoan(Loan model){
        try {
            UpdateLoanUseCase updateLoanUseCase = new UpdateLoanUseCase(dataRepository);
            updateLoanUseCase.execute(model);
        } catch (Exception e){
            LoanView.printErrorMessage("Error al modificar los datos del préstamo: "+ e.getMessage() );
        }
    }
}

