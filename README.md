
# Ximple Project

The project was created according with the specifications defined by the test.

### Set Up:
To compile and run the application must be executed the following command:

```sh
On Linux:
    ./mvnw spring-boot:run
On Windows:
    mvnw spring-boot:run
```

To excecute the unit tests:
```sh
On Linux:
    ./mvnw test
On Windows:
    mvnw test
```

### Endpoints

The endpoints created was:

   | Path | Request Type | Accepted parameters| Description
| ------ | ------ | ------ | ------ | 
|/v1/books | GET | No parameters | List all books registered
|/v1/books/available | GET | No parameters | List only the available books
| /v1/review | GET | No parameters |List all the reviews registered
| /v1/review | POST | RequestBody: reviewDto| Register the review of the book.
|/v1/reservations | POST | RequestParam: bookId - Long, RequestParam: userId - Long | Make the reservation of the book
|

All this endpoints, models and specific information of the request, can be found on:

```sh
http://localhost:8080/swagger-ui.html
```

### Technology:
 The project was developed using java 21 and springboot 3.2.5 with a memory database and swagger to make the documentation of the endpoints.

### Observability:
The java-melody was the choice to make the observability of the project.

The console can be acessed on:

```sh
http://localhost:8080/monitoring
```
 and will be displayed the screen above.
[PUT IMAGE HERE]

 ### Caching

 For caching was used the spring cache with the caffeine configuration in three specifc points.

 * When is used the service to get all the available books, to avoid multiple requests unecessary to the database.

 * When is created a new reservation, to update the current cache from the books availables.

 * In the review data to put the information of the new review in the cache without go to the database.

The validation of the caching was defined to 10 minutes in the application.properties

 ### Database Modeling

 Was create a simple model database based on the requirements with objective to simplify the structure.

 [PUT IMG ER MODEL HERE]

 ### Migrations

 For future implementation of the migrations can be used the liquibase to store the scripts of the change and restore the database to a specific point at time.

 Obs: How was request to create a strategy without use of a specifc tools, probably, I was used the script data.sql to mange the updates from the database, versioning this script on git according of the necessity.
