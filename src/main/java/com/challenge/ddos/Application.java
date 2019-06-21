package com.challenge.ddos;

import com.challenge.ddos.sources.ApacheLogFileReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        try {
            ApacheLogFileReader producer = ctx.getBean(ApacheLogFileReader.class);
            producer.pubToKafka();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}