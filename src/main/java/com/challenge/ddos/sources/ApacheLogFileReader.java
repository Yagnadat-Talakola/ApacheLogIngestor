package com.challenge.ddos.sources;

import com.challenge.ddos.model.ApacheLogTemplate;
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
public class ApacheLogFileReader {

    @Autowired
    KafkaMessageSender sender;

    @Autowired
    private LogRegExp regExp;

    private static final Logger logger = LoggerFactory.getLogger(ApacheLogFileReader.class);
    public int LOOP_DELAY = 1000;

    public void pubToKafka() throws IOException {

        logger.info("inside pubToKafka function");
        BufferedReader in = new BufferedReader(new FileReader("./logs"));
        String line;

        while(true) {
            line = in.readLine();
            if (line == null) {
                logger.error("reached end of file");
                try { Thread.sleep(LOOP_DELAY); }
                catch (InterruptedException e) { System.out.println(e); };
            } else {
                logger.debug(line);
                // process each message
                String json = processMsg(line);
                sender.send(json);
            }
        }
    }

    public String processMsg(String message) {
        // parse the message for ipAddress, timestamp, statusCode and path.
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeJsonConverter()).create();
        ApacheLogTemplate obj = regExp.parseLogMsg(message);
        return gson.toJson(obj);
    }
}
