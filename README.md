# Portfolio Spring Backend
Backend for Portfolio, created using Java Spring Boot, with Web template.

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
