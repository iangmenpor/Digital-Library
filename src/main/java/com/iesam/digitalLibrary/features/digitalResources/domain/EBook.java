package com.iesam.digitalLibrary.features.digitalResources.domain;

public class EBook extends DigitalResource {

    public final String isbn;
    public final String numPages;


    public EBook(Integer id, String isbn, String title, String author, String description, String numPages, String format) {
        super(id, title, author, format, description);
        this.isbn = isbn;
        this.numPages = numPages;
    }
    public EBook(String isbn, String title, String author, String description, String numPages, String format) {
        super(title, author, format, description);
        this.isbn = isbn;
        this.numPages = numPages;
    }

    @Override
    public String toString() {
        return String.format("EBOOK [ID: %d | Título: %s | Autor: %s | ISBN: %s | Páginas: %s | Formato: %s]", id, title, author, isbn, numPages, format);
    }
}
