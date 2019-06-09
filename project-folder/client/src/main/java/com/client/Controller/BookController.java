package com.client.Controller;

import com.client.Client.RestClient;
import com.client.Model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    private RestClient restClient;
    @GetMapping("/books/list")
    public ModelAndView getAllGames(){
        ModelAndView model = new ModelAndView("booksList.jsp");
        model.addObject("books",restClient.getAllBooks());
        return model;
    }

    @PostMapping("/books/create")
    public ModelAndView createGame(@Valid Book book, @RequestParam Map<String,String> gameNew){

        restClient.createBook(book,gameNew);
        return new ModelAndView("redirect:/books/list");
    }

    @GetMapping("/books/{id}")
    public ModelAndView getGame(@PathVariable Integer id) {
        ModelAndView model = new ModelAndView("bookDetail.jsp");
        model.addObject("book",restClient.getBook(id));

        return model;
    }

    @PostMapping("/books/{id}")
    public ModelAndView updateGame(@Valid Book book, @RequestParam Map<String, String> body, @PathVariable("id") Integer id) {
       restClient.updateBook(book, body, id);
       return new ModelAndView("redirect:/books/list");
    }

    @GetMapping(value="books/delete/{id}")
    public ModelAndView deleteGame(@PathVariable("id") Integer id) {
        restClient.deleteBook(id);
        return new ModelAndView("redirect:/books/list");
    }
}
