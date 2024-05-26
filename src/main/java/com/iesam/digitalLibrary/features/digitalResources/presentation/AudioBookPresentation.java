package com.iesam.digitalLibrary.features.digitalResources.presentation;

import com.iesam.digitalLibrary.features.digitalResources.domain.*;

import java.util.List;
import java.util.Scanner;

public class AudioBookPresentation {

    private final Scanner sc;
    private final DigitalResourceRepository<AudioBook> audioBookDigitalResourceRepository;

    public AudioBookPresentation(Scanner sc, DigitalResourceRepository<AudioBook> audioBookDigitalResourceRepository) {
        this.sc = sc;
        this.audioBookDigitalResourceRepository = audioBookDigitalResourceRepository;
    }

    public void displayAudioBookMenu() {
        int choice;
        do {
            AudioBookView.printAudioBookMenu();
            AudioBookView.printConsoleRequest("Ingrese su elección");
            choice = getValidatedIntegerInput();

            switch (choice) {
                case 1:
                    saveAudioBook();
                    break;
                case 2:
                    getAudioBook();
                    break;
                case 3:
                    deleteAudioBook();
                    break;
                case 4:
                    AudioBookView.printAudioBooksInfo(getAllAudioBooks());
                    break;
                case 5:
                    updateAudioBook();
                    break;
                case 0:
                    AudioBookView.printInfoMessage("Volviendo al menú de recursos...");
                    break;
                default:
                    AudioBookView.printErrorMessage("Opción no válida. Por favor, vuelva a intentarlo.");
                    break;
            }
        } while (choice != 0);
    }

    private int getValidatedIntegerInput() {
        while (!sc.hasNextInt()) {
            AudioBookView.printErrorMessage("Por favor, ingresa un número válido.");
            sc.next(); // Consumir la entrada no válida
        }
        int input = sc.nextInt();
        sc.nextLine(); // Consumir la nueva línea
        return input;
    }

    private void saveAudioBook() {
        AudioBook newAudioBook = AudioBookView.captureAudioBookDetails(sc);
        saveAudioBook(newAudioBook);
        AudioBookView.printAudioBookInfo(newAudioBook);
        AudioBookView.printSuccessMessage("Nuevo Audiolibro catalogado correctamente");
    }

    public void saveAudioBook(AudioBook model) {
        try {
            SaveDigitalResourceUseCase<AudioBook> saveDigitalResourceUseCase = new SaveDigitalResourceUseCase<>(audioBookDigitalResourceRepository);
            saveDigitalResourceUseCase.execute(model);
        } catch (Exception e) {
            AudioBookView.printErrorMessage("Error al guardar el Audiolibro: " + e.getMessage());
        }
    }

    private void getAudioBook() {
        AudioBookView.printConsoleRequest("Ingresa el ID del Audiolibro que deseas recuperar");
        int id = getValidatedIntegerInput();
        AudioBook audioBook = getAudioBook(id);
        if (audioBook == null) {
            AudioBookView.printErrorMessage("Audiolibro no encontrado");
        } else {
            AudioBookView.printAudioBookInfo(audioBook);
        }
    }

    public AudioBook getAudioBook(Integer id) {
        GetDigitalResourceUseCase<AudioBook> getDigitalResourceUseCase = new GetDigitalResourceUseCase<>(audioBookDigitalResourceRepository);
        return getDigitalResourceUseCase.execute(id);
    }

    private void deleteAudioBook() {
        AudioBookView.printConsoleRequest("Ingresa el ID del Audiolibro que deseas eliminar");
        int id = getValidatedIntegerInput();

        AudioBook audioBook = getAudioBook(id);
        if (audioBook == null) {
            AudioBookView.printErrorMessage("Audiolibro no encontrado");
        } else {
            AudioBookView.deleteAudioBookConfirmation(audioBook);
            char conf = sc.next().charAt(0);
            char confirmation = Character.toUpperCase(conf);
            sc.nextLine(); //consumo

            switch (confirmation) {
                case 'Y':
                    deleteAudioBook(id);
                    AudioBookView.printSuccessMessage("Audiolibro eliminado");
                    break;
                case 'N':
                    AudioBookView.printCancelMessage("Eliminación cancelada");
                    break;
                default:
                    AudioBookView.printErrorMessage("Opción no válida. Orden cancelada por defecto");
                    break;
            }
        }
    }

    public void deleteAudioBook(Integer id) {
        try {
            DeleteDigitalResourceUseCase<AudioBook> deleteDigitalResourceUseCase = new DeleteDigitalResourceUseCase<>(audioBookDigitalResourceRepository);
            deleteDigitalResourceUseCase.execute(id);
        } catch (Exception e) {
            AudioBookView.printErrorMessage("Error al eliminar el Audiolibro: " + e.getMessage());
        }
    }

    public List<AudioBook> getAllAudioBooks() {
        GetDigitalResourcesUseCase<AudioBook> getDigitalResourcesUseCase = new GetDigitalResourcesUseCase<>(audioBookDigitalResourceRepository);
        return getDigitalResourcesUseCase.execute();
    }

    private void updateAudioBook() {
        AudioBookView.printConsoleRequest("Ingresa el ID del Audiolibro que deseas modificar");
        int id = getValidatedIntegerInput();

        AudioBook audioBook = getAudioBook(id);
        if (audioBook == null) {
            AudioBookView.printErrorMessage("Audiolibro no encontrado");
        } else {
            AudioBookView.updateAudioBookConfirmation(audioBook);
            char conf = sc.next().charAt(0);
            char confirmation = Character.toUpperCase(conf);
            sc.nextLine(); //consumo

            switch (confirmation) {
                case 'Y':
                    updateAudioBook(AudioBookView.captureAudioBookDetails(sc, id));
                    AudioBookView.printSuccessMessage("Audiolibro Actualizado");
                    break;
                case 'N':
                    AudioBookView.printCancelMessage("Modificación cancelada");
                    break;
                default:
                    AudioBookView.printErrorMessage("Opción no válida. Orden cancelada por defecto");
                    break;
            }
        }
    }

    public void updateAudioBook(AudioBook model) {
        try {
            UpdateDigitalResourceUseCase<AudioBook> updateDigitalResourceUseCase = new UpdateDigitalResourceUseCase<>(audioBookDigitalResourceRepository);
            updateDigitalResourceUseCase.execute(model);
        } catch (Exception e) {
            AudioBookView.printErrorMessage("Error al actualizar el Audiolibro: " + e.getMessage());
        }
    }
}
