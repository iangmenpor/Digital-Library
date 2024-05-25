package com.iesam.digitalLibrary.features.digitalResources.presentation;

import com.iesam.digitalLibrary.features.digitalResources.domain.EBook;

import java.util.List;
import java.util.Scanner;

public class EbookView {

    public static void printEbookMenu() {
        System.out.println("\n+-----------------------------------------+");
        System.out.println("|            Gestión de Ebooks            |");
        System.out.println("|-----------------------------------------|");
        System.out.println("|          [1] Agregar Ebook.             |");
        System.out.println("|          [2] Consultar Ebook.           |");
        System.out.println("|          [3] Borrar Ebook.              |");
        System.out.println("|          [4] Lista de Ebooks.           |");
        System.out.println("|          [5] Modificar un Ebook.        |");
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

    public static void printEbookInfo(EBook ebook){
        System.out.println("> INFORMACIÓN DEL LIBRO ELECTRÓNICO:");
        System.out.println("------------------------------------");
        System.out.println("- ID        : " + ebook.id);
        System.out.println("- Título    : " + ebook.title);
        System.out.println("- Autor     : " + ebook.author);
        System.out.println("- ISBN      : " + ebook.isbn);
        System.out.println("- Páginas   : " + ebook.numPages);
        System.out.println("- Formato   : " + ebook.format);
        System.out.println("- Descripción: " + ebook.description);
        System.out.println("------------------------------------");
    }

    public static void printEBooksInfo(List<EBook> ebooks) {
        System.out.println("+------------------------------------------------------------------+");
        System.out.println("| ID     | ISBN          | Título            | Autor               |");
        System.out.println("+------------------------------------------------------------------+");

        for (EBook ebook : ebooks) {
            System.out.printf("| %06d | %-13s | %-18s | %-18s |\n",
                    ebook.id, ebook.isbn, ebook.title, ebook.author);
        }

        System.out.println("+------------------------------------------------------------------+");
        printInfoMessage("Total de Libros Electrónicos: " + ebooks.size());
    }

    public static EBook captureEbookDetails(Scanner sc, Integer id){
        System.out.print("-> ISBN: ");
        String isbn = sc.nextLine();
        System.out.print("-> Título: ");
        String title = sc.nextLine();
        System.out.print("-> Autor: ");
        String author = sc.nextLine();
        System.out.print("-> Descripción: ");
        String description = sc.nextLine();
        System.out.print("-> Número de Páginas: ");
        String numPages = sc.nextLine();
        System.out.print("-> Formato (PDF, EPUB, AZW, ...): ");
        String format = sc.nextLine();

        if (id != null) {
            return new EBook(id, isbn, title, author, description, numPages, format);
        } else {
            return new EBook(isbn, title, author, description, numPages, format);
        }
    }

    public static EBook captureEbookDetails(Scanner sc) {
        return captureEbookDetails(sc, null);
    }

    public static void deleteEbookConfirmation(EBook ebook){
        printInfoMessage("Recuperando Ebook...");
        printEbookInfo(ebook);
        printWarningMessage("Eliminar un Ebook es una acción permanente.");
        System.out.print("- ¿Desea eliminar este Ebook? (Y|N): ");
    }

    public static void updateEbookConfirmation(EBook ebook) {
        printInfoMessage("Recuperando Ebook...");
        printEbookInfo(ebook);
        System.out.print("- ¿Desea modificar este Ebook? (Y|N): ");
    }
}
