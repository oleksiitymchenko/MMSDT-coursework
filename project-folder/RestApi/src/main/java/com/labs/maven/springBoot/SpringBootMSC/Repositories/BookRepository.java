package com.labs.maven.springBoot.SpringBootMSC.Repositories;

import com.labs.maven.springBoot.SpringBootMSC.Model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer>{
}
