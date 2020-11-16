# items-on-sale
This is an API implemented by using Spring boot framework and maven for dependency management. 
The API recommends items on sale to a valid user/consumer on a shopping website.


##Running the MYSQL database container
1. `cd` into the project root directory, and
2. run `docker-compose up -d`

#Building the service
1. `cd` into the project root directory, and
2. Make sure the MySQL docker container is up and running. If not, run `docker-compose up -d`.
3. Run the following command:
    ```
    mvn package
    ```

##Running the service
1. `cd` into the project root directory, and
2. Make sure the MySQL docker container is up and running.  If not, run `docker-compose up -d`.
3. Run the following command:
    ```mvn spring-boot:run```

##Running the tests
1. `cd` into the project root directory, and
2. Make sure the MySQL docker container is up and running. If not, run `docker-compose up -d`.
3. Run the following command:
    ```mvn spring-boot:test```

##Sending Requests to the API Endpoints
The API has the following endpoints:
1. An authentication POST endpoint: /authenticate
2. A greetings GET endpoint: /welcome
3. An endpoint for recommending items to a user: /recommendations/{id}

### Authentication Endpoint
- The API is secured to only allow calls from "rbc.shopping.com" domain. If the IP of the domain cannot be found, by default, 
the API is set to allow calls from "localhost".
- The API is secured to only allow consumers that have valid JWT tokens when they call the API. 
If a consumer does not have a valid token, it will receive a 403 forbidden error.
- To POST a request to the authentication endpoint:
```
    curl --location --request POST 'http://localhost:8080/authenticate' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "username": "default",
        "password": "0000"
    }'
```
- The token has a 2-hour expiry time

### Greetings Endpoint
Use the token recieved from the authentication endpoint to send a GET request as follows:
```
    curl --location --request GET 'localhost:8080/welcome' \
    --header 'Authorization: Bearer TOKEN' \
    --data-raw ''
```

### Recommenations Endpoint
Use the token recieved from the authentication endpoint to send a GET request as follows:
```
curl --location --request GET 'localhost:8080/recommendations/1/' \
--header 'Authorization: Bearer TOKEN'
```

** NOTE: Currently, the API is configured to have one "default" user with `{id}` value of `1`.