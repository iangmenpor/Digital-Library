package com.iesam.digitalLibrary.features.digitalResources.presentation;

import com.iesam.digitalLibrary.features.digitalResources.data.EbookDataRepository;
import com.iesam.digitalLibrary.features.digitalResources.data.local.AudioBookDataRepository;
import com.iesam.digitalLibrary.features.digitalResources.data.local.AudioBookFileDataSource;
import com.iesam.digitalLibrary.features.digitalResources.data.local.EbookFileDataSource;
import com.iesam.digitalLibrary.features.digitalResources.domain.DigitalResource;

import java.util.Scanner;

public class DigitalResourcePresentation {


    private final Scanner sc;
    private final EbookPresentation ebookPresentation;
    private final AudioBookPresentation audioBookPresentation;

    public DigitalResourcePresentation(Scanner sc) {
        this.sc = sc;
        ebookPresentation = new EbookPresentation(sc, new EbookDataRepository(new EbookFileDataSource()));
        audioBookPresentation = new AudioBookPresentation(sc, new AudioBookDataRepository(new AudioBookFileDataSource()));
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
                    audioBookPresentation.displayAudioBookMenu();
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
            DigitalResourceView.printErrorMessage("Por favor, ingresa un número válido.");
            sc.next(); // Consumir la entrada no válida
        }
        int input = sc.nextInt();
        sc.nextLine(); // Consumir la nueva línea
        return input;
    }

    public DigitalResource getDigitalResource(Integer id) {
        // Implementar un menú para elegir el tipo de producto digital
        DigitalResourceView.availableResources();
        DigitalResourceView.printConsoleRequest("Selecciona el tipo de producto digital");
        int choice = getValidatedIntegerInput();
        return switch (choice) {
            case 1 -> ebookPresentation.getEbook(id);
            case 2 -> audioBookPresentation.getAudioBook(id);
            default -> {
                DigitalResourceView.printErrorMessage("Tipo de producto digital no válido.");
                yield null;
            }
        };
    }
}
