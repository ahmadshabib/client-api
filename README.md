# OpenPayd Challange
[![Build Status](https://github.com/ahmadshabib/client-api/workflows/Java%20CI%20with%20Maven/badge.svg?branch=master)](https://github.com/ahmadshabib/client-api)
[![codecov](https://codecov.io/gh/ahmadshabib/client-api/branch/master/graph/badge.svg)](https://codecov.io/gh/ahmadshabib/client-api)

This repository has the proposed solution for the first assignment of recruitment process.

## Architecture

The project is divided into one maven module:
- `client-api`: includes the both abstract classes and interfaces and the implementations that is used by implementation module.

The project is following `DDD` structure.

`google-java-format` was used to format the code
[Google Java Style][].

[Google Java Style]: https://google.github.io/styleguide/javaguide.html

## Prerequisite

- Maven 3.5+.
- Java 8.

## Build

In order to get the application running you can go with one of the available options:
*** Note: 
Make sure that you are under the project directory

- ### Maven:
    - First you should run:
     
      ```
      mvn -B -T 4C clean install
      ```
    
    - Then:
        
      ```
      docker run -d --name dev-postgres -e POSTGRES_USER=user -e POSTGRES_HOST=postgres-db -e POSTGRES_PASSWORD=Pass2020! -v ${HOME}/postgres-data/:/var/lib/postgresql/data -p 5432:5432 postgres
      ```  
          
    - Then:
    
      ```
      java -jar ./target/clientEntity-api-0.0.1-SNAPSHOT.jar
      ```
      
- ### run.sh Script:
     Using the script requires that you have java and maven already installed
     then based on your operating system you either run
     - `run.sh` for linux.
     - `run_win.sh` for windows.
     
- ### Docker:
     To use docker we need first to build the image:      
     
     - Using the following command:
     
       ```
       docker-compose up --build
       ```
           
## Usage

If you've followed the build phase you should have the server up and running on port 8080,
And you can target the following end points:

- `POST /api/account`: Create a new account.
- `GET /api/accounts/{accountId}`: Get an account based on ID.
- `GET /api/client/{clientId}`: Get a client based on ID.
- `POST /api/client`: Create a new Client.
- `GET /api/clients`: List of all clients.
- `GET /api/transfer`: Creation of transaction of money.
- `GET /api/transfers/{accountId}`: List of all transactions for a specific account.

Postman collection is available and named as: 
client-api.postman_collection.json

## Notes

***Github actions pipeline*** was used as well

***Dependency enforcer and checker*** were both used.

***Checkstyle*** dependency is being used as well.