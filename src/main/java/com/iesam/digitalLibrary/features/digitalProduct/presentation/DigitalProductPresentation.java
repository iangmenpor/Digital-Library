package com.iesam.digitalLibrary.features.digitalProduct.presentation;

import com.iesam.digitalLibrary.features.digitalProduct.data.DigitalProductDataRepository;
import com.iesam.digitalLibrary.features.digitalProduct.data.local.DigitalProductFileDataSource;
import com.iesam.digitalLibrary.features.digitalProduct.data.local.DigitalProductMemLocalDataSource;
import com.iesam.digitalLibrary.features.digitalProduct.domain.*;

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
            System.out.println("2. Consultar un Producto.");
            System.out.println("3. Eliminar un Producto.");
            System.out.println("4. Volver a menú principal.");
            System.out.println("+-------------------------------+");
            System.out.print("> Ingrese su elección: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consumir la nueva línea después del entero

            switch (choice){
                case 1:
                    saveDigitalProduct();
                    break;
                case 2:
                    getDigitalProductPresentation();
                    break;
                case 3:
                    deleteDigitalProductPresentation();
                    break;
                case 4:
                    System.out.println("<Info> Volviendo a menú principal...");
                    break;
                default:
                    System.err.println("<!> Opción no valida. Vuelva a intentarlo.");
            }
        } while (choice != 4);
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
    private static void getDigitalProductPresentation(){
        System.out.print("> Ingresa el ID del Usuario que deseas recuperar: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consumir línea
        DigitalProduct recoveredDigitalProduct = getDigitalProduct(id);
        if (recoveredDigitalProduct != null){
            System.out.println("<Info> Recuperando información sobre el Producto digital con ID " + id);
            System.out.println("  > " + recoveredDigitalProduct);
        } else {
            System.err.println("\n<!> No se ha encontrado un producto digital con ese ID");
        }
    }
    public static DigitalProduct getDigitalProduct(Integer id){
        GetDigitalProductUseCase getDigitalProductUseCase = new GetDigitalProductUseCase(dataRepository);
        return getDigitalProductUseCase.execute(id);
    }
    private  static void deleteDigitalProductPresentation(){
        System.out.print("> Ingresa el ID del producto que deseas eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();
        DigitalProduct productToDelete = getDigitalProduct(id);
        if (productToDelete != null){
            System.out.println("- El producto que estás a punto de eliminar es: " + productToDelete);
            System.out.println("  <Warning> Eliminar a un producto es una acción permanente.");
            System.out.print("- ¿Estás seguro de que deseas eliminar a este producto? (Y|N): ");
            char conf = sc.next().charAt(0);
            char confirmation = Character.toUpperCase(conf);
            sc.nextLine(); //consumo
            switch (confirmation) {
                case 'Y':
                    deleteDigitalProduct(id);
                    System.out.println("<OK> Producto eliminado.");
                    break;
                case 'N':
                    System.out.println("<Info> Se ha cancelado la eliminación. Volviendo...");
                    break;
                default:
                    System.err.println("<!> Opción no válida. Por favor introduce Y o N.");
            }
        } else {
            System.err.println("<!> No se ha encontrado un producto con ese ID");
        }
    }
    public static void deleteDigitalProduct(Integer id){
        DeleteDigitalProductUseCase deleteDigitalProductUseCase = new DeleteDigitalProductUseCase(dataRepository);
        deleteDigitalProductUseCase.execute(id);
    }
}
