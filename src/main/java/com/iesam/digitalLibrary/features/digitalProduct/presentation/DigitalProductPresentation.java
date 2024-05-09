package com.iesam.digitalLibrary.features.digitalProduct.presentation;

import com.iesam.digitalLibrary.features.digitalProduct.data.DigitalProductDataRepository;
import com.iesam.digitalLibrary.features.digitalProduct.data.local.DigitalProductFileDataSource;
import com.iesam.digitalLibrary.features.digitalProduct.data.local.DigitalProductMemLocalDataSource;
import com.iesam.digitalLibrary.features.digitalProduct.domain.*;

import java.util.ArrayList;
import java.util.List;
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
            System.out.println("4. Consultar lista de productos.");
            System.out.println("5. Modificar producto.");
            System.out.println("6. Volver a menú principal.");
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
                    getAllDigitalProductsPresentation();
                    break;
                case 5:
                    updateDigitalProductPresentation();
                    break;
                case 6:
                    System.out.println("<Info> Volviendo a menú principal...");
                    break;
                default:
                    System.err.println("<!> Opción no valida. Vuelva a intentarlo.");
                    break;
            }
        } while (choice != 6);
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
    private static void getAllDigitalProductsPresentation(){
        List<DigitalProduct> allProducts = new ArrayList<>(getAllDigitalProducts());
        System.out.println("<Info> Imprimiendo listado de Productos Digitales...");
        if (!allProducts.isEmpty()){
            allProducts.forEach(System.out::println);
            System.out.println("<OK> Productos en total: "+ allProducts.size());
        } else {
            System.out.println("<Info> No se han encontrado productos digitales.");
        }
    }
    public static List<DigitalProduct> getAllDigitalProducts(){
        GetDigitalProductsUseCase getDigitalProductsUseCase = new GetDigitalProductsUseCase(dataRepository);
        return getDigitalProductsUseCase.execute();
    }
    private static void updateDigitalProductPresentation(){
        System.out.print("-> Introduce el id del producto que deseas actualizar: ");
        int id = sc.nextInt();
        DigitalProduct oldProduct = getDigitalProduct(id);
        System.out.println(oldProduct.getClass().getSimpleName());
        if (oldProduct == null) {
            System.err.println("<!> No se ha encontrado un Producto Digital con ese ID");
            return;
        }

        System.out.println("╔--------------------------------------------╗");
        System.out.println("║        Seleccione el tipo de producto      ║");
        System.out.println("║--------------------------------------------║");
        System.out.println("║   [1] EBook                                ║");
        System.out.println("║   [2] Otro tipo de producto                ║");
        System.out.println("║                                            ║");
        System.out.println("+--------------------------------------------+");
        System.out.print("-> Seleccione una opción: ");
        int option = sc.nextInt();
        sc.nextLine(); // Consume el salto de línea

        switch (option) {
            case 1:
                updateEBook(oldProduct);
                break;
            case 2:
                System.out.println("-Más productos por venir!!!");
                break;
            default:
                System.err.println("<!> Opción no válida.");
                break;
        }
    }
    private static void updateEBook(DigitalProduct oldProduct) {
        if (oldProduct instanceof EBook) {
            EBook ebook = (EBook) oldProduct; // Hace el casting a EBook
            System.out.println(" > " + ebook); // Imprime la representación de eBook
        } else {
            System.out.println(" > " + oldProduct); // Si no es una instancia de EBook, imprime la representación de DigitalProduct
        }
        System.out.print("- ¿Deseas actualizar este libro?(Y|N): ");
        char asw = sc.next().charAt(0);
        char confirmation = Character.toUpperCase(asw);
        sc.nextLine(); // Consume el salto de línea

        switch (confirmation) {
            case 'Y':
                EBook eBookUpdated = captureEbookProductDetails(oldProduct);
                updateDigitalProduct(eBookUpdated);
                System.out.println("<OK> Datos actualizados.");
                break;
            case 'N':
                System.out.println("<Info> Se ha cancelado la Actualización. Volviendo...");
                break;
            default:
                System.err.println("<!> Opción no válida. Por favor introduce Y o N.");
        }
    }

    private static EBook captureEbookProductDetails(DigitalProduct oldProduct) {
        System.out.print("-> Nuevo ISBN:");
        String isbn = sc.nextLine();
        System.out.print("-> Nuevo título: ");
        String title = sc.nextLine();
        System.out.print("-> Nuevo autor: ");
        String author = sc.nextLine();
        System.out.print("-> Nueva numeración de páginas: ");
        String numPages = sc.nextLine();
        System.out.print("-> Nuevo formato: ");
        String format = sc.nextLine();

        // Devolver una instancia actualizada de DigitalProduct con los nuevos detalles
        return new EBook(oldProduct.id, isbn, title, author, numPages, format);
    }
    public static void updateDigitalProduct(DigitalProduct model){
        UpdateDigitalProductUseCase updateDigitalProductUseCase = new UpdateDigitalProductUseCase(dataRepository);
        updateDigitalProductUseCase.execute(model);
    }
}
