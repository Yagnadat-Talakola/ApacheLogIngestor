package com.challenge.ddos;

import com.challenge.ddos.sources.ApacheLogFileConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        try {
            ApplicationContext ctx = SpringApplication.run(Application.class, args);
            ApacheLogFileConsumer producer = ctx.getBean(ApacheLogFileConsumer.class);
            producer.processMessages();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}