package com.bookStore.App.BookStoreSpringBootApp.Controllers;

import com.bookStore.App.BookStoreSpringBootApp.Models.LoggerTable;
import com.bookStore.App.BookStoreSpringBootApp.ServerExceptions.ItemNotFoundException;
import com.bookStore.App.BookStoreSpringBootApp.Services.LoggerTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/logger")
public class LoggerTableController {

    private LoggerTableService service;

    @Autowired
    public void setLoggerTableController(LoggerTableService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<LoggerTable> getGenre(@PathVariable Integer id){
        return service.getEntityById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<LoggerTable> getAllGenres(){
        return service.getAllEntities();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> createGenre(@RequestBody LoggerTable genre){
        return ResponseEntity.status(HttpStatus.OK).body(service.saveEntity(genre));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateGenre(@RequestBody LoggerTable newGenre, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateEntity(newGenre, id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Map<String, Boolean> deleteGenre(@PathVariable Integer id) throws ItemNotFoundException {
        service.getEntityById(id).orElseThrow(() -> new ItemNotFoundException(""));
        service.deleteEntity(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Delete", Boolean.TRUE);
        return response;
    }
}
