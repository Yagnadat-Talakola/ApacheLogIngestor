package com.challenge.ddos.producer;

import com.challenge.ddos.config.KafkaProducerConfig;
import com.challenge.ddos.model.ApacheLogEntry;
import com.challenge.ddos.model.LocalDateTimeJsonConverter;
import com.challenge.ddos.sources.ApacheLogParserImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class KafkaMessageSender {

    private static final Logger logger = LoggerFactory.getLogger(KafkaMessageSender.class);

    @Autowired
    private KafkaProducerConfig config;

    @Autowired
    private KafkaProducer producer;

    @Value("${kafka.producer.data.topic}")
    private String topicName;

    public void send(ApacheLogEntry entry) throws Exception {
        final ProducerRecord<String, String> record = new ProducerRecord<>(topicName, entry.getIpAddress(), getAsJSON(entry));

        //send messages synchronously
        //producer.send(record).get();

        //send messages asynchronously + callback (much faster than sync)
        producer.send(record, (metadata, ex) -> {
           if(metadata != null) {
               logger.info("Successfully sent message to meta(partition={}, offset={})", metadata.offset(), metadata.partition());
           } else {
               logger.error("Error sending messages {}", record);
           }
        });
    }

    public String getAsJSON(ApacheLogEntry entry) {
        // parse the message for ipAddress, timestamp, statusCode and path.
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeJsonConverter()).create();
        return gson.toJson(entry);
    }

}