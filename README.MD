### An approach to tackle a Distributed Denial of Service (DDOS) attack

**Concepts**:

>DOS and DDOS
---
A Denial of Service(DoS) attack is a malicious attempt to affect the availability of a targeted system, such as a website or application, to legitimate end users. Typically, attackers generate large volumes of packets or requests ultimately overwhelming the target system. In the case of DDOS attack, the attacker uses multiple compromised or controlled sources to generate the attack.
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

* identify illegitimate requests which could have characteristics like disguising as good traffic or coming from *bad IPs*, unexpected geographies etc.


**References**
1. https://aws.amazon.com/shield/ddos-attack-protection/
2.
