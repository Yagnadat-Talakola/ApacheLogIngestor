package com.challenge.ddos.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class AppConfig {

    @Bean
    @ConfigurationProperties("kafka.producer.data")
    public KafkaProducerConfig config() {
        return new KafkaProducerConfig();
    }

    @Bean
    public KafkaProducer getProducer() {
        Properties props = new Properties();

        //Assign localhost id
        props.put("bootstrap.servers", config().getBootstrapServers());

        //Set acknowledgements for producer requests.
        props.put("acks", config().getAck());

        //If the request fails, the producer can automatically retry,
        props.put("retries", config().getRetriesConfig());

        //Specify buffer size in config
        props.put("batch.size", config().getBatchSize());

        props.put("linger.ms", config().getLingerms());

        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        props.put("buffer.memory", config().getBufferMemory());

        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());

        return new KafkaProducer<>(props);

    }
}
