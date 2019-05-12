package com.labs.maven.springBoot.SpringBootMSC.Repositories;

import com.labs.maven.springBoot.SpringBootMSC.Model.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Integer> {
}
