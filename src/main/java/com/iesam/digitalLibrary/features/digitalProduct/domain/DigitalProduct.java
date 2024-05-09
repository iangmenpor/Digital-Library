package com.iesam.digitalLibrary.features.digitalProduct.domain;

public abstract class DigitalProduct {

    public final Integer id;
    public final String title;
    public final String author;
    public final String format;

    public DigitalProduct(Integer id, String title, String author, String format1) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.format = format1;
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
