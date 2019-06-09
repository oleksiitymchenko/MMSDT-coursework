package com.bookStore.App.BookStoreSpringBootApp.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id = null;
    private String title;
    private boolean isDeleted = false;

    public Genre(String title){
        this.title = title;
    }

    public Genre(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) { this.id = id;  }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted){
        this.isDeleted = isDeleted;
    }
}