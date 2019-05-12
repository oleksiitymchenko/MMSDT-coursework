package com.labs.maven.springBoot.SpringBootMSC.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "Name", nullable = false)
    private String Name;
    @Column(name = "Floors", nullable = true)
    private Integer Floors;
    @Column(name = "Address", nullable = false)
    private String Address;

    public Book(String name, Integer floors, String address)
    {
        this.Name = name;
        this.Floors = floors;
        this.Address = address;
    }

    public Book() { }

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "authors",
            joinColumns = { @JoinColumn(name = "book_id") },
            inverseJoinColumns = { @JoinColumn(name = "author_id") })
    private Set<Author> authors;

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "genres",
            joinColumns = { @JoinColumn(name = "book_id") },
            inverseJoinColumns = { @JoinColumn(name = "genre_id") })
    private Set<Genre> genres;

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

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
        this.Name = name;
    }

    public Integer getFloors() {
        return Floors;
    }

    public void setFloors(Integer floors) {
        this.Floors = floors;
    }

    @Override
    public boolean equals(Object obj)
    {
        Book dep = (Book)obj;
        return id == dep.id &&
                Name.equals(dep.Name) &&
                Floors.equals(dep.Floors);
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }
}
