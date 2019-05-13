package com.labs.maven.springBoot.SpringBootMSC.Service;

import com.labs.maven.springBoot.SpringBootMSC.Model.Book;
import com.labs.maven.springBoot.SpringBootMSC.Repositories.BookRepository;
import com.labs.maven.springBoot.SpringBootMSC.ServerExceptions.InvalidInfoException;
import com.labs.maven.springBoot.SpringBootMSC.ServerExceptions.ItemNotFoundException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IEntityService<Book> {

    @Autowired
    public LoggerService loggerService;

    private BookRepository repository;

    @Autowired
    public void setConstructionRepository(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Book> getEntityById(Integer id) {
        Optional<Book> construction = repository.findById(id);

        if (!construction.isPresent()) {
            throw new ItemNotFoundException("There is no entity with such id");
        }
        return construction;
    }

    @Override
    public List<Book> getAllEntities() {
        return (List<Book>)repository.findAll();
    }

    @Override
    public Book saveEntity(Book book) {
        if (book.getName() == null || book.getName() == null) {
            throw new InvalidInfoException("Not all required fields where filled in");
        }
        ObjectMapper serializer = new ObjectMapper();
        try {
            loggerService.addLog("Book", "Created", serializer.writeValueAsString(book));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repository.save(book);
    }

    @Override
    public Book updateEntity(Book newBook, Integer id) {

        if (newBook.getName() == null || newBook.getDescription() == null) {
            throw new InvalidInfoException("Not all required fields where filled in");
        }
        return repository.findById(id)
                .map(constr -> {
                    constr.setName(newBook.getName());
                    constr.setPrice(newBook.getPrice());
                    constr.setDescription(newBook.getDescription());
                    ObjectMapper serializer = new ObjectMapper();
                    try {
                        loggerService.addLog("Book", "Updated", serializer.writeValueAsString(constr));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return repository.save(constr);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    ObjectMapper serializer = new ObjectMapper();
                    try {
                        loggerService.addLog("Book", "Updated", serializer.writeValueAsString(newBook));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return repository.save(newBook);
                });
    }

    @Override
    public void deleteEntity(Integer id) {
        ObjectMapper serializer = new ObjectMapper();
        try {
            loggerService.addLog("Book", "Deleted", serializer.writeValueAsString(repository.findById(id).get()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        repository.deleteById(id);
    }
}
