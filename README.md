# notes
web application for managing notes written in Java 17, Spring 3, Thymeleaf

How to start application:
- Clone this repository
- In terminal run: mvn spring-boot:run in project directory
- access: localhost:8080


Specification:
internal h2 db with auto create on startup with import script
tomcat server, slf4j as logger unit tests in junit
form validation: title not blank and matches format: [a-zA-Z], content not blank and matches format: [a-zA-Z0-9s]
