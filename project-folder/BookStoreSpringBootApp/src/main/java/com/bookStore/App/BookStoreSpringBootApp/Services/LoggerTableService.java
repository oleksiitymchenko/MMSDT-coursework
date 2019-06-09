package com.bookStore.App.BookStoreSpringBootApp.Services;

import com.bookStore.App.BookStoreSpringBootApp.DAL.LoggerTableRepository;
import com.bookStore.App.BookStoreSpringBootApp.Models.LoggerTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoggerTableService  implements  IService<LoggerTable>{

    private LoggerTableRepository repository;

    @Autowired
    public void setLoggerRepository(LoggerTableRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<LoggerTable> getAllEntities() {

        return (List<LoggerTable>)repository.findAll();
    }

    @Override
    public Optional<LoggerTable> getEntityById(Integer id) {
        return null;
    }

    @Override
    public LoggerTable saveEntity(LoggerTable loggerTable) {
        return null;
    }

    @Override
    public LoggerTable updateEntity(LoggerTable loggerTable, Integer id) {
       return null;
    }

    @Override
    public void deleteEntity(Integer id) {

    }
}

