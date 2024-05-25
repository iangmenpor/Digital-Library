package com.iesam.digitalLibrary.features.digitalResources.presentation;

import com.iesam.digitalLibrary.features.digitalResources.data.EbookDataRepository;
import com.iesam.digitalLibrary.features.digitalResources.data.local.EbookFileDataSource;

import java.util.Scanner;

public class DigitalResourcePresentation {


    private final Scanner sc;
    private final EbookPresentation ebookPresentation;

    public DigitalResourcePresentation(Scanner sc) {
        this.sc = sc;
        ebookPresentation = new EbookPresentation(sc, new EbookDataRepository(new EbookFileDataSource()));
    }

    public void displayDigitalProductsMenu() {
        int choice;
        do {
            DigitalResourceView.printDigitalResourcesMenu();
            DigitalResourceView.printConsoleRequest("Ingrese su elección");
            choice = getValidatedIntegerInput();

            switch (choice){
                case 1:
                    ebookPresentation.displayEbookMenu();
                    break;
                case 2:
                    DigitalResourceView.printInfoMessage("En futuras actualizaciones");
                    break;
                case 0:
                    DigitalResourceView.printInfoMessage("Volviendo al menú principal...");
                    break;
                default:
                    DigitalResourceView.printErrorMessage("Opción no válida. Por favor, vuelva a intentarlo.");
                    break;
            }
        } while (choice != 0);
    }

    private int getValidatedIntegerInput() {
        while (!sc.hasNextInt()) {
            EbookView.printErrorMessage("Por favor, ingresa un número válido.");
            sc.next(); // Consumir la entrada no válida
        }
        int input = sc.nextInt();
        sc.nextLine(); // Consumir la nueva línea
        return input;
    }
}
