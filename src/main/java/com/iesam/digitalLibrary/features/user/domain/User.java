package com.iesam.digitalLibrary.features.user.domain;
import java.util.*;
public class User {


    public final Integer id;
    public final String name;
    public final String surname;
    public final String dni;
    public final String email;

    // Constructor con ID
    public User (Integer id, String name, String surname, String dni, String email){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.email = email;
    }
    // Constructor sin ID (el ID se genera por un método privado)
    public User(String name, String surname, String dni, String email) {
        this.id = defaultId();
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.email = email;
    }

    private Integer defaultId(){
        return generateUniqueCombinedId();
    }
    // Lógica para generar un ID único
    public static int generateUniqueCombinedId() {
        // Obtener la marca de tiempo actual en milisegundos
        long timestamp = new Date().getTime();

        // Generar un ID aleatorio único
        int randomId = generateUniqueRandomId();

        // Combinar la marca de tiempo y el ID aleatorio para formar un ID único
        // Limita el timestamp a tres cifras y luego lo multiplica por 1000 para dejar espacio al ID aleatorio
        int id = (int) (timestamp % 1000) * 1000 + randomId;

        // Asegurar que el ID sea positivo
        return Math.abs(id);
    }

    private static int generateUniqueRandomId() {
        Random random = new Random();
        int id;
        id = random.nextInt(90) + 10; // Genera un número aleatorio entre 10 y 99 (inclusive)
        return id;
    }

    @Override
    public String toString() {
        return "- Usuario : {" +
                " id =" + id +
                ", nombre ='" + name + '\'' +
                ", apellidos ='" + surname + '\'' +
                ", dni ='" + dni + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
