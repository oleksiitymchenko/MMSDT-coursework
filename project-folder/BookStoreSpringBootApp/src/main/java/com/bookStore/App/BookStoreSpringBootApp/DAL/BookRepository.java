package com.bookStore.App.BookStoreSpringBootApp.DAL;

import com.bookStore.App.BookStoreSpringBootApp.Models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
    Optional<Book> findByIdAndIsDeletedFalse(int id);
    List<Book> findByIsDeletedFalse();
}
