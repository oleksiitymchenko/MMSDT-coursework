package com.bookStore.App.BookStoreSpringBootApp.Controllers;

import com.bookStore.App.BookStoreSpringBootApp.Models.Book;
import com.bookStore.App.BookStoreSpringBootApp.ServerExceptions.ItemNotFoundException;
import com.bookStore.App.BookStoreSpringBootApp.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService service;

    @Autowired
    public void setBookService(BookService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Book> getGame(@PathVariable Integer id){
        return service.getEntityById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Book> getAllGames(){
        return service.getAllEntities();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> createGame(@RequestBody Book book){
        return ResponseEntity.status(HttpStatus.OK).body(service.saveEntity(book));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateGame(@RequestBody Book newBook, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateEntity(newBook, id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Map<String, Boolean> deleteGame(@PathVariable Integer id) throws ItemNotFoundException {
        service.getEntityById(id).orElseThrow(() -> new ItemNotFoundException(""));
        service.deleteEntity(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Delete", Boolean.TRUE);
        return response;
    }
}
