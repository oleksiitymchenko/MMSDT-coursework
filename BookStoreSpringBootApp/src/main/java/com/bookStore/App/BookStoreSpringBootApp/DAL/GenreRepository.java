package com.bookStore.App.BookStoreSpringBootApp.DAL;

import com.bookStore.App.BookStoreSpringBootApp.Models.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface GenreRepository extends CrudRepository<Genre, Integer> {
    Optional<Genre> findByIdAndIsDeletedFalse(int id);
    List<Genre> findByIsDeletedFalse();
}
