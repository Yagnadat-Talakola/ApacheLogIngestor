package com.challenge.ddos.sources;

import com.challenge.ddos.model.ApacheLogEntry;
import com.challenge.ddos.model.LocalDateTimeJsonConverter;
import com.challenge.ddos.producer.KafkaMessageSender;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDateTime;

@Component
public class ApacheLogFileConsumer {

    @Autowired
    private KafkaMessageSender sender;

    @Autowired
    private ApacheLogParserImpl parser;

    private static final Logger logger = LoggerFactory.getLogger(ApacheLogFileConsumer.class);
    private int LOOP_DELAY = 1000;

    public void processMessages() throws InterruptedException, IOException {

        BufferedReader in = new BufferedReader(new FileReader("./logs"));
        String logLine;

        while (true) {
            logLine = in.readLine();
            if (logLine == null) {
                logger.error("reached end of file");
                Thread.sleep(LOOP_DELAY);
            } else {
                String jsonFormatLogEntry = parseMessage(logLine);
                sender.send(jsonFormatLogEntry);
            }
        }
    }

    public String parseMessage(String message) {
        // parse the message for ipAddress, timestamp, statusCode and path.
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeJsonConverter()).create();
        ApacheLogEntry obj = parser.parse(message);
        return gson.toJson(obj);
    }
}
