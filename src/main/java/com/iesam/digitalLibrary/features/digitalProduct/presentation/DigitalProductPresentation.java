package com.iesam.digitalLibrary.features.digitalProduct.presentation;

import com.iesam.digitalLibrary.features.digitalProduct.data.DigitalProductDataRepository;
import com.iesam.digitalLibrary.features.digitalProduct.data.local.DigitalProductFileDataSource;
import com.iesam.digitalLibrary.features.digitalProduct.data.local.DigitalProductMemLocalDataSource;
import com.iesam.digitalLibrary.features.digitalProduct.domain.DigitalProduct;
import com.iesam.digitalLibrary.features.digitalProduct.domain.EBook;
import com.iesam.digitalLibrary.features.digitalProduct.domain.SaveDigitalProductUseCase;

import java.util.Scanner;

public class DigitalProductPresentation {

    private static Scanner sc;

    private static final DigitalProductDataRepository dataRepository = new DigitalProductDataRepository(new DigitalProductFileDataSource());

    public DigitalProductPresentation(Scanner sc) { DigitalProductPresentation.sc = sc; }

    public void displayDigitalProductsMenu(){
        int choice;
        do {
            System.out.println();
            System.out.println("+-------Menu de Productos-------+");
            System.out.println("1. Catalogar nuevo Producto.");
            System.out.println("2. Volver a menú principal.");
            System.out.println("+-------------------------------+");
            System.out.print("> Ingrese su elección: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consumir la nueva línea después del entero

            switch (choice){
                case 1:
                    saveDigitalProduct();
                    break;
                case 2:
                    System.out.println("<Info> Volviendo a menú principal...");
                    break;
                default:
                    System.err.println("<!> Opción no valida. Vuelva a intentarlo.");
                    break;
            }
        } while (choice != 2);
    }

    private static void saveDigitalProduct() {
        SaveDigitalProductUseCase saveDigitalProductUseCase = new SaveDigitalProductUseCase(dataRepository);
        //Integer id; String isbn, String title, String author, String numPages, String format
        int type;
        do {
            System.out.println();
            System.out.println("[--Tipos de Productos digitales--]");
            System.out.println("|     [1] Libros digitales.      |");
            System.out.println("|     [2]    Cancelar.           |");
            System.out.println("[--------------------------------]");
            System.out.print(" > Especifique el tipo de Producto: ");
            type = sc.nextInt();
            sc.nextLine();
            switch (type){
                case 1:
                    System.out.print("> ID: ");
                    Integer id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("> ISBN: ");
                    String isbn = sc.nextLine();
                    System.out.print("> Titulo: ");
                    String title = sc.nextLine();
                    System.out.print("> Autor: ");
                    String author = sc.nextLine();
                    System.out.print("> Nº Páginas: ");
                    String nPages = sc.nextLine();
                    System.out.print("> Formato (ePUB,BeeB,..): ");
                    String format = sc.nextLine();
                    DigitalProduct newProduct = new EBook(id,isbn,title,author,nPages,format);
                    saveDigitalProductUseCase.execute(newProduct);
                    System.out.println("<OK> Nuevo Libro Catalogado.");
                    break;
                case 2:
                    System.out.println("<Info> Cancelado.");
                    break;
            }
        }  while (type != 2);
    }
}
