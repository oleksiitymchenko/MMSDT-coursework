package com.bookStore.App.BookStoreSpringBootApp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "isDeleted", nullable = false)
    private Boolean isDeleted = false;

    @JsonIgnore
    public Boolean getIsDeleted() {
        return isDeleted;
    }
    public void  setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname", nullable = true)
    private String surname;
    @Column(name = "email", nullable = false)
    private String email;

    public Client(String name, String surname, String email)
    {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Client() { }

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Book> books;

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object obj)
    {
        Client dep = (Client)obj;
        return id == dep.id &&
                name.equals(dep.name) &&
                surname.equals(dep.surname);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
