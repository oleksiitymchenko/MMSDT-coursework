package com.labs.maven.springBoot.SpringBootMSC.Repositories;

import com.labs.maven.springBoot.SpringBootMSC.Model.BookInGenre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookInGenreRepository extends CrudRepository<BookInGenre, Integer> {
    Optional<BookInGenre> findBookInGenreByBook_Id(Integer Id);
    Optional<BookInGenre> findBookInGenreByGenre_Id(Integer Id);
}
