### Apache Log File Ingestor ###
* Apache Log File -> Kafka Message Format

```
194.70.52.190 - - [25/May/2015:23:11:52 +0000] "GET / HTTP/1.0" 200 3557 "-" "Opera/9.00 (Windows NT 5.1; U; en)"


{
    "ip_address":"10.90.80.1111",
    "request":"GET / HTTP/1.0",
    "bytes_sent":"3557",
    "browser":"Mozilla/3.01 (compatible;)",
    "response_status_code":"503",
    "timestamp":"25/May/2015:23:11:50"
}
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


>Problem Context:
>Attacked by botnet. Need a real-time detector for DDoS attacks.

#### Business Rules:
0. Ingest from a Apache log file.
0. Reading messages from messaging system. (Kafka)
1. Should be detected within 1 to 2 minutes after application starting.
2. IP address should be returned to results directory.
3. Present the solution and approach taken.

> To Answer:
1. How to scale up in a distributed environment?

**References**
1. https://aws.amazon.com/shield/ddos-attack-protection/
2. https://www.imperva.com/learn/application-security/ddos-attacks/
