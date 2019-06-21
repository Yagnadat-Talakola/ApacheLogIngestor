package com.challenge.ddos.sources;

import com.challenge.ddos.model.ApacheLogTemplate;
import com.challenge.ddos.producer.KafkaMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class ApacheLogFileReader {

    @Autowired
    KafkaMessageSender sender;

    @Autowired
    private LogRegExp regExp;

    private static final Logger logger = LoggerFactory.getLogger(ApacheLogFileReader.class);
    public int LOOP_DELAY = 1000;

    public void pubToKafka() throws FileNotFoundException, IOException {
        logger.info("inside pubToKafka function");
        File f = new File("./apache-access-log.txt");
        BufferedReader in = new BufferedReader(new FileReader(f));

        boolean done = false;
        while(!done) {
            String line = in.readLine();
            if (line == null) {
                logger.error("reached end of file");
                try { Thread.sleep(LOOP_DELAY); }
                catch (InterruptedException e) { System.out.println(e); };
            } else {
                // Process the line.
                logger.debug(line);
                // process each message
                //processMsg(line);
                sender.send(line);
            }
        }
    }

    public ApacheLogTemplate processMsg(String message) {
        // parse the message for ipAddress, timestamp, statusCode and path.
        return regExp.parseLogMsg(message);

    }
}
