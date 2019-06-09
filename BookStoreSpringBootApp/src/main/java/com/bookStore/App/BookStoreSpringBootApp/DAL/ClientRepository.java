package com.bookStore.App.BookStoreSpringBootApp.DAL;

import com.bookStore.App.BookStoreSpringBootApp.Models.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {
    Optional<Client> findByIdAndIsDeletedFalse(int id);
    List<Client> findByIsDeletedFalse();
}

