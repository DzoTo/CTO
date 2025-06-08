Hi, here is the step-by-step guide for this project:

1) Clone the project
2) Run Docker application, then run command in terminal: docker compose up -d
3) Run application manually in Intellij IDE
4) http://localhost:8080/swagger-ui/index.html here is simple swagger documentation for all endpoints, however require to use postman:)
5) Initially, create user by registering and login for further endpoints


Project has Flyway migrations:
I have added admin user via migration, since only admin can make updates on request.

Application has simple SMTP imitation which will send email to contact email(iside request) about updates of status.
Therefore, write real emails during the creation of request.

As well, there is implementation of simple security with authentication and authorization with role based permitions.

This project has been done in short time and might have some issues and error. 