# TelegramBot

### Technologies: 
Spring Boot, Spring Data, Hibernate, JPA, REST, Spring MVC, MySQL, JUnit, Mockito, Maven
### Java version: 14

### Start the app:
com.resliv.task.TelegramBotApplication 

### Bot settings:
src/resourses/bot.properties
### App settings:
src/resourses/application.properties
### SQL script:
src/resourses/script.sql


### TelegramBot
Username: @BeluzhkaBot
Token: 1395499760:AAHrUXVGuAROo6EWy-1oIMR6cISCmirKCJQ

### REST

> - Get all cities: GET http://localhost:8080/cities/ 
> - Get city by id: GET http://localhost:8080/cities/id
> - Get city by name: GET http://localhost:8080/cities/city/name

> - Add new city: POST http://localhost:8080/cities/city
> - Add new cities: POST http://localhost:8080/cities/cities

> - Update city: PUT http://localhost:8080/cities/id

> - Delete city by id: DELETE http://localhost:8080/cities/id
> - Delete city by name: DELETE http://localhost:8080/cities/city/name
> - Delete all cities: DELETE http://localhost:8080/cities/
