package com.challenge.ddos.sources;

import com.challenge.ddos.model.ApacheLogTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class LogRegExp implements LogInterface {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss");
    private static final Logger logger = LoggerFactory.getLogger(ApacheLogFileReader.class);

    public ApacheLogTemplate parseLogMsg(String logEntry) {

        String logEntryPattern = "^([\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"([^\"]+)\"";

        Pattern p = Pattern.compile(logEntryPattern);
        Matcher matcher = p.matcher(logEntry);
        if (!matcher.matches() || NUM_FIELDS != matcher.groupCount()) {
            //log an error and ignore any unexpected log format messages
            logger.error("Bad log entry - unable to parse apache logs");
            throw new RuntimeException("bad entry " + NUM_FIELDS);
        }

        return new ApacheLogTemplate(matcher.group(1), matcher.group(5), matcher.group(7), matcher.group(9), matcher.group(6),
                LocalDateTime.parse(matcher.group(4).split(" ")[0], dateTimeFormatter));
    }
}
