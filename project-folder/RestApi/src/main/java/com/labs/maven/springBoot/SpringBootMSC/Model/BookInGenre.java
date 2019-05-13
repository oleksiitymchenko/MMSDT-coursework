package com.labs.maven.springBoot.SpringBootMSC.Model;

import javax.persistence.*;

@Entity
public class BookInGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name ="mapBookId")
    private int mapBookId;

    @Column(name = "mapGenreId")
    private int mapGenreId;

    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name="genre_id")
    private Genre genre;

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

    public int getMapGenreId() {
        return mapGenreId;
    }

    public void setMapGenreId(int mapGenreId) {
        this.mapGenreId = mapGenreId;
    }

    public int getMapBookId() {
        return mapBookId;
    }

    public void setMapBookId(int mapBookId) {
        this.mapBookId = mapBookId;
    }
}
