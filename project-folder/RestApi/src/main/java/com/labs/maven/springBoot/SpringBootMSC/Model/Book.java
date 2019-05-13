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
    @Column(name = "Price", nullable = true)
    private Integer Price;
    @Column(name = "Description", nullable = false)
    private String Description;
    @Column(name ="Author")
    private String Author;

    public Set<BookInGenre> getBookInGenres() {
        return bookInGenres;
    }

    public void setBookInGenres(Set<BookInGenre> bookInGenres) {
        this.bookInGenres = bookInGenres;
    }

    @OneToMany(mappedBy = "book")
    Set<BookInGenre> bookInGenres;

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public Book(String name, Integer price, String description)
    {
        this.Name = name;
        this.Price = price;
        this.Description = description;
    }

    public Book() { }

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

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        this.Price = price;
    }

    @Override
    public boolean equals(Object obj)
    {
        Book dep = (Book)obj;
        return id == dep.id &&
                Name.equals(dep.Name) &&
                Price.equals(dep.Price);
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }
}
