package com.bookStore.App.BookStoreSpringBootApp.Services;

import com.bookStore.App.BookStoreSpringBootApp.DAL.BookRepository;
import com.bookStore.App.BookStoreSpringBootApp.Models.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bookStore.App.BookStoreSpringBootApp.Messaging.Producer;
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
public class BookService implements IService<Book> {

    private BookRepository repository;

    @Autowired
    public void setBookRepository(BookRepository repository) {
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
    public Optional<Book> getEntityById(Integer id) {
        Optional<Book> book = repository.findByIdAndIsDeletedFalse(id);
        if (!book.isPresent())
            throw new ItemNotFoundException("There is no entity with such id");

        return book;
    }

    @Override
    public List<Book> getAllEntities() {
        return (List<Book>)repository.findByIsDeletedFalse();
    }

    @Override
    public Book saveEntity(Book book) {

        if (book.getTitle() == null ||
                book.getYear() == null) {
            throw new InvalidInfoException("Not all required fields where filled in");
        }
        sendLog(book, queueCreatedName);
        return repository.save(book);
    }

    @Override
    public Book updateEntity(Book newBook, Integer id) {
        if (newBook.getTitle() == null ||
                newBook.getYear() == null) {
            throw new InvalidInfoException("Not all required fields where filled in");
        } else {
            return repository.findById(id)
                    .map(book -> {
                        book.setTitle(newBook.getTitle());
                        book.setYear(newBook.getYear());
                        sendLog(book, queueUpdatedName);
                        return repository.save(book);
                    })
                    .orElseGet(() -> {
                        newBook.setId(id);
                        sendLog(newBook, queueUpdatedName);
                        return repository.save(newBook);
                    });
        }
    }

    @Override
    public void deleteEntity(Integer id) {
        Optional<Book> book = repository.findById(id);
        if (!book.isPresent())
            throw new ItemNotFoundException("There is no entity with such id");
        book.map(gameNew -> {
            gameNew.setIsDeleted(true);
            return repository.save(gameNew);
        });
        repository.save(book.get());
        sendLog(book.get(), queueDeletedName);
    }




    private void sendLog(Book book, String queueName) {
        System.out.println("________________");
        System.out.println("Sending message");
        LoggerTable logRecord = new LoggerTable();
        ObjectMapper mapper = new ObjectMapper();
        try {
            logRecord.setMessageText(mapper.writeValueAsString(book));
            logRecord.setEntityName(Book.class.getName());
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
