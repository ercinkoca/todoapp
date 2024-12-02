#### TODOAPP APPLICATION
This project is a TO-DO app. It is like users can create their own Todolists.

In the application, users should be able to register and create their own to-do
lists.

#### Technology Stack:

    • Java 17
    • Spring Boot
    • Spring Data Couchbase
    • Couchbase
    • Swagger
    • Maven
    • Docker
    • JUnit and Mockito

#### To Use Couchbase Docker Image:

    docker pull couchbase
    docker run -d --name db -p 8091-8094:8091-8094 -p 11210:11210 couchbase

#### Couchbase Local Information

    clusterName --> todoapp
    admin username --> admin
    pass --> root123456
    local cocuhbase server ui --> http://localhost:8091/ui/index.html

#### Swagger Information

    http://localhost:8080/swagger-ui/index.html

#### To Use Swagger: 

    You should send this request to create new user at server,this is the curl script:

    curl --location --request POST 'http://localhost:8080/admin/createUser' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "firstName":"String",
    "lastName": "String",
    "email":"String",
    "password": "String",
    "confirmPassword": "String",
    "phone": "String"
    }'

    After that, you should login the application, this is the curl script:

    curl --location --request POST 'http://localhost:8080/auth/login' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "email":"String",
    "password": "String"
    }'

    After user login, you can user swagger-ui and the other endpoints.

    Swagger Local UI: http://localhost:8080/swagger-ui/index.html

#### Run Application On Docker: 

    docker build -t jre-build-example .
    docker run -p 8080:8080 jre-build-example
