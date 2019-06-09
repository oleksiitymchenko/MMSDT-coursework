package com.client.Controller;

import com.client.Model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

import com.client.Client.RestClient;

@RestController
public class ClientController {

    @Autowired
    private RestClient restClient;
    @GetMapping("/clients/list")
    public ModelAndView getAllCustomers(){
        ModelAndView model = new ModelAndView("clientsList.jsp");
        model.addObject("clients",restClient.getAllClients());
        return model;
    }

    @PostMapping("/clients/create")
    public ModelAndView createCustomer(@Valid Client client, @RequestParam Map<String,String> customerNew){

        restClient.createClient(client,customerNew);
        return new ModelAndView("redirect:/clients/list");
    }

    @GetMapping("/clients/{id}")
    public ModelAndView getCustomer(@PathVariable Integer id) {
        ModelAndView model = new ModelAndView("clientDetail.jsp");
        model.addObject("client",restClient.getClient(id));

        return model;
    }

    @PostMapping("/clients/{id}")
    public ModelAndView updateCustomer(@Valid Client client, @RequestParam Map<String, String> body, @PathVariable("id") Integer id) {
        restClient.updateClient(client,body,id);
        return new ModelAndView("redirect:/clients/list");
    }

    @GetMapping(value="clients/delete/{id}")
    public ModelAndView deleteCustomer(@PathVariable("id") Integer id) {
        restClient.deleteClient(id);
        return new ModelAndView("redirect:/clients/list");
    }
}
