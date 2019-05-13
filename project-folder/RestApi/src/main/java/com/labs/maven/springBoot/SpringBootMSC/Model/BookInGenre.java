package com.labs.maven.springBoot.SpringBootMSC.Model;

import javax.persistence.*;

@Entity
public class BookInGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name="book_id")
    Book book;

    @ManyToOne
    @JoinColumn(name="genre_id")
    Genre genre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
