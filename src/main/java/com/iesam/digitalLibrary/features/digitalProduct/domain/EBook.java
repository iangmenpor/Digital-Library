package com.iesam.digitalLibrary.features.digitalProduct.domain;

public class EBook extends DigitalProduct {

    public final String isbn;
    public final String numPages;


    public EBook(Integer id, String isbn, String title, String author, String numPages, String format) {
        super(id, title, author, format);
        this.isbn = isbn;
        this.numPages = numPages;
    }

    @Override
    public String toString() {
        return "EBook{" +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", numPages='" + numPages + '\'' +
                ", format='" + format + '\'' +
                '}';
    }
}