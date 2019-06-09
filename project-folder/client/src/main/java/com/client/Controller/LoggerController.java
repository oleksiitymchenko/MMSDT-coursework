package com.client.Controller;

import com.client.Client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoggerController {

    @Autowired
    private RestClient restClient;

    @GetMapping("/logger/list")
    public ModelAndView getAll() {
        ModelAndView model = new ModelAndView("loggerList.jsp");
        model.addObject("logs", restClient.getLogs());
        return model;
    }
}
