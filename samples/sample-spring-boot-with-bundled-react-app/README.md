# Spring Boot with bundled react-app

Demonstrates how to use the [frontend-maven-plugin](https://github.com/eirslett/frontend-maven-plugin)
to bundle a js-webapp (react) inside an application jar.

##### Build backend app
`mvn clean install`

##### Build and start frontend app separately
```shell 
cd src/main/webapp
yarn build
yarn start
``` 

##### Build backend and frontend together
```
mvn clean install -Pbuild-webapp
```

##### Start application
```
java -jar target/*.jar
```