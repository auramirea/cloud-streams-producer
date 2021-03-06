package com.test.producer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service
@EnableBinding(Source.class)
@EnableScheduling
public class Producer {

    public final Source source;

    public Producer(Source source) {
        this.source = source;
    }

    //@SendTo(Source.OUTPUT)
    @Scheduled(fixedRate = 200)
    public void produce() {
//        String letters = "abcdef";
//        String message = "message " + letters.charAt(new Random().nextInt(letters.length()));
        double number = Math.random() * 20;
        Marketcap marketcap = new Marketcap(number);
        System.out.println("Sending message " + marketcap);
        source.output().send(new GenericMessage<>(marketcap));
    }

}
