package com.client.Controller;


import com.client.Model.Authorities;
import com.client.Model.Users;
import com.client.Services.AuthService;
import com.client.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@EnableDiscoveryClient
@RestController
public class AuthController {

    @Autowired
    AuthService userRep;

    @Autowired
    RoleService roleRep;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(String error, String logout) {
        ModelAndView model = new ModelAndView("login.jsp");
        if (error != null)
            model.addObject("errorMsg", "Your username or password are invalid.");

        if (logout != null)
            model.addObject("msg", "You have been logged out successfully.");
        return model;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("index.jsp");
        return model;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration(String error, String logout) {
        ModelAndView model = new ModelAndView("registration.jsp");

        if (error != null)
            model.addObject("errorMsg", "Your username and password are invalid.");

        return model;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registration_post(@ModelAttribute Users users, String role) {
        users.setEnabled(true);
        userRep.save(users);
        Authorities a = new Authorities(users.getUsername(),"ROLE_"+role.toUpperCase());
        roleRep.save(a);
        return new ModelAndView("redirect:/login");
    }
}
