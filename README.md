# Ultimate AI

Ultimate AI is an application to serve customers as a chat bot.

## Technolojies Used

* Spring Boot 2
* Java 8
* Maven 3
* Mongo Db Atlas
* OpenApi - Swagger
* Lombok
* Mockito

### Requirements

* Maven 3
* Jdk 8

## Test and Run

Run the following command to test and run the application;

```console
cd ultimate-ai && mvn clean install && cd ultimate-ai-app && mvn spring-boot:run
```

Or run the main class on an IDE after maven clean install(generated code is not commited.)

## Access

Chat bot can be accessed on;

* http://localhost:9090/swagger-ui.html#

Or post can be applied directly on;

* http://localhost:9090/chat-bot

## Clean Code Actions

* Annotations are used to keep the code simple.
* An api module is created to keep the generated code isolated and manage dependencies seperately.
* IntentApiService is written in api module to prevent generated code to cause test failures in app module.
* Constants are located in the same class on purpose to prevent a big mess constant class.
* Sonarlint is used and issues are handled.

## Choices and Reasons

* For database, I came up with two solutions as an online database or Docker Compose with a local one. I used Mongo Db
  Atlas to be able to connect from anywhere. It was a simpler and lighter solution.
* For confidence treshold, I thought what matters is how better is the best intent from the second intent, the other
  intents makes no difference. I observed that when I send "goodbye" as request, AI returns "Thank you" with confidence
  1 and "Goodbye" with confidence 0.81. So I defined a configurable fallibility percentage and set it to 20%.

## What Could Be Done More

* ApiKey could be encrypted an passed as an argument.
* BotId pattern could be validated for differentiated error messages.
* Controller could be tested as mvc in case of http status of the response matters.
* In a bigger project, interfaces of the components could be used as contract.
* In a bigger project, -data , -service, -web modules would be created to separate dependencies and test needs.