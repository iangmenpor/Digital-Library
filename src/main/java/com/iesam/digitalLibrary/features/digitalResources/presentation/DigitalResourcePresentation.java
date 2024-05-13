package com.iesam.digitalLibrary.features.digitalResources.presentation;

import com.iesam.digitalLibrary.features.digitalResources.data.EbookDataRepository;
import com.iesam.digitalLibrary.features.digitalResources.data.local.EBookDataSourceRepository;
import com.iesam.digitalLibrary.features.digitalResources.data.local.EbookFileDataSource;
import com.iesam.digitalLibrary.features.digitalResources.domain.*;

import java.util.List;
import java.util.Scanner;

public class DigitalResourcePresentation {

    private static Scanner sc;

    // Para la implementación de libros electrónicos (EBooks)
    private static final EBookDataSourceRepository ebookDataSourceRepository = new EbookFileDataSource();
    private static final DigitalResourceRepository<EBook> ebookRepository = new EbookDataRepository(ebookDataSourceRepository);


    public DigitalResourcePresentation(Scanner sc) { DigitalResourcePresentation.sc = sc; }

    public void displayDigitalProductsMenu() {
        int choice;
        do {
            System.out.println("\n+--------------------------------+");
            System.out.println("|        Menú de Productos        |");
            System.out.println("+--------------------------------+");
            System.out.println("| 1. Catalogar Nuevo Producto.   |");
            System.out.println("| 2. Consultar un Producto.      |");
            System.out.println("| 3. Eliminar un Producto.       |");
            System.out.println("| 4. Consultar Lista de Productos|");
            System.out.println("| 5. Modificar Producto.         |");
            System.out.println("| 6. Volver a Menú Principal.    |");
            System.out.println("+--------------------------------+");

            System.out.print("> Ingrese su elección: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consumir la nueva línea después del entero

            switch (choice) {
                case 1:
                    saveDigitalResource();
                    break;
                case 2:
                    getDigitalProduct();
                    break;
                case 3:
                    deleteDigitalProduct();
                    break;
                case 4:
                    getAllDigitalProducts();
                    break;
                case 5:
                    updateDigitalProduct();
                    break;
                case 6:
                    System.out.println("<EXIT> Volviendo al menú principal...");
                    break;
                default:
                    System.err.println("<!> Opción no válida. Por favor, vuelva a intentarlo.");
                    break;
            }
        } while (choice != 6);
    }

    public static void availableResourcesMenu(){
        System.out.println("╔--------------------------------------------╗");
        System.out.println("║        Seleccione el tipo de producto      ║");
        System.out.println("╠--------------------------------------------╣");
        System.out.println("║   [1] EBook.                               ║");
        System.out.println("║   [2] Otro tipo de producto.               ║");
        System.out.println("║   [0] Cancelar.                            ║");
        System.out.println("+---------------------<>---------------------+");
    }



    private static void saveDigitalResource() {
        int option;
        do {
            availableResourcesMenu();
            System.out.print("> Ingresa tu elección: ");
            option = sc.nextInt();
            sc.nextLine();
            switch (option){
                case 0:
                    System.out.println("<EXIT> Cancelado.");
                    break;
                case 1:
                    System.out.print("-> ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    saveBook(captureEbookProductDetails(id));
                    break;
                case 2:
                    System.out.println("<Info> Esta opción aún no está disponible.");
                    break;
                default:
                    System.err.println("<!> Opción no válida. Por favor, intenta de nuevo.");
                    break;
            }
        }  while (option != 0);
    }
    private static void getDigitalProduct(){
        int option;
        do {
            availableResourcesMenu();
            System.out.print("> Ingresa tu elección: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 0:
                    System.out.println("<EXIT> Cancelado.");
                    break;
                case 1:
                    getEBookPretty();
                    break;
                case 2:
                    System.out.println("<Info> Esta opción aún no está disponible.");
                    break;
                default:
                    System.err.println("<!> Opción no válida. Por favor, intenta de nuevo.");
                    break;
            }
        } while (option != 0);
    }
    private  static void deleteDigitalProduct(){
        int option;
        do {
        availableResourcesMenu();
        System.out.print("> Ingresa tu elección: ");
        option = sc.nextInt();
        sc.nextLine();

        switch (option) {
            case 0:
                System.out.println("<EXIT> Cancelado.");
                break;
            case 1:
                System.out.print("-> ID: ");
                int id = sc.nextInt();
                deleteEbookPretty(id);
                break;
            case 2:
                System.out.println("<Info> Esta opción aún no está disponible.");
                break;
            default:
                System.err.println("<!> Opción no válida. Por favor, intenta de nuevo.");
                break;
            }
        }  while (option != 0);
    }
    private static void getAllDigitalProducts(){
        int option;
        do {
            availableResourcesMenu();
            System.out.print("> Ingresa tu elección: ");
            option = sc.nextInt();
            sc.nextLine();
            System.out.println("<Info> Imprimiendo listado de Productos Digitales...");
            switch (option) {
                case 0:
                    System.out.println("<Info> Cancelado.");
                    break;
                case 1:
                    getEBooksPretty();
                    break;
                case 2:
                    System.out.println("En futuras actualizaciones.");
                    break;
                default:
                    System.err.println("<!> Opción no válida. Por favor, intenta de nuevo.");
                    break;
            }
        } while (option != 0);
    }
    private static void updateDigitalProduct(){
        availableResourcesMenu();
        System.out.print("> Ingresa tu elección: ");
        int option = sc.nextInt();
        sc.nextLine();

        switch (option) {
            case 0:
                System.out.println("<EXIT> Cancelado.");
                break;
            case 1:
                System.out.print("-> Introduce el ID del libro electrónico que deseas actualizar: ");
                int id = sc.nextInt();
                updateEbookPretty(id);
                break;
            case 2:
                System.out.println("<Info> Esta opción aún no está disponible.");
                break;
            default:
                System.err.println("<!> Opción no válida. Por favor, intenta de nuevo.");
                break;
        }
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
        System.out.print("-> Formato (PDF,EPUB,AZW,..): ");
        String format = sc.nextLine();

        // Devolver una instancia actualizada de Ebook con los nuevos detalles
        return new EBook(id, isbn, title, author, numPages, format);
    }
    private static void getEBookPretty() {
        System.out.print("> Ingresa el ID del Libro Electrónico que deseas recuperar: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consumir la nueva línea después del entero

        EBook recoveredEBook = getEBook(id);

        if (recoveredEBook != null) {
            System.out.println(recoveredEBook);
        } else {
            System.err.println("<!> No se encontró ningún libro electrónico con ese ID.");
        }
    }
    private static void deleteEbookPretty(int id){
        EBook ebookToDelete = getEBook(id);

        if (ebookToDelete != null) {
            // Mostrar los detalles del libro electrónico a eliminar
            System.out.println("- El producto que estás a punto de eliminar es:");
            System.out.println(ebookToDelete);

            // Solicitar confirmación para la eliminación
            System.out.println("<Warning> Eliminar un producto es una acción permanente.");
            System.out.print("- ¿Estás seguro de que deseas eliminar este producto? (Y|N): ");
            char confirmation = Character.toUpperCase(sc.next().charAt(0));
            sc.nextLine();

            // Procesar la confirmación
            switch (confirmation) {
                case 'Y':
                    // Eliminar el libro electrónico
                    deleteEbook(id);
                    System.out.println("<OK> Producto eliminado.");
                    break;
                case 'N':
                    System.out.println("<Info> Se ha cancelado la eliminación. Volviendo...");
                    break;
                default:
                    System.err.println("<!> Opción no válida. Por favor, introduce Y o N.");
                    break;
            }
        } else {
            System.err.println("<!> No se ha encontrado ningún libro con ese ID.");
        }
    }
    private static void getEBooksPretty(){
        // Obtener la lista de libros electrónicos
        List<EBook> ebooks = getEBooks();
        if (!ebooks.isEmpty()) {
            // Mostrar los detalles de cada libro electrónico
            ebooks.forEach(System.out::println);
            System.out.println("<OK> Productos en total: " + ebooks.size());
        } else {
            System.out.println("<Info> No se han encontrado libros electrónicos.");
        }
    }
    private static void updateEbookPretty(int id) {
        // Obtener el libro electrónico con el ID proporcionado
        EBook ebookToUpdate = getEBook(id);

        if (ebookToUpdate != null) {
            // Mostrar los detalles del libro electrónico a actualizar
            System.out.println("- Los detalles del libro electrónico que estás a punto de actualizar son:");
            System.out.println(ebookToUpdate);

            // Solicitar nuevos detalles al usuario
            System.out.println("- Introduce los nuevos detalles para el libro electrónico:");

            updateEBook(captureEbookProductDetails(id));

            System.out.println("<OK> Libro electrónico actualizado con éxito.");
        } else {
            System.err.println("<!> No se ha encontrado ningún libro electrónico con ese ID.");
        }
    }


    public static void saveBook(EBook model){
        SaveDigitalResourceUseCase<EBook> saveDigitalResourceUseCase = new SaveDigitalResourceUseCase<>(ebookRepository);
        saveDigitalResourceUseCase.execute(model);
    }
    public static EBook getEBook(Integer id){
        GetDigitalResourceUseCase<EBook> getDigitalResourceUseCase = new GetDigitalResourceUseCase<>(ebookRepository);
        return getDigitalResourceUseCase.execute(id);
    }
    public static void deleteEbook(Integer id){
        DeleteDigitalResourceUseCase<EBook> deleteDigitalResourceUseCase = new DeleteDigitalResourceUseCase<>(ebookRepository);
        deleteDigitalResourceUseCase.execute(id);
    }
    public static List<EBook> getEBooks(){
        GetDigitalResourcesUseCase<EBook> getDigitalResourcesUseCase = new GetDigitalResourcesUseCase<>(ebookRepository);
        return getDigitalResourcesUseCase.execute();
    }
    public static void updateEBook(EBook model) {
        UpdateDigitalResourceUseCase<EBook> updateDigitalResourceUseCase = new UpdateDigitalResourceUseCase<>(ebookRepository);
        updateDigitalResourceUseCase.execute(model);
    }


}
