package com.labs.maven.springBoot.SpringBootMSC.Service;

import com.labs.maven.springBoot.SpringBootMSC.Model.Genre;
import com.labs.maven.springBoot.SpringBootMSC.Repositories.GenreRepository;
import com.labs.maven.springBoot.SpringBootMSC.ServerExceptions.InvalidInfoException;
import com.labs.maven.springBoot.SpringBootMSC.ServerExceptions.ItemNotFoundException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class GenreService implements IEntityService<Genre> {
    @Autowired
    public LoggerService loggerService;

    private GenreRepository repository;

    @Autowired
    public void setConstructionRepository(GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Genre> getEntityById(Integer id) {
        Optional<Genre> entities = repository.findById(id);

        if (!entities.isPresent()) {
            throw new ItemNotFoundException("There is no entity with such id");
        }
        return entities;
    }

    @Override
    public List<Genre> getAllEntities() {
        return (List<Genre>) repository.findAll();
    }

    @Override
    public Genre saveEntity(Genre genre) {
        ObjectMapper serializer = new ObjectMapper();
        try {
            loggerService.addLog("Genre", "Created", serializer.writeValueAsString(genre));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repository.save(genre);
    }

    @Override
    public Genre updateEntity(Genre updatedEntity, Integer id) {
        if (updatedEntity.getName() == null || updatedEntity.getDescription() == null) {
            throw new InvalidInfoException("Not all required fields where filled in");
        }
        return repository.findById(id)
                .map(entity -> {
                    entity.setName(updatedEntity.getName());
                    entity.setDescription(updatedEntity.getDescription());
                    entity.setBooks(updatedEntity.getBooks());
                    ObjectMapper serializer = new ObjectMapper();
                    try {
                        loggerService.addLog("Genre", "Updated", serializer.writeValueAsString(entity));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return repository.save(entity);
                })
                .orElseGet(() -> {
                    updatedEntity.setId(id);
                    ObjectMapper serializer = new ObjectMapper();
                    try {
                        loggerService.addLog("Genre", "Updated", serializer.writeValueAsString(updatedEntity));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return repository.save(updatedEntity);
                });
    }

    @Override
    public void deleteEntity(Integer id) {
        ObjectMapper serializer = new ObjectMapper();
        try {
            loggerService.addLog("Genre", "Deleted", serializer.writeValueAsString(repository.findById(id).get()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        repository.deleteById(id);
    }
}
