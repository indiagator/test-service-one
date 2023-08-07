package com.example.testserviceone;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer
{

    MynumberRepository mynumberRepository;

    Consumer( MynumberRepository mynumberRepository)
    {
        this.mynumberRepository = mynumberRepository;
    }
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "product-offers", groupId = "group_id")
    public void consume(String message) throws IOException
    {

        ObjectMapper mapper = new ObjectMapper();
        Mynumber number =  mapper.readValue(message, Mynumber.class);
        number.setId((int)(Math.random()*10000));
        mynumberRepository.save(number);
        logger.info(String.format("#### -> Consumed message -> %s", message));
    }
}
