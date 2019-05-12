package com.labs.maven.springBoot.SpringBootMSC.Controller;

import com.labs.maven.springBoot.SpringBootMSC.Model.Author;
import com.labs.maven.springBoot.SpringBootMSC.Service.AuthorService;
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
@RequestMapping("/builders")
public class BuilderController {

    private AuthorService service;

    @Autowired
    public void setBuilderService(AuthorService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Author> getOne(@PathVariable Integer id){
        return service.getEntityById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Author> getAll(){
        return service.getAllEntities();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody Author author){
        return ResponseEntity.status(HttpStatus.OK).body(service.saveEntity(author));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@RequestBody Author newAuthor, @PathVariable Integer id){
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
