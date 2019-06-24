### Apache Log File Ingestor ###
* Convert apache log file entry to JSON format before publishing to Kafka

```
Example:
194.70.52.190 - - [25/May/2015:23:11:52 +0000] "GET / HTTP/1.0" 200 3557 "-" "Opera/9.00 (Windows NT 5.1; U; en)"

After parsing, extract the required fields to JSON format.
{
    "ip_address":"194.70.52.190",
    "request":"GET / HTTP/1.0",
    "bytes_sent":"3557",
    "browser":"Opera/9.00 (Windows NT 5.1; U; en)",
    "response_status_code":"200",
    "timestamp":"25/May/2015:23:11:52"
}
```

* Sample Data Generation
```
    cd sample-data
    ./log_entries_gen.sh ipAddress timeStamp statusCode repeatNum
    
    The above script will create log entries in Apache log format with the given IP Address, statusCode, timestamp.  You can also repeat the log entries in the file using last param.
    
    Example:
    ./log_entries_gen.sh 10.90.80.100 '25\/May\/2015:23:11:53' 200 1000

    Normal Traffic pattern:  cd sample-data/usecase1 
    DDOS Traffic pattern: cd sample-data/usecase2
    Normal + DDOS Traffic pattern: cd sample-data/usecase3
   
```

* Environment 
```
   docker-compose.yml:
   1. Kafka container - running on port 9092
   2. Zookeeper container - running on port 2181

   docker-compose up 
   docker-compose down
 
```

### An approach to tackle a Distributed Denial of Service (DDoS) attack

**Concepts**:

>DoS and DDoS
---
A Denial of Service(DoS) attack is a malicious attempt to affect the availability of a targeted system, such as a website or application, to legitimate end users. Typically, attackers generate large volumes of packets or requests ultimately overwhelming the target system. In the case of DDoS attack, the attacker uses multiple compromised or controlled sources to generate the attack.
>
>Attack targets
---
In the Open Systems Interconnection(OSI) model, the most common attack targets include:
>
**Infrastructure layer attacks**
* Network (layer 3)
* Transport (layer 4)
>
**Application layer attacks**
* Presentation (layer 6)
* Application (layer 7)

>Some approaches to identify and deal with DDOS attacks:
* *Normal vs Abnormal traffic*: setup a baseline traffic (also called *rate limiting*). This is the traffic that the host(s) can handle without affecting availability. Anything above this is abnormal and possibly an attack scenario.   

* *filtering traffic*: identify illegitimate requests which could have characteristics like disguising as good traffic or coming from *bad IPs*, unexpected geographies etc. Blocking this even before it reaches the server.

* Monitoring visitor behavior, blocking known bots, challenging suspicious behavior or unrecognized entities with JS test, Cookie challenge and even CAPTCHAs

**References**
1. https://aws.amazon.com/shield/ddos-attack-protection/
2. https://www.imperva.com/learn/application-security/ddos-attacks/
