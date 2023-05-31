# Portfolio Spring Backend
Backend for Portfolio, created using Java Spring Boot, with Web template.

## About the project 
- This project uses following technology:
   - Spring Boot Starter Web / Data JPA
   - Java 17 Eclipse
   - Jakarta Persistence API
   - JDBC for PostgreSQL

### Prerequisites

* Java 17 Eclipse
* PostgreSQL (Developed & tested only with version PostgreSQL 15.3)

### Setting up

* Install / set up Java 17, and isntall and set up PostgreSQL server with database and user that can be connected to this project
* Create env.properties file, and place it to project root with following content (make sure the PostgreSQL port is correct):
 ```sh
DB_DATABASE=//localhost:5432/{database name here}
DB_USER= database username
DB_PASSWORD= database password
 ```
* Build the project in IDE (Tested only on IntelliJ IDEA 2022.3.3 Ultimate)
* The project will create necessary tables, columns and indexes based on the models/entities defined in the project "Modules" package when it's run first time.
* The Tomcat Web Server will open at http://localhost:8080

## The component architechture is structured so that the application consists of
- Controllers
- Services
- Repositories
- Models

## Controller classes 
- Will handle the incoming HTTP requests, and process and validate them, and send appropriate responses.
- Will extract the data to populate model classes

## Service classes
- Includes the "business" logic
- Coordinate operations between repositories
- Data validation & transofrmation
- Pass data to repositories 
- Data is processed and transferred using model classes

## Repository classes
- Handle the data access to and from the database
- Mapping between DB entities & Java objects through hibernate
- CRUD and application data transfer operations are done using model classes

## Model classes
- Represent the data objects throughout the different Spring layers
- Are containers for data transfer
- Provide mapping between DB schema and object-oriented representation

# Things still missing
- [] Faster search queries for bike trip table
- [] DB calls for getting the statistics / data related to single stations
- [] Meaningful tests

