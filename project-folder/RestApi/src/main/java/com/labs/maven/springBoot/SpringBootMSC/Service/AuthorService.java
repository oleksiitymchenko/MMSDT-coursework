package com.labs.maven.springBoot.SpringBootMSC.Service;

import com.labs.maven.springBoot.SpringBootMSC.Model.Author;
import com.labs.maven.springBoot.SpringBootMSC.Model.LoggerTable;
import com.labs.maven.springBoot.SpringBootMSC.Repositories.AuthorRepository;
import com.labs.maven.springBoot.SpringBootMSC.ServerExceptions.InvalidInfoException;
import com.labs.maven.springBoot.SpringBootMSC.ServerExceptions.ItemNotFoundException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService implements IEntityService<Author> {

    private AuthorRepository repository;

    @Autowired
    public void setBuilderRepository(AuthorRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public LoggerService loggerService;

    @Override
    public Optional<Author> getEntityById(Integer id) {
        Optional<Author> doc = repository.findById(id);
        if (!doc.isPresent())
            throw new ItemNotFoundException("There is no entity with such id");

        return doc;
    }

    @Override
    public List<Author> getAllEntities() {
        return (List<Author>)repository.findAll();
    }

    @Override
    public Author saveEntity(Author author) {

        if (author.getFirstName() == null ||
                author.getLastName() == null ||
                author.getDateOfBirth() == null) {
            throw new InvalidInfoException("Not all required fields where filled in");
        }
        ObjectMapper serializer = new ObjectMapper();
        try {
            loggerService.addLog("Author", "Created", serializer.writeValueAsString(author));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repository.save(author);
    }

    @Override
    public Author updateEntity(Author newAuthor, Integer id) {
        if (newAuthor.getFirstName() == null ||
                newAuthor.getLastName() == null ||
                newAuthor.getDateOfBirth() == null) {
            throw new InvalidInfoException("Not all required fields where filled in");
        } else {
            return repository.findById(id)
                    .map(builder -> {
                        builder.setFirstName(newAuthor.getFirstName());
                        builder.setLastName(newAuthor.getLastName());
                        builder.setDateOfBirth(newAuthor.getDateOfBirth());
                        ObjectMapper serializer = new ObjectMapper();
                        try {
                            loggerService.addLog("Author", "Updated", serializer.writeValueAsString(builder));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return repository.save(builder);
                    })
                    .orElseGet(() -> {
                        newAuthor.setId(id);
                        ObjectMapper serializer = new ObjectMapper();
                        try {
                            loggerService.addLog("Author", "Updated", serializer.writeValueAsString(newAuthor));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return repository.save(newAuthor);
                    });
        }
    }

    @Override
    public void deleteEntity(Integer id) {
        ObjectMapper serializer = new ObjectMapper();
        try {
            loggerService.addLog("Author", "Deleted", serializer.writeValueAsString(repository.findById(id).get()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        repository.deleteById(id);
    }




    private void sendLog(Author author, String queueName) {
        System.out.println("*******************");
        System.out.println("Sending message");
        LoggerTable logRecord = new LoggerTable();
        ObjectMapper mapper = new ObjectMapper();


        try {
            logRecord.setMessageText(mapper.writeValueAsString(author));
            logRecord.setEntityName(Author.class.getName());
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
