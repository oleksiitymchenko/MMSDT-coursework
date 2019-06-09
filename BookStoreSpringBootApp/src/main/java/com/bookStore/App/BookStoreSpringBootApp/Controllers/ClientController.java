package com.bookStore.App.BookStoreSpringBootApp.Controllers;

import com.bookStore.App.BookStoreSpringBootApp.Models.Client;
import com.bookStore.App.BookStoreSpringBootApp.Services.ClientService;
import com.bookStore.App.BookStoreSpringBootApp.ServerExceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private ClientService service;

    @Autowired
    public void setClientService(ClientService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Client> getCustomer(@PathVariable Integer id){
        return service.getEntityById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Client> getAllCustomers(){
        return service.getAllEntities();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> createCustomer(@RequestBody Client client){
        return ResponseEntity.status(HttpStatus.OK).body(service.saveEntity(client));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCustomer(@RequestBody Client newClient, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateEntity(newClient, id));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Map<String, Boolean> deleteCustomer(@PathVariable Integer id) throws ItemNotFoundException {
        service.getEntityById(id).orElseThrow(() -> new ItemNotFoundException(""));
        service.deleteEntity(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Delete", Boolean.TRUE);
        return response;
    }
}
