package com.gpch.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class GenreController {/*
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/instances")
    public String getInstancesRun(){
        String response = this.restTemplate.exchange(String.format("http://restapi/test/test"),
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                }).getBody();
        return  response;
    }
*/
}
