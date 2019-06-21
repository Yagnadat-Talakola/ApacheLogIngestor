package com.challenge.ddos;

import com.talakola.kafka.producer.ShipmentTransitProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        ShipmentTransitProducer producer = ctx.getBean(ShipmentTransitProducer.class);
        producer.produce();
    }
}