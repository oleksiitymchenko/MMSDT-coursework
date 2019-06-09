package com.bookStore.App.BookStoreSpringBootApp.Controllers;

import com.bookStore.App.BookStoreSpringBootApp.Models.Genre;
import com.bookStore.App.BookStoreSpringBootApp.ServerExceptions.ItemNotFoundException;
import com.bookStore.App.BookStoreSpringBootApp.Services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private GenreService service;

    @Autowired
    public void setGenreController(GenreService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Genre> getGenre(@PathVariable Integer id){
        return service.getEntityById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Genre> getAllGenres(){
        return service.getAllEntities();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> createGenre(@RequestBody Genre genre){
        return ResponseEntity.status(HttpStatus.OK).body(service.saveEntity(genre));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateGenre(@RequestBody Genre newGenre, @PathVariable Integer id){
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
