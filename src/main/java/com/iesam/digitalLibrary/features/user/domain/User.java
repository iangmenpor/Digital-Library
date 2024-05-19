package com.iesam.digitalLibrary.features.user.domain;
import java.util.*;
public class User {

    //Usar random para el id
    public final Integer id;
    public final String name;
    public final String surname;
    public final String dni;
    public final String email;

    public User(Integer id, String name, String surname, String dni, String email) {
        this.id = defaultId(id);
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.email = email;
    }

    private Integer defaultId(Integer id){
        if (id == null){
            return generateUniqueCombinedId();
        }
        return id;
    }

    public static int generateUniqueCombinedId() {
        long timestamp = new Date().getTime();
        int randomId = generateUniqueRandomId();
        int id = (int) (timestamp % 1000) * 1000 + randomId; // Limita el timestamp a tres cifras y luego lo multiplica por 1000
        return Math.abs(id); // Asegura que el ID sea positivo
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
