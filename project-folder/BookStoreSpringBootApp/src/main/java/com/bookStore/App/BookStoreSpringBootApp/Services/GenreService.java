package com.bookStore.App.BookStoreSpringBootApp.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.bookStore.App.BookStoreSpringBootApp.DAL.GenreRepository;
//import Producer;
import com.bookStore.App.BookStoreSpringBootApp.Messaging.Producer;
import com.bookStore.App.BookStoreSpringBootApp.Models.Genre;
import com.bookStore.App.BookStoreSpringBootApp.Models.LoggerTable;
import com.bookStore.App.BookStoreSpringBootApp.ServerExceptions.InvalidInfoException;
import com.bookStore.App.BookStoreSpringBootApp.ServerExceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class GenreService implements IService<Genre> {

    private GenreRepository repository;

    @Autowired
    public void setGenreRepository(GenreRepository repository) {
        this.repository = repository;
    }

    @Autowired
    Producer publisher;

    @Value("${jsa.rabbitmq.queue.createdtype}")
    String queueCreatedName;

    @Value("${jsa.rabbitmq.queue.updatedtype}")
    String queueUpdatedName;

    @Value("${jsa.rabbitmq.queue.deletedtype}")
    String queueDeletedName;

    @Override
    public Optional<Genre> getEntityById(Integer id) {
        Optional<Genre> genre = repository.findByIdAndIsDeletedFalse(id);
        if (!genre.isPresent())
            throw new ItemNotFoundException("There is no entity with such id");

        return genre;
    }

    @Override
    public List<Genre> getAllEntities() {
        return (List<Genre>)repository.findByIsDeletedFalse();
    }

    @Override
    public Genre saveEntity(Genre genre) {

        if (genre.getTitle() == null ) {
            throw new InvalidInfoException("Not all required fields where filled in");
        }
        sendLog(genre, queueCreatedName);
        return repository.save(genre);
    }

    @Override
    public Genre updateEntity(Genre newGenre, Integer id) {
        if (newGenre.getTitle() == null ) {
            throw new InvalidInfoException("Not all required fields where filled in");
        } else {
            return repository.findById(id)
                    .map(genre -> {
                        genre.setTitle(newGenre.getTitle());
                        sendLog(genre, queueUpdatedName);
                        return repository.save(genre);
                    })
                    .orElseGet(() -> {
                        newGenre.setId(id);
                        sendLog(newGenre, queueUpdatedName);
                        return repository.save(newGenre);
                    });
        }
    }

    @Override
    public void deleteEntity(Integer id) {
        Optional<Genre> genre = repository.findById(id);
        if (!genre.isPresent())
            throw new ItemNotFoundException("There is no entity with such id");
        genre.map(genreNew -> {
            genreNew.setDeleted(true);
            return repository.save(genreNew);
        });
        repository.save(genre.get());
        sendLog(genre.get(), queueDeletedName);
    }




    private void sendLog(Genre genre, String queueName) {
        System.out.println("__________________");
        System.out.println("Sending message");
        LoggerTable logRecord = new LoggerTable();
        ObjectMapper mapper = new ObjectMapper();

        try {
            logRecord.setMessageText(mapper.writeValueAsString(genre));
            logRecord.setEntityName(Genre.class.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String loggerRecordString = null;
        try {
            loggerRecordString = mapper.writeValueAsString(logRecord);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(loggerRecordString);
        publisher.produceMsg(loggerRecordString, queueName);
    }
}
