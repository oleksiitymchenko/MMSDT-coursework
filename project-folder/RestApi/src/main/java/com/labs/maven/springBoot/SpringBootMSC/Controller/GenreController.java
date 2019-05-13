package com.labs.maven.springBoot.SpringBootMSC.Controller;

import com.labs.maven.springBoot.SpringBootMSC.Model.Genre;
import com.labs.maven.springBoot.SpringBootMSC.Service.GenreService;
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
@RequestMapping("/genres")
public class GenreController {

    private GenreService service;

    @Autowired
    public void setConstructionService(GenreService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Genre> getDepartment(@PathVariable Integer id){
        return service.getEntityById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Genre> getAllDepartments(){
        return service.getAllEntities();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody Genre book){
        return ResponseEntity.status(HttpStatus.OK).body(service.saveEntity(book));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@RequestBody Genre newConstr, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateEntity(newConstr, id));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Map<String, Boolean> delete(@PathVariable Integer id) throws ResourceNotFoundException {
        service.getEntityById(id).orElseThrow(() -> new ResourceNotFoundException(""));
        service.deleteEntity(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Delete", Boolean.TRUE);
        return response;
    }
}
