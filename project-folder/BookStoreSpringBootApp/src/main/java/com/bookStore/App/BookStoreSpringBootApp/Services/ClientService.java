package com.bookStore.App.BookStoreSpringBootApp.Services;

import com.bookStore.App.BookStoreSpringBootApp.DAL.ClientRepository;
import com.bookStore.App.BookStoreSpringBootApp.Messaging.Producer;
import com.bookStore.App.BookStoreSpringBootApp.Models.Client;
import com.bookStore.App.BookStoreSpringBootApp.Models.Genre;
import com.bookStore.App.BookStoreSpringBootApp.Models.LoggerTable;
import com.bookStore.App.BookStoreSpringBootApp.ServerExceptions.InvalidInfoException;
import com.bookStore.App.BookStoreSpringBootApp.ServerExceptions.ItemNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IService<Client> {

    @Autowired
    Producer publisher;

    @Value("${jsa.rabbitmq.queue.createdtype}")
    String queueCreatedName;

    @Value("${jsa.rabbitmq.queue.updatedtype}")
    String queueUpdatedName;

    @Value("${jsa.rabbitmq.queue.deletedtype}")
    String queueDeletedName;

    private ClientRepository repository;

    @Autowired
    public void setClientRepository(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Client> getEntityById(Integer id) {
        Optional<Client> customer = repository.findByIdAndIsDeletedFalse(id);

        if (!customer.isPresent()) {
            throw new ItemNotFoundException("There is no entity with such id");
        }
        return customer;
    }

    @Override
    public List<Client> getAllEntities() {
        return (List<Client>)repository.findByIsDeletedFalse();
    }

    @Override
    public Client saveEntity(Client client) {
        if (client.getName() == null || client.getName() == null) {
            throw new InvalidInfoException("Not all required fields where filled in");
        }
        return repository.save(client);
    }

    @Override
    public Client updateEntity(Client newcustomer, Integer id) {

        if (newcustomer.getName() == null || newcustomer.getEmail() == null) {
            throw new InvalidInfoException("Not all required fields where filled in");
        }
        return repository.findById(id)
                .map(constr -> {
                    constr.setName(newcustomer.getName());
                    constr.setSurname(newcustomer.getSurname());
                    constr.setEmail(newcustomer.getEmail());
                    return repository.save(constr);
                })
                .orElseGet(() -> {
                    newcustomer.setId(id);
                    return repository.save(newcustomer);
                });
    }

    @Override
    public void deleteEntity(Integer id) {
        Optional<Client> client = repository.findById(id);
        if (!client.isPresent())
            throw new ItemNotFoundException("There is no entity with such id");

        client.map(clientNew -> {
            clientNew.setIsDeleted(true);
            return repository.save(clientNew);
        });
        repository.save(client.get());
    }

    private void sendLog(Client client, String queueName) {
        System.out.println("__________________");
        System.out.println("Sending message");
        LoggerTable logRecord = new LoggerTable();
        ObjectMapper mapper = new ObjectMapper();

        try {
            logRecord.setMessageText(mapper.writeValueAsString(client));
            logRecord.setEntityName(Genre.class.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String loggerRecordString = null;
        try {
            loggerRecordString = mapper.writeValueAsString(logRecord);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(loggerRecordString);
        publisher.produceMsg(loggerRecordString, queueName);
    }
}
