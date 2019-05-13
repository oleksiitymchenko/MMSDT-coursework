package com.gpch.login.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "Name", nullable = false)
    private String Name;

    @Column(name = "Description", nullable = false)
    private String Description;

    @OneToMany(mappedBy = "genre")
    Set<BookInGenre> bookInGenres;

    public Genre() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Set<BookInGenre> getBookInGenres() {
        return bookInGenres;
    }

    public void setBookInGenres(Set<BookInGenre> bookInGenres) {
        this.bookInGenres = bookInGenres;
    }
}
