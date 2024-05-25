package com.iesam.digitalLibrary.features.digitalResources.presentation;


public class DigitalResourceView {

    public static void printDigitalResourcesMenu() {
        System.out.println("\n+---------------------------------------+");
        System.out.println("|      Menú de Recursos Digitales       |");
        System.out.println("|---------------------------------------|");
        System.out.println("|        [1] Gestión de Ebooks.         |");
        System.out.println("|        [2] Gestión de Otro.           |");
        System.out.println("|        [0] Salir                      |");
        System.out.println("+---------------------------------------+");
    }

    public static void printInfoMessage(String message) {
        System.out.println("[INFO] " + message + ".");
    }


    public static void printErrorMessage(String message) {
        System.err.println("[!] " + message);
    }

    public static void printConsoleRequest(String message){
        System.out.print("-> "+ message + ": ");
    }
}
