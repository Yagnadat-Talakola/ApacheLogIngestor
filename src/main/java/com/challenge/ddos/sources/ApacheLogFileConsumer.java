package com.challenge.ddos.sources;

import com.challenge.ddos.model.ApacheLogEntry;
import com.challenge.ddos.producer.KafkaMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class ApacheLogFileConsumer {

    @Autowired
    private KafkaMessageSender sender;

    @Autowired
    private ApacheLogParserImpl parser;

    @Value("${source.file.name}")
    private String sourceFile;

    private static final Logger logger = LoggerFactory.getLogger(ApacheLogFileConsumer.class);
    private static int LOOP_DELAY = 5000;

    public void processMessages() throws Exception {

        logger.info("reading from file {}", sourceFile);
        BufferedReader in = new BufferedReader(new FileReader(sourceFile));
        String logLine;

        while (true) {
            logLine = in.readLine();
            if (logLine == null) {
                logger.info("reached end of file - waiting for more logs");
                Thread.sleep(LOOP_DELAY);
            } else {
                ApacheLogEntry entry = parseMessage(logLine);
                sender.send(entry);
            }
        }
    }

    public ApacheLogEntry parseMessage(String message) {
        // parse the message for ipAddress, timestamp, statusCode and request details.
        return parser.parse(message);
    }
}
