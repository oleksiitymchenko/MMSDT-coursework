package com.client.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "isDeleted", nullable = false)
    private Boolean isDeleted = false;

    @JsonIgnore
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void  setIsDeleted(Boolean isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "year", nullable = false)
    private Integer year;

    public Book(String title, Integer year)
    {
        this.title = title;
        this.year = year;
    }

    public Book()
    { }


    @ManyToOne(targetEntity = Client.class)
    private Client Client;

    @JsonIgnore
    public Client getClient() {
        return Client;
    }

    public void setClient(Client client) {
        this.Client = client;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {        this.id = id;    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted){
        this.isDeleted = isDeleted;
    }

    @Override
    public boolean equals(Object obj)
    {
        Book doc = (Book) obj;
        return id == doc.id &&
                title.equals(doc.title) &&
                year.equals(doc.year);
    }
}