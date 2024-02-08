# Java DropWizard Flyway Starter

Database Migration
---

1. Add the following lines to your ~/.zshrc file:
```
export FLYWAY_URL="jdbc:mysql://YOUR_DB_HOST/YOUR_DB_NAME"
export FLYWAY_USER="YOUR_DB_USER"
export FLYWAY_PASSWORD="YOUR_DB_PASSWORD"
export FLYWAY_BASELINE_ON_MIGRATE=true
```
2. Reload your terminal session if required:
```
. ~/.zshrc
```
3. Run Flyway command through Maven:
```
mvn flyway:migrate
```

How to start the true application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/java-swagger-flyway-starter-org.kainos.ea.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
