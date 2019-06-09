package com.client.Controller;

import com.client.Client.RestClient;
import com.client.Model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class GenreController {

    @Autowired
    private RestClient restClient;
    @GetMapping("/genres/list")
    public ModelAndView getAllGenres(){
        ModelAndView model = new ModelAndView("genresList.jsp");
        model.addObject("genres",restClient.getAllGenres());
        return model;
    }

    @PostMapping("/genres/create")
    public ModelAndView createGenre(@Valid Genre genre, @RequestParam Map<String,String> genreNew){
        restClient.createGenre(genre,genreNew);
        return new ModelAndView("redirect:/genres/list");
    }

    @GetMapping("/genres/{id}")
    public ModelAndView getGenre(@PathVariable Integer id) {
        ModelAndView model = new ModelAndView("genreDetail.jsp");
        model.addObject("genre",restClient.getGenre(id));

        return model;
    }

    @PostMapping("/genres/{id}")
    public ModelAndView updateGenre(@Valid Genre genre, @RequestParam Map<String, String> body, @PathVariable("id") Integer id) {
        System.out.println("Updating genre: " + genre.getTitle());
        System.out.println("Updating genreNEw: " + body.toString());
        restClient.updateGenre(genre,body,id);
        return new ModelAndView("redirect:/genres/list");
    }

    @GetMapping(value="genres/delete/{id}")
    public ModelAndView deleteGenre(@PathVariable("id") Integer id) {
        restClient.deleteGenre(id);
        return new ModelAndView("redirect:/genres/list");
    }
}
