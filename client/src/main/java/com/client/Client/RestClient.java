package com.client.Client;

import com.client.Model.Book;
import com.client.Model.Client;
import com.client.Model.Genre;
import com.client.Model.LoggerTable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient("service")
public interface RestClient {

    @GetMapping("/books")
    List<Book> getAllBooks();

    @PostMapping("books")
    Book createBook(@RequestBody Book book, @RequestParam Map<String, String> bookNew);

    @GetMapping("books/{id}")
    Book getBook(@PathVariable Integer id);

    @PutMapping("books/{id}")
    ResponseEntity<Object> updateBook(@RequestBody Book newBook, @RequestParam Map<String, String> body, @PathVariable Integer id);

    @DeleteMapping("books/{id}")
    Map<String, Boolean> deleteBook(@PathVariable Integer id);

    @GetMapping("clients")
    List<Client> getAllClients();

    @PostMapping("clients")
    Client createClient(@RequestBody Client client, @RequestParam Map<String, String> clientNew);

    @GetMapping("clients/{id}")
    Client getClient(@PathVariable Integer id);

    @PutMapping("clients/{id}")
    ResponseEntity<Object> updateClient(@RequestBody Client newClient, @RequestParam Map<String, String> body, @PathVariable Integer id);

    @DeleteMapping("clients/delete/{id}")
    Map<String, Boolean> deleteClient(@PathVariable Integer id);

    @GetMapping("genres")
    List<Genre> getAllGenres();

    @PostMapping("genres")
    Genre createGenre(@RequestBody Genre genre, @RequestParam Map<String, String> newGenre);

    @GetMapping("genres/{id}")
    Genre getGenre(@PathVariable Integer id);

    @PutMapping("genres/{id}")
    ResponseEntity<Object> updateGenre(@RequestBody Genre newGenre, @RequestParam Map<String, String> body, @PathVariable Integer id);

    @DeleteMapping("genres/{id}")
    Map<String, Boolean> deleteGenre(@PathVariable Integer id);

    @GetMapping("logger")
    List<LoggerTable> getLogs();
}

