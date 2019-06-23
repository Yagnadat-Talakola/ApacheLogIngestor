package com.challenge.ddos.sources;

import com.challenge.ddos.model.ApacheLogEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ApacheLogParserImpl implements LogParser {

    private static final String logEntryPattern = "^([\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"([^\"]+)\"";

    @Override
    public ApacheLogEntry parse(String logEntry) {

        Pattern p = Pattern.compile(logEntryPattern);
        Matcher matcher = p.matcher(logEntry);
        if (!matcher.matches() || NUM_FIELDS != matcher.groupCount()) {
            throw new RuntimeException("Unable to parse the log entry: " + logEntry);
        }

        return new ApacheLogEntry(matcher.group(1),
                matcher.group(5),
                matcher.group(7),
                matcher.group(9),
                matcher.group(6),
                LocalDateTime.parse(matcher.group(4).split(" ")[0], dateTimeFormatter));
    }

}
