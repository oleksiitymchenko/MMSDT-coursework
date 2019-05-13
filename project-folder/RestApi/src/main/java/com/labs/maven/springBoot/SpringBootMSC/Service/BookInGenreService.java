package com.labs.maven.springBoot.SpringBootMSC.Service;

import com.labs.maven.springBoot.SpringBootMSC.Model.BookInGenre;
import com.labs.maven.springBoot.SpringBootMSC.Model.LoggerTable;
import com.labs.maven.springBoot.SpringBootMSC.Repositories.BookInGenreRepository;
import com.labs.maven.springBoot.SpringBootMSC.ServerExceptions.ItemNotFoundException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BookInGenreService implements IEntityService<BookInGenre> {

    private BookInGenreRepository repository;

    @Autowired
    public void setBuilderRepository(BookInGenreRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public LoggerService loggerService;

    @Override
    public Optional<BookInGenre> getEntityById(Integer id) {
        Optional<BookInGenre> doc = repository.findById(id);
        if (!doc.isPresent())
            throw new ItemNotFoundException("There is no entity with such id");

        return doc;
    }

    public Optional<BookInGenre> getEntityByBookId(Integer id) {
        Optional<BookInGenre> doc = repository.findBookInGenreByBook_Id(id);
        if (!doc.isPresent())
            throw new ItemNotFoundException("There is no entity with such id");

        return doc;
    }

    public Optional<BookInGenre> getEntityByGenreId(Integer id) {
        Optional<BookInGenre> doc = repository.findBookInGenreByGenre_Id(id);
        if (!doc.isPresent())
            throw new ItemNotFoundException("There is no entity with such id");

        return doc;
    }

    @Override
    public List<BookInGenre> getAllEntities() {
        return (List<BookInGenre>)repository.findAll();
    }

    @Override
    public BookInGenre saveEntity(BookInGenre entity) {

        ObjectMapper serializer = new ObjectMapper();
        try {
            loggerService.addLog("BookInGenre", "Created", serializer.writeValueAsString(entity));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repository.save(entity);
    }

    @Override
    public BookInGenre updateEntity(BookInGenre newAuthor, Integer id) {
            return repository.findById(id)
                    .map(builder -> {
                        builder.setBook(newAuthor.getBook());
                        builder.setGenre(newAuthor.getGenre());
                        ObjectMapper serializer = new ObjectMapper();
                        try {
                            loggerService.addLog("BookInGenre", "Updated", serializer.writeValueAsString(builder));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return repository.save(builder);
                    })
                    .orElseGet(() -> {
                        newAuthor.setId(id);
                        ObjectMapper serializer = new ObjectMapper();
                        try {
                            loggerService.addLog("BookInGenre", "Updated", serializer.writeValueAsString(newAuthor));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return repository.save(newAuthor);
                    });
    }

    @Override
    public void deleteEntity(Integer id) {
        ObjectMapper serializer = new ObjectMapper();
        try {
            loggerService.addLog("BookInGenre", "Deleted", serializer.writeValueAsString(repository.findById(id).get()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        repository.deleteById(id);
    }




    private void sendLog(BookInGenre author, String queueName) {
        System.out.println("*******************");
        System.out.println("Sending message");
        LoggerTable logRecord = new LoggerTable();
        ObjectMapper mapper = new ObjectMapper();


        try {
            logRecord.setMessageText(mapper.writeValueAsString(author));
            logRecord.setEntityName(BookInGenre.class.getName());
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
        //publisher.produceMsg(loggerRecordString, queueName);
    }
}
