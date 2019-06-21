package com.challenge.ddos.model;

import java.util.Objects;

public class ApacheLogTemplate {

    private String ipAddress;
    private String request;
    private String bytesSent;
    private String browser;
    private String responseStatusCode;
    private String timestamp;

    public ApacheLogTemplate(String ipAddress, String request, String bytesSent, String browser, String responseStatusCode, String timestamp) {
        this.ipAddress = ipAddress;
        this.request = request;
        this.bytesSent = bytesSent;
        this.browser = browser;
        this.responseStatusCode = responseStatusCode;
        this.timestamp = timestamp;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getRequest() {
        return request;
    }

    public String getBytesSent() {
        return bytesSent;
    }

    public String getBrowser() {
        return browser;
    }

    public String getResponseStatusCode() {
        return responseStatusCode;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApacheLogTemplate that = (ApacheLogTemplate) o;
        return Objects.equals(ipAddress, that.ipAddress) &&
                Objects.equals(request, that.request) &&
                Objects.equals(bytesSent, that.bytesSent) &&
                Objects.equals(browser, that.browser) &&
                Objects.equals(responseStatusCode, that.responseStatusCode) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ipAddress, request, bytesSent, browser, responseStatusCode, timestamp);
    }

    @Override
    public String toString() {
        return "ApacheLogTemplate{" +
                "ipAddress='" + ipAddress + '\'' +
                ", request='" + request + '\'' +
                ", bytesSent='" + bytesSent + '\'' +
                ", browser='" + browser + '\'' +
                ", responseStatusCode='" + responseStatusCode + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
