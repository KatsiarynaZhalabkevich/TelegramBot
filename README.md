# TelegramBot

### Technologies: 
> Spring Boot, Spring Data, Hibernate, JPA, REST, Spring MVC, MySQL, JUnit, Mockito, Maven
### Java version: 14

### Start the app:
> com.resliv.task.TelegramBotApplication 
## Main Settings
### Bot:
> src/main/resourses/bot.properties
### App:
> src/main/resourses/application.properties
### SQL:
> src/main/resourses/script.sql

## Test Settings
### App:
> src/test/resourses/application.properties
### SQL scripts:
> src/test/resourses/create-city-before.sql 
> src/test/resourses/create-city-after.sql



### TelegramBot
> - Username: @BeluzhkaBot
> - Token: 1395499760:AAHrUXVGuAROo6EWy-1oIMR6cISCmirKCJQ

### REST
> JSON file for requests: src/main/resourses/validJsonFile.json
> - Get all cities: GET http://localhost:8080/cities/ 
> - Get city by id: GET http://localhost:8080/cities/id
> - Get city by name: GET http://localhost:8080/cities/city/name

> - Add new city: POST http://localhost:8080/cities/city
> - Add new cities: POST http://localhost:8080/cities/cities

> - Update city: PUT http://localhost:8080/cities/id

> - Delete city by id: DELETE http://localhost:8080/cities/id
> - Delete city by name: DELETE http://localhost:8080/cities/city/name
> - Delete all cities: DELETE http://localhost:8080/cities/
