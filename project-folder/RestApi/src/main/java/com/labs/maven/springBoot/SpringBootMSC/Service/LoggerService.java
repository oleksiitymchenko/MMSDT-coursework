package com.labs.maven.springBoot.SpringBootMSC.Service;

import com.labs.maven.springBoot.SpringBootMSC.Model.Genre;
import com.labs.maven.springBoot.SpringBootMSC.Model.LoggerTable;
import com.labs.maven.springBoot.SpringBootMSC.Repositories.LoggerTableRepository;
import com.labs.maven.springBoot.SpringBootMSC.ServerExceptions.InvalidInfoException;
import com.labs.maven.springBoot.SpringBootMSC.ServerExceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoggerService implements IEntityService<LoggerTable> {
    private LoggerTableRepository repository;

    @Autowired
    public void setConstructionRepository(LoggerTableRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<LoggerTable> getEntityById(Integer id) {
        Optional<LoggerTable> entities = repository.findById(id);

        if (!entities.isPresent()) {
            throw new ItemNotFoundException("There is no entity with such id");
        }
        return entities;
    }

    @Override
    public List<LoggerTable> getAllEntities() {
        return (List<LoggerTable>) repository.findAll();
    }

    @Override
    public LoggerTable saveEntity(LoggerTable entity) {
        return repository.save(entity);
    }

    @Override
    public LoggerTable updateEntity(LoggerTable updatedEntity, Integer id) {
        return repository.findById(id)
                .map(entity -> {
                    entity.setEntityName(updatedEntity.getEntityName());
                    entity.setMessageType(updatedEntity.getMessageType());
                    entity.setMessageText(updatedEntity.getMessageText());
                    return repository.save(entity);
                })
                .orElseGet(() -> {
                    updatedEntity.setId(id);
                    return repository.save(updatedEntity);
                });
    }

    @Override
    public void deleteEntity(Integer id) {
        repository.deleteById(id);
    }

    public void addLog(String entityName, String MessageType, String MessageText){
        LoggerTable entity = new LoggerTable();
        entity.setEntityName(entityName);
        entity.setMessageText(MessageText);
        entity.setMessageType(MessageType);
        repository.save(entity);
    }
}
