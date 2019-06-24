# Sample application

Technologies:
- JDK 8
- Tomcat (embedded)
- Spring Boot
- Spring Boot Data JPA
- Spring Web
- Jackson
- Lombok
- Junit5

Build: `mvn package`
Run: `java -jar ./target/demoapp-0.0.1-SNAPSHOT.jar` or `mvn spring-boot:run`


### Database
The application should connect to a PostgreSQL database created with [this schema](https://github.com/JorgenRingen/microservice-samples/blob/master/db/init.sql).

[A postgresql docker-image initialised with the schema is available on hub.docker.com](https://hub.docker.com/r/jorgenringen/ms_samples_postgres/). 

Start the container by running: `docker run -p 5432:5432 jorgenringen/ms_samples_postgres`

```
- port: 5432
- database: companies
- username: user
- password: password
```