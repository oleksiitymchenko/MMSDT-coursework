package com.labs.maven.springBoot.SpringBootMSC.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "FirstName", nullable = false)
    private String FirstName;
    @Column(name = "LastName", nullable = false)
    private String LastName;
    @Column(name = "DateOfBirth", nullable = false)
    private Integer DateOfBirth;

    public Author(String FirstName, String LastName, Integer dateOfBirth)
    {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.DateOfBirth = dateOfBirth;
    }

    public Author()
    { }


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "author")
    private Set<Book> books = new HashSet<>();

    public Set<Book> getBooks() {
        return books;
    }

    public void setBook(Set<Book> books) {
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public Integer getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Integer dateOfBirth) {
        this.DateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object obj)
    {
        Author entity = (Author)obj;
        return id == entity.id &&
                FirstName.equals(entity.FirstName) &&
                LastName.equals(entity.LastName) &&
                DateOfBirth.equals(entity.DateOfBirth);
    }
}
