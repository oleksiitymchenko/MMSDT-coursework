package com.labs.maven.springBoot.SpringBootMSC.Controller;

import com.labs.maven.springBoot.SpringBootMSC.Model.BookInGenre;
import com.labs.maven.springBoot.SpringBootMSC.Service.BookInGenreService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/authors")
public class BookInGenreController {

    private BookInGenreService service;

    @Autowired
    public void setBuilderService(BookInGenreService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<BookInGenre> getOne(@PathVariable Integer id){
        return service.getEntityById(id);
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public Optional<BookInGenre> getOneByBook(@PathVariable Integer id){
        return service.getEntityByBookId(id);
    }

    @RequestMapping(value = "/genre/{id}", method = RequestMethod.GET)
    public Optional<BookInGenre> getOneByGenre(@PathVariable Integer id){
        return service.getEntityByGenreId(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<BookInGenre> getAll(){
        return service.getAllEntities();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody BookInGenre author){
        return ResponseEntity.status(HttpStatus.OK).body(service.saveEntity(author));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@RequestBody BookInGenre newAuthor, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateEntity(newAuthor, id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Map<String, Boolean> delete(@PathVariable Integer id) throws ResourceNotFoundException {
        service.getEntityById(id).orElseThrow(() -> new ResourceNotFoundException(""));
        service.deleteEntity(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Delete", Boolean.TRUE);
        return response;
    }
}
