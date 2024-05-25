package com.iesam.digitalLibrary.features.digitalResources.presentation;

import com.iesam.digitalLibrary.features.digitalResources.domain.*;


import java.util.List;
import java.util.Scanner;

public class EbookPresentation{

    private final Scanner sc;
    private final DigitalResourceRepository<EBook> eBookDigitalResourceRepository;

    public EbookPresentation(Scanner sc, DigitalResourceRepository<EBook> eBookDigitalResourceRepository) {
        this.sc = sc;
        this.eBookDigitalResourceRepository = eBookDigitalResourceRepository;
    }

    public void displayEbookMenu() {
        int choice;
        do {
            EbookView.printEbookMenu();
            EbookView.printConsoleRequest("Ingrese su elección");
            choice = getValidatedIntegerInput();

            switch (choice) {
                case 1:
                    saveEbook();
                    break;
                case 2:
                    getEbook();
                    break;
                case 3:
                    deleteEbook();
                    break;
                case 4:
                    EbookView.printEBooksInfo(getAllEbooks());
                    break;
                case 5:
                    updateEbook();
                    break;
                case 0:
                    EbookView.printInfoMessage("Volviendo al menú de recursos...");
                    break;
                default:
                    EbookView.printErrorMessage("Opción no válida. Por favor, vuelva a intentarlo.");
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
    private void saveEbook() {
        EBook newEbook = EbookView.captureEbookDetails(sc);
        saveEbook(newEbook);
        EbookView.printEbookInfo(newEbook);
        EbookView.printSuccessMessage("Nuevo Ebook catalogado correctamente");
    }
    public void saveEbook(EBook model){
        try {
            SaveDigitalResourceUseCase<EBook> saveDigitalResourceUseCase = new SaveDigitalResourceUseCase<>(eBookDigitalResourceRepository);
            saveDigitalResourceUseCase.execute(model);
        } catch (Exception e){
            EbookView.printErrorMessage("Error al guardar el Ebook: "+ e.getMessage());
        }
    }
    private void getEbook() {
        EbookView.printConsoleRequest("Ingresa el ID del Ebook que deseas recuperar");
        int id = getValidatedIntegerInput();
        EBook eBook = getEbook(id);
        if (eBook == null ){
            EbookView.printErrorMessage("Ebook no encontrado");
        } else {
            EbookView.printEbookInfo(eBook);
        }
    }
    public EBook getEbook(Integer id){
        GetDigitalResourceUseCase<EBook> getDigitalResourceUseCase = new GetDigitalResourceUseCase<>(eBookDigitalResourceRepository);
        return getDigitalResourceUseCase.execute(id);
    }
    private void deleteEbook() {
        EbookView.printConsoleRequest("Ingresa el ID del Ebook que deseas eliminar");
        int id = getValidatedIntegerInput();

        EBook eBook = getEbook(id);
        if (eBook == null){
            EbookView.printErrorMessage("Ebook no encontrado");
        } else {
            EbookView.deleteEbookConfirmation(eBook);
            char conf = sc.next().charAt(0);
            char confirmation = Character.toUpperCase(conf);
            sc.nextLine(); //consumo

            switch (confirmation) {
                case 'Y':
                    deleteEbook(id);
                    EbookView.printSuccessMessage("Ebook eliminado");
                    break;
                case 'N':
                    EbookView.printCancelMessage("Eliminación cancelada");
                    break;
                default:
                    EbookView.printErrorMessage("Opción no valida. Orden cancelada por defecto");
                    break;
            }
        }
    }
    public void deleteEbook(Integer id){
        try {
            DeleteDigitalResourceUseCase<EBook> deleteDigitalResourceUseCase = new DeleteDigitalResourceUseCase<>(eBookDigitalResourceRepository);
            deleteDigitalResourceUseCase.execute(id);
        } catch (Exception e){
            EbookView.printErrorMessage("Error al eliminar el Ebook: "+ e.getMessage());
        }
    }
    public List<EBook> getAllEbooks() {
        GetDigitalResourcesUseCase<EBook> getDigitalResourcesUseCase = new GetDigitalResourcesUseCase<>(eBookDigitalResourceRepository);
        return getDigitalResourcesUseCase.execute();
    }
    private void updateEbook() {
        EbookView.printConsoleRequest("Ingresa el ID del Ebook que deseas modificar");
        int id = getValidatedIntegerInput();

        EBook eBook = getEbook(id);
        if (eBook == null){
            EbookView.printErrorMessage("Ebook no encontrado");
        } else {
            EbookView.updateEbookConfirmation(eBook);
            char conf = sc.next().charAt(0);
            char confirmation = Character.toUpperCase(conf);
            sc.nextLine(); //consumo

            switch (confirmation) {
                case 'Y':
                    updateEbook(EbookView.captureEbookDetails(sc, id));
                    EbookView.printSuccessMessage("Ebook Actualizado");
                    break;
                case 'N':
                    EbookView.printCancelMessage("Modificación cancelada");
                    break;
                default:
                    EbookView.printErrorMessage("Opción no valida. Orden cancelada por defecto");
                    break;
            }
        }
    }
    public void updateEbook(EBook model){
        try {
            UpdateDigitalResourceUseCase<EBook> updateDigitalResourceUseCase = new UpdateDigitalResourceUseCase<>(eBookDigitalResourceRepository);
            updateDigitalResourceUseCase.execute(model);
        } catch (Exception e){
            EbookView.printErrorMessage("Error al actualizar el Ebook: "+ e.getMessage());
        }
    }
}
