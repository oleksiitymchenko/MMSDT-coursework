package com.labs.maven.springBoot.SpringBootMSC.Repositories;

import com.labs.maven.springBoot.SpringBootMSC.Model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
