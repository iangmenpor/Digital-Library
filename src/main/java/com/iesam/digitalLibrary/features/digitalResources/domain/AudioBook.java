package com.iesam.digitalLibrary.features.digitalResources.domain;

public class AudioBook extends DigitalResource {

    public final String narrator;
    public final String duration;

    public AudioBook(Integer id, String title, String author, String format, String description, String narrator, String duration) {
        super(id, title, author, format, description);
        this.narrator = narrator;
        this.duration = duration;
    }
    public AudioBook(String title, String author, String format, String description, String narrator, String duration) {
        super(title, author, format, description);
        this.narrator = narrator;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("AudioLibro [%d | Título: %s | Autor: %s | Formato: %s | Descripción: %s | Narrador: %s | Duración: %s]",
                id, title, author, format, description, narrator, duration);
    }
}
