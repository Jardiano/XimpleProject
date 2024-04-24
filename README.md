
# Ximple Project

The project was created according to the specifications defined by the test.

### Set Up:
To compile and run the application must be executed the following command:

```sh
On Linux:
    ./mvnw spring-boot:run
On Windows:
    mvnw spring-boot:run
```

To execute the unit tests:
```sh
On Linux:
    ./mvnw test
On Windows:
    mvnw test
```

### Endpoints

The endpoints created were:

   | Path | Request Type | Accepted parameters| Description
| ------ | ------ | ------ | ------ | 
|/v1/books | GET | No parameters | List all books registered
|/v1/books/available | GET | No parameters | List only the available books
| /v1/review | GET | No parameters |List all the reviews registered
| /v1/review | POST | RequestBody: reviewDto| Register the review of the book.
|/v1/reservations | POST | RequestParam: bookId - Long, RequestParam: userId - Long | Make the reservation of the book

All these endpoints, models, and specific information of the request, can be found on:

```sh
http://localhost:8080/swagger-ui.html
```

### Technology:
 The project was developed using Java 21 and springboot 3.2.5 with a memory database and swagger to make the documentation of the endpoints.

### Observability:
The java-melody was the choice to make the observability of the project.

The console can be accessed on:

```sh
http://localhost:8080/monitoring
```
 and will be displayed on the screen above.
![alt text](https://github.com/Jardiano/XimpleProject/blob/main/src/main/resources/images/java_melodry_screen.JPG?raw=true)

As can be seen in the image, this option was selected by your facilitation of configuration and the disponibility for a lot of metrics 
that can be useful in analyzing the performance of the application

 ### Caching

 For caching, the spring cache with the caffeine configuration is in three specific points.

 * When is used the service to get all the available books, to avoid multiple requests unnecessary to the database.

 * When is created a new reservation, update the current cache from the books available.

 * In the review data to put the information of the new review in the cache without go to the database.

The valid of the caching was defined as 10 minutes in the application.properties

 ### Database Modeling

 Create a simple model database based on the requirements with objective of simplifying the structure.

![alt text](https://github.com/Jardiano/XimpleProject/blob/main/src/main/resources/diagram/diagram.JPG?raw=true)

 ### Migrations

 For future implementation of the migrations can be used the liquibase to store the scripts of the change and restore the database to a specific point at the time.

 Obs: How was requested to create a strategy without the use of specific tools, probably, I will probably use the script data.sql to manage the updates from the database, versioning this script on git according to the necessity.
