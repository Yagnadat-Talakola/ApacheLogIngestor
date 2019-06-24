package com.challenge.ddos.config;

public class KafkaProducerConfig {

    private String bootstrapServers;
    private String topic;
    private String keySerializer;
    private String valueSerializer;
    private String ack;
    private String retriesConfig;
    private String lingerms;
    private String batchSize;
    private String bufferMemory;
    private String maxInflightConnections;

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public String getTopic() {
        return topic;
    }

    public String getKeySerializer() {
        return keySerializer;
    }

    public String getValueSerializer() {
        return valueSerializer;
    }

    public String getAck() {
        return ack;
    }

    public String getRetriesConfig() {
        return retriesConfig;
    }

    public String getLingerms() {
        return lingerms;
    }

    public String getBatchSize() {
        return batchSize;
    }

    public String getBufferMemory() {
        return bufferMemory;
    }

    public String getMaxInflightConnections() {
        return maxInflightConnections;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setKeySerializer(String keySerializer) {
        this.keySerializer = keySerializer;
    }

    public void setValueSerializer(String valueSerializer) {
        this.valueSerializer = valueSerializer;
    }

    public void setAck(String ack) {
        this.ack = ack;
    }

    public void setRetriesConfig(String retriesConfig) {
        this.retriesConfig = retriesConfig;
    }

    public void setLingerms(String lingerms) {
        this.lingerms = lingerms;
    }

    public void setBatchSize(String batchSize) {
        this.batchSize = batchSize;
    }

    public void setBufferMemory(String bufferMemory) {
        this.bufferMemory = bufferMemory;
    }

    public void setMaxInflightConnections(String maxInflightConnections) {
        this.maxInflightConnections = maxInflightConnections;
    }

    @Override
    public String toString() {
        return "KafkaProducerConfig{" +
                "bootstrapServers='" + bootstrapServers + '\'' +
                ", topic='" + topic + '\'' +
                ", keySerializer='" + keySerializer + '\'' +
                ", valueSerializer='" + valueSerializer + '\'' +
                ", ack='" + ack + '\'' +
                ", retriesConfig='" + retriesConfig + '\'' +
                ", lingerms='" + lingerms + '\'' +
                ", batchSize='" + batchSize + '\'' +
                ", bufferMemory='" + bufferMemory + '\'' +
                ", maxInflightConnections='" + maxInflightConnections + '\'' +
                '}';
    }

}