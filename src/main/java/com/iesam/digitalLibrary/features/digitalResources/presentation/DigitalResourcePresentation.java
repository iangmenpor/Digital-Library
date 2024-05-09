package com.iesam.digitalLibrary.features.digitalResources.presentation;

import com.iesam.digitalLibrary.features.digitalResources.data.DigitalResourceDataRepository;
import com.iesam.digitalLibrary.features.digitalResources.data.local.EbookFileDataSource;
import com.iesam.digitalLibrary.features.digitalResources.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DigitalResourcePresentation {

    private static Scanner sc;

    private static final DigitalResourceDataRepository ebookDataRepository = new DigitalResourceDataRepository(new EbookFileDataSource());

    public DigitalResourcePresentation(Scanner sc) { DigitalResourcePresentation.sc = sc; }

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
        int option;
        do {
            availableResourcesMenu();
            System.out.print("-> Seleccione una opción: ");
            option = sc.nextInt();
            sc.nextLine();
            switch (option){
                case 1:
                    System.out.print("> ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    saveBook(captureEbookProductDetails(id));
                    System.out.println("<OK> Nuevo Libro Catalogado.");
                    break;
                case 2:
                    System.out.println("En futuras actualizaciones.");
                    break;
                case 0:
                    System.out.println("<Info> Cancelado.");
                    break;
            }
        }  while (option != 0);
    }
    public static void saveBook(EBook model){
        SaveDigitalResourceUseCase saveDigitalResourceUseCase = new SaveDigitalResourceUseCase(ebookDataRepository);
        saveDigitalResourceUseCase.execute(model);
    }
    private static void getDigitalProductPresentation(){
        System.out.print("> Ingresa el ID del Producto que deseas recuperar: ");
        int id = sc.nextInt();
        sc.nextLine();
        int option;
        do {
            availableResourcesMenu();
            System.out.print("-> Seleccione una opción: ");
            option = sc.nextInt();
            sc.nextLine();
            switch (option){
                case 1:
                    EBook recoveredEbook = getEbook(id);
                    if (recoveredEbook == null){
                        System.err.println("<!> No se ha encontrado un producto digital con ese ID");
                    } else {
                        if (isInstanceOfEbook(recoveredEbook)){
                            System.out.println("<Info> Recuperando información sobre el libro con ID " + id);
                            System.out.println("  > " + recoveredEbook);
                        }
                    }
                    break;
                case 2:
                    System.out.println("En futuras actualizaciones.");
                    break;
                case 0:
                    System.out.println("<Info> Cancelado.");
                    break;
            }
        }  while (option != 0);

        sc.nextLine(); // Consumir línea

    }
    public static EBook getEbook(Integer id){
        GetDigitalResourceUseCase getDigitalResourceUseCase = new GetDigitalResourceUseCase(ebookDataRepository);
        return getDigitalResourceUseCase.execute(id);
    }
    private  static void deleteDigitalProductPresentation(){
        System.out.print("> Ingresa el ID del producto que deseas eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();
        int option;
        do {
            availableResourcesMenu();
            System.out.print("-> Seleccione una opción: ");
            option = sc.nextInt();
            sc.nextLine();
            switch (option){
                case 1:
                   EBook eBookToDelete = getEbook(id);
                    if (eBookToDelete == null){
                        System.err.println("<!> No se ha encontrado un libro con ese ID");
                    } else {
                        System.out.println("- El producto que estás a punto de eliminar es: " + eBookToDelete);
                        System.out.println("  <Warning> Eliminar a un producto es una acción permanente.");
                        System.out.print("- ¿Estás seguro de que deseas eliminar a este producto? (Y|N): ");
                        char conf = sc.next().charAt(0);
                        char confirmation = Character.toUpperCase(conf);
                        sc.nextLine(); //consumo
                        switch (confirmation) {
                            case 'Y':
                                deleteEbook(id);
                                System.out.println("<OK> Producto eliminado.");
                                break;
                            case 'N':
                                System.out.println("<Info> Se ha cancelado la eliminación. Volviendo...");
                                break;
                            default:
                                System.err.println("<!> Opción no válida. Por favor introduce Y o N.");
                        }
                    }
                    break;
                case 2:
                    System.out.println("En futuras actualizaciones.");
                    break;
                case 0:
                    System.out.println("<Info> Cancelado.");
                    break;
            }
        }  while (option != 0);
    }
    public static void deleteEbook(Integer id){
        DeleteDigitalResourceUseCase deleteDigitalResourceUseCase = new DeleteDigitalResourceUseCase(ebookDataRepository);
        deleteDigitalResourceUseCase.execute(id);
    }
    private static void getAllDigitalProductsPresentation(){
        int option;
        do {
            availableResourcesMenu();
            System.out.print("-> Seleccione una opción: ");
            option = sc.nextInt();
            sc.nextLine();
            System.out.println("<Info> Imprimiendo listado de Productos Digitales...");
            switch (option){
                case 1:
                    ArrayList<EBook> eBooks = new ArrayList<>(getEBooks());
                    if (!eBooks.isEmpty()){
                        eBooks.forEach(System.out::println);
                        System.out.println("<OK> Productos en total: "+ eBooks.size());
                    } else {
                        System.out.println("<Info> No se han encontrado productos digitales.");
                    }
                    break;
                case 2:
                    System.out.println("En futuras actualizaciones.");
                    break;
                case 0:
                    System.out.println("<Info> Cancelado.");
                    break;
            }
        }  while (option != 0);
    }

    public static List<EBook> getEBooks(){
        GetDigitalResourcesUseCase getDigitalResourcesUseCase = new GetDigitalResourcesUseCase(ebookDataRepository);
        return getDigitalResourcesUseCase.execute();
    }
    private static void updateDigitalProductPresentation(){
        System.out.print("-> Introduce el id del producto que deseas actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();
        int option;
        do {
            availableResourcesMenu();
            System.out.print("-> Seleccione una opción: ");
            option = sc.nextInt();
            sc.nextLine();
            switch (option){
                case 1:
                    EBook oldEbook = getEbook(id);
                    if (oldEbook == null) {
                        System.err.println("<!> No se ha encontrado un Producto Digital con ese ID");
                        break;
                    }
                    updateEBook(captureEbookProductDetails(id));
                    break;
                case 2:
                    System.out.println("En futuras actualizaciones.");
                    break;
                case 0:
                    System.out.println("<Info> Cancelado.");
                    break;
            }
        }  while (option != 0);
    }
    private static void updateEBook(EBook oldEbook) {
       UpdateDigitalResourceUseCase updateDigitalResourceUseCase = new UpdateDigitalResourceUseCase(ebookDataRepository);
       updateDigitalResourceUseCase.execute(oldEbook);
    }

    private static EBook captureEbookProductDetails(Integer id) {
        System.out.print("-> ISBN:");
        String isbn = sc.nextLine();
        System.out.print("-> Título: ");
        String title = sc.nextLine();
        System.out.print("-> Autor: ");
        String author = sc.nextLine();
        System.out.print("-> Numeración de páginas: ");
        String numPages = sc.nextLine();
        System.out.print("-> Formato: ");
        String format = sc.nextLine();

        // Devolver una instancia actualizada de DigitalProduct con los nuevos detalles
        return new EBook(id, isbn, title, author, numPages, format);
    }
    private static boolean isInstanceOfEbook(DigitalResource digitalResource){
        return digitalResource instanceof EBook;
    }
    private static void availableResourcesMenu(){
        System.out.println("╔--------------------------------------------╗");
        System.out.println("║        Seleccione el tipo de producto      ║");
        System.out.println("║--------------------------------------------║");
        System.out.println("║   [1] EBook.                               ║");
        System.out.println("║   [2] Otro tipo de producto.               ║");
        System.out.println("║   [0] Cancelar.                            ║");
        System.out.println("║                                            ║");
        System.out.println("+--------------------------------------------+");
    }
}
