package com.iesam.digitalLibrary.features.digitalResources.presentation;

import com.iesam.digitalLibrary.features.digitalResources.domain.AudioBook;

import java.util.List;
import java.util.Scanner;

public class AudioBookView {

    public static void printAudioBookMenu() {
        System.out.println("\n+-----------------------------------------+");
        System.out.println("|           Gestión de Audiolibros        |");
        System.out.println("|-----------------------------------------|");
        System.out.println("|          [1] Agregar Audiolibro.        |");
        System.out.println("|          [2] Consultar Audiolibro.      |");
        System.out.println("|          [3] Borrar Audiolibro.         |");
        System.out.println("|          [4] Lista de Audiolibros.      |");
        System.out.println("|          [5] Modificar un Audiolibro.   |");
        System.out.println("|          [0]     Salir.                 |");
        System.out.println("+-------------------<~>-------------------+");
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
        System.err.println("[CANCELED] "+ message + ".");
    }

    public static void printConsoleRequest(String message){
        System.out.print("-> "+ message + ": ");
    }

    public static void printAudioBookInfo(AudioBook audioBook){
        System.out.println("> INFORMACIÓN DEL AUDIOLIBRO:");
        System.out.println("------------------------------");
        System.out.println("- ID         : " + audioBook.id);
        System.out.println("- Título     : " + audioBook.title);
        System.out.println("- Autor      : " + audioBook.author);
        System.out.println("- Duración   : " + audioBook.duration);
        System.out.println("- Formato    : " + audioBook.format);
        System.out.println("- Descripción: " + audioBook.description);
        System.out.println("------------------------------");
    }

    public static void printAudioBooksInfo(List<AudioBook> audioBooks) {
        System.out.println("+----------------------------------------------------------------------------------------+");
        System.out.println("| ID     | Duración   | Narrador            | Título            | Autor                  |");
        System.out.println("+----------------------------------------------------------------------------------------+");

        for (AudioBook audioBook : audioBooks) {
            System.out.printf("| %06d | %-10s | %-18s | %-18s | %-22s |\n",
                    audioBook.id, audioBook.duration, audioBook.narrator, audioBook.title, audioBook.author);
        }

        System.out.println("+----------------------------------------------------------------------------------------+");
        printInfoMessage("Total de Audiolibros: " + audioBooks.size());
    }

    public static AudioBook captureAudioBookDetails(Scanner sc, Integer id){
        System.out.print("-> Duración: ");
        String duration = sc.nextLine();
        System.out.print("-> Narrador: ");
        String narrator = sc.nextLine();
        System.out.print("-> Título: ");
        String title = sc.nextLine();
        System.out.print("-> Autor: ");
        String author = sc.nextLine();
        System.out.print("-> Descripción: ");
        String description = sc.nextLine();
        System.out.print("-> Formato (MP3, FLAC, AAC, ...): ");
        String format = sc.nextLine();

        if (id != null) {
            return new AudioBook(id, title, author, format, description ,narrator, duration);
        } else {
            return new AudioBook(title, author, format, description ,narrator, duration);
        }
    }

    public static AudioBook captureAudioBookDetails(Scanner sc) {
        return captureAudioBookDetails(sc, null);
    }

    public static void deleteAudioBookConfirmation(AudioBook audioBook){
        printInfoMessage("Recuperando Audiolibro...");
        printAudioBookInfo(audioBook);
        printWarningMessage("Eliminar un Audiolibro es una acción permanente.");
        System.out.print("- ¿Desea eliminar este Audiolibro? (Y|N): ");
    }

    public static void updateAudioBookConfirmation(AudioBook audioBook) {
        printInfoMessage("Recuperando Audiolibro...");
        printAudioBookInfo(audioBook);
        System.out.print("- ¿Desea modificar este Audiolibro? (Y|N): ");
    }
}
