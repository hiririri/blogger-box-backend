## Projet Blogger Box Backend

Pour lancer l'application, veuillez changer les informations de Base de Données en `application.yml` : 
```sh
  datasource:
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
```
