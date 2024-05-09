package com.iesam.digitalLibrary.features.digitalResources.domain;

public abstract class DigitalResource {

    public final Integer id;
    public final String title;
    public final String author;
    public final String format;

    public DigitalResource(Integer id, String title, String author, String format) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.format = format;
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
