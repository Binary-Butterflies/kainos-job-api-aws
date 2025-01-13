# Kainos Job API

The backend for the Kainos Job Roles Application

---

## Migrations

The application uses Flyway to carry out the database migration scripts.

### Environment Variables

The following environment variables are required to run the migrations:

```bash
FLYWAY_URL=jdbc:mysql://{DB_URL}/{DB_NAME}
FLYWAY_USER={YOUR_USERNAME}
FLYWAY_PASSWORD={YOUR_PASSWORD}
FLYWAY_BASELINE_ON_MIGRATE=true
```

**Note:** Ensure you replace the values inside of the curly braces.

### Running Migrations

To run the migrations, execute the following command:

```bash
mvn flyway:migrate
```

## Building

The recommended way to build and run the project is using IntelliJ IDEA

### Environment File

To run the application, please create a `.env` file in the root of the project based on the `.env.template` file.

### Configuration

Please create a new "Application" run configuration with the following parameters:

- **Main Class:** `org.example.TestApplication`
- **Program Arguments:** `server`
- **Environment variables:** Path to `.env` file 

**__Note:__** If there are issues using a local path, try using the absolute path instead.

### Running

To run the application, execute the run configuration. The api can be found at [`http://localhost:8080`](http://localhost:8080)

This application also hosts a Swagger page, which can be found at [`http://localhost:8080/swagger`](http://localhost:8080/swagger)

## Health Check

The current health of the application can be found at [`http://localhost:8081/healthcheck`](http://localhost:8081/healthcheck)

## Testing

The application uses JUnit as its testing suite.

### Configuration

Please create a new "JUnit" run configuration with the following parameters:

- **Resource Type:** "All in package" (Found in the dropdown)
- **Package:** `org.example`
- **Environment variables:** Semicolon-separated list of the environment variables

**Note:** Currently, you cannot supply a path to your `.env` file for running tests.