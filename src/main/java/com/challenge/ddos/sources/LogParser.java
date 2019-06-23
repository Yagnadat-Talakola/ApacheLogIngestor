package com.challenge.ddos.sources;

import com.challenge.ddos.model.ApacheLogEntry;

import java.time.format.DateTimeFormatter;

public interface LogParser {

    // Number of fields to be found.
    int NUM_FIELDS = 9;

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss");

    ApacheLogEntry parse(String logEntry);
}
