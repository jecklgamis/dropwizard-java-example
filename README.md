## Dropwizard Java Example

[![Build Status](https://travis-ci.org/jecklgamis/dropwizard-java-example.svg?branch=master)](https://travis-ci.org/jecklgamis/dropwizard-java-example)

This is an example Dropwizard app using Java.

## Building The App
Ensure you Java 8 or later, and Maven 3 installed.
```
mvn clean package
```
## Running The App
```
java -jar target/dropwizard-java-example.jar server src/main/resources/config.yml
```

## Running The App In Docker

```
docker build -t dropwizard-java-example .
docker run dropwizard-java-example
```

## GET, PUT, POST, DELETE Examples

PUT Request
```
curl -v -X PUT -H "Content-Type:application/json" "http://localhost:8080/user" -d'{"username":"me", "email":"me@example.com"}'
```

POST Request
```
curl -v -X POST -H "Content-Type:application/json" "http://localhost:8080/user" -d'{"username":"me", "email":"me@example.com"}'
```

GET Request
```
curl -v -X GET -H "Content-Type:application/json" "http://localhost:8080/user?username=me"
```

DELETE Request
```
curl -v -X DELETE -H "Content-Type:application/json" "http://localhost:8080/user?username=me"
```







