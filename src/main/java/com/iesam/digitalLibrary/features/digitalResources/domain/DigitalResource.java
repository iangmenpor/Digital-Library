package com.iesam.digitalLibrary.features.digitalResources.domain;

import java.util.Date;
import java.util.Random;

public class DigitalResource {

    public final Integer id;
    public final String title;
    public final String author;
    public final String format;
    public final String description;

    // Constructor con ID
    public DigitalResource(Integer id, String title, String author, String format, String description) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.format = format;
        this.description = description;
    }
    // Constructor sin ID (el ID se genera por un método privado)
    public DigitalResource(String title, String author, String format, String description) {
        this.id = defaultId();
        this.title = title;
        this.author = author;
        this.format = format;
        this.description = description;
    }
    // Lógica para generar un ID único
    private Integer defaultId() {
        Random random = new Random();
        // Obtener la marca de tiempo actual en milisegundos
        long timestamp = new Date().getTime();
        // Generar un ID aleatorio único entre 10 y 99 (inclusive)
        int randomId = random.nextInt(90) + 10;
        // Combinar la marca de tiempo y el ID aleatorio para formar un ID único
        int id = (int) (timestamp % 1000) * 1000 + randomId;
        // Asegurar que el ID sea positivo
        return Math.abs(id);
    }
    @Override
    public String toString() {
        return "DigitalProduct{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", format='" + format + '\'' +
                '}';
    }
}
