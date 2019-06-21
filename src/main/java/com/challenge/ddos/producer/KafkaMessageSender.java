package com.challenge.ddos.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageSender {

    private static final Logger logger = LoggerFactory.getLogger(KafkaMessageSender.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.producer.data.topic}")
    private String topicName;

    public void send(String json) {
        logger.info(json);
        kafkaTemplate.send(topicName, json);
    }
}