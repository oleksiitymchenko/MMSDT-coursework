package com.labs.maven.springBoot.SpringBootMSC.Controller;

//import com.labs.maven.springBoot.SpringBootMSC.Messaging.Producer;
import com.labs.maven.springBoot.SpringBootMSC.Model.LoggerTable;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/test")
public class TestController {
    //@Autowired
    //Producer publisher;

    @RequestMapping("/test")
    public String sendMessage(){
        System.out.println("*******************");
        //publisher.produceMsg("jsa.queue2", jsonInString);
        return "Successfully Msg Sent";
    }
}
