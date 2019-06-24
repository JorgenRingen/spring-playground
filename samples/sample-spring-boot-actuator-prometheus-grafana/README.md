##### Tutorial
https://www.callicoder.com/spring-boot-actuator-metrics-monitoring-dashboard-prometheus-grafana/

##### maven dep

```xml
<dependency>
	<groupId>io.micrometer</groupId>
	<artifactId>micrometer-registry-prometheus</artifactId>
</dependency>
```

Adds PrometheusMeterRegistry and CollectorRegistry which exports metrics
data in a format that can be scraped by a Prometheus server.

`localhost:8080/actuator/prometheus`

##### Prometheus

```
1. docker pull prom/prometheus
2. replace ip in prometheus.yml with local ip (ifconfig | grep netmask | grep broadcast)
3. replace path to prometheus.yml with local path ($PWD/prometheus.yml)
4. docker run -d --name=prometheus -p 9090:9090 -v /Users/jorgen.ringen/workspace/spring-playground/samples/sample-spring-boot-actuator-prometheus-grafana/prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus --config.file=/etc/prometheus/prometheus.yml
5. http://localhost:9090
```

##### Grafana

```
1. docker run -d --name=grafana -p 3000:3000 grafana/grafana
2. http://localhost:3000
3. admin/admin
```