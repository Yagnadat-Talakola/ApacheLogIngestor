package com.challenge.ddos.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    @ConfigurationProperties("kafka.producer.data")
    public KafkaProducerConfig getConfig() {
        return new KafkaProducerConfig();
    }

}
