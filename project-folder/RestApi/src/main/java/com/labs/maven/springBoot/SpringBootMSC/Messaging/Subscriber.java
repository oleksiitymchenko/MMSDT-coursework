package com.labs.maven.springBoot.SpringBootMSC.Messaging;

import com.labs.maven.springBoot.SpringBootMSC.Model.LoggerTable;
import com.labs.maven.springBoot.SpringBootMSC.Service.LoggerService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Subscriber {

    @Autowired
    LoggerService loggerService;

    @RabbitListener(queues="${jsa.rabbitmq.queue.general}")
    public void receivedMessageCreated(String msg) {
        System.out.println("Received Message: " + msg);

        LoggerTable record = DeserializeLogRecord(msg);
        System.out.println(record);

        if(record == null) return;

        loggerService.saveEntity(record);
    }

    private LoggerTable DeserializeLogRecord(String logRecord)
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            LoggerTable LoggerTableEntity = mapper.readValue(logRecord, LoggerTable.class);
            System.out.println(LoggerTableEntity);
            return  LoggerTableEntity;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}