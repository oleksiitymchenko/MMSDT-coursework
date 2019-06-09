package com.bookStore.App.BookStoreSpringBootApp.DAL;

import com.bookStore.App.BookStoreSpringBootApp.Models.LoggerTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoggerTableRepository extends CrudRepository<LoggerTable, Integer> {
    List<LoggerTable> findAll();
}


