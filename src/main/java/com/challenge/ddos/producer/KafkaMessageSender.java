package com.challenge.ddos.producer;

import com.challenge.ddos.config.KafkaProducerConfig;
import com.challenge.ddos.model.ApacheLogEntry;
import com.challenge.ddos.model.LocalDateTimeJsonConverter;
import com.challenge.ddos.sources.ApacheLogParserImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Properties;

@Component
public class KafkaMessageSender {

    private static final Logger logger = LoggerFactory.getLogger(KafkaMessageSender.class);

    @Autowired
    private KafkaProducerConfig config;

    @Autowired
    private ApacheLogParserImpl parser;

    @Value("${kafka.producer.data.topic}")
    private String topicName;

    public void send(ApacheLogEntry entry) {
        Properties props = new Properties();

        //Assign localhost id
        props.put("bootstrap.servers", config.getBootstrapServers());

        //Set acknowledgements for producer requests.
        props.put("acks", config.getAck());

        //If the request fails, the producer can automatically retry,
        props.put("retries", config.getRetriesConfig());

        //Specify buffer size in config
        props.put("batch.size", config.getBatchSize());

        props.put("linger.ms", config.getLingerms());

        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        props.put("buffer.memory", config.getBufferMemory());

        props.put("key.serializer", config.getKeySerializer());

        props.put("value.serializer", config.getValueSerializer());

        Producer<String, String> producer = new KafkaProducer<>(props);

        producer.send(new ProducerRecord<>(topicName, entry.getIpAddress(), getAsJSON(entry)));
        producer.close();
    }

    public String getAsJSON(ApacheLogEntry entry) {
        // parse the message for ipAddress, timestamp, statusCode and path.
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeJsonConverter()).create();
        return gson.toJson(entry);
    }
}