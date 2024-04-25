package com.iesam.digitalLibrary.features.user.domain;

public class User {

    public final Integer id;
    public final String name;
    public final String surname;
    public final String dni;
    public final String email;

    public User(Integer id, String name, String surname, String dni, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.email = email;
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
