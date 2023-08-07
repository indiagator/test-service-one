package com.example.testserviceone;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class TestserviceoneApplication
{
    Logger logger = LoggerFactory.getLogger(TestserviceoneApplication.class);

    @Autowired
    private WebClient.Builder webClientBuilder;

    public static void main(String[] args)
    {
        SpringApplication.run(TestserviceoneApplication.class, args);
    }

    @GetMapping("/")
    public ResponseEntity<String> greet()
    {
        logger.info("greeting was sent from service 1");
        return new ResponseEntity<>("hello from test service one", HttpStatus.OK);

    }

    @GetMapping("/get")
    public Mono<String> getFromServiceTwo()
    {
        logger.info("request forwarded to service 2");
        return webClientBuilder.build().get().uri("http://localhost:8072/test-service-2/").retrieve().bodyToMono(String.class);
    }

    @PostMapping("/getsum")
    public Mono<Integer> getSumFromServiceTwo()
    {
        logger.info("request forwarded to service 2 for SUM");
        NumberPair pair = new NumberPair();
        pair.setN1(56);
        pair.setN2(78);
        return webClientBuilder.build().post().uri("http://localhost:8072/test-service-2/add").body(Mono.just(pair),NumberPair.class).retrieve().bodyToMono(Integer.class);
    }


    @GetMapping("percolate")
    public Mono<String> percolate()
    {
        logger.info("request forwarded to service 2");
        Mono<String> response4 =webClientBuilder.build().get().uri("http://localhost:8072/test-service-4/").retrieve().bodyToMono(String.class);
        Mono<String> response2 =   webClientBuilder.build().get().uri("http://localhost:8072/test-service-2/percolate").retrieve().bodyToMono(String.class);
        //return new ResponseEntity<>(response.subscribe().toString(),HttpStatus.OK);
        //return new ResponseEntity<>(response.subscribe().toString(),HttpStatus.OK);
        return response4;
    }




}
