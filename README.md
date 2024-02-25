# Company Management Project
This project help company to manage its resources such as employees, departments, projects 
1. Organising departments
2. Organising employees
3. Organising Projects
4. Organising tasks
5. Organising Meeting
6. Organising Reports

## Technical Information
It's a Restful service API backend developed with [springBoot](https://spring.io/projects/spring-boot/) framework, using the library of dependency management maven.
- The following project using the following java version ([Java 17](ww.oracle.com/java/technologies/javase/jdk17-archive-downloads.html))
- To lean more about maven you can visit the following link [maven](https://maven.apache.org/)

## Docker
This Project is dockerized using [docker](https://www.docker.com/).
- To dockerise this project you must flow this two steps:
  - build the docker image using :
  
    ```` mvn clean install ````
  
    ```` docker build -t company_Managment_Image . ````
  - Run the docker container using: 
  
    ````docker run company_Managment -p 8080:8080 -d company_Managment_Image````
  - Or by running the [docker file](/Users/oumaima/Downloads/demo/Dockerfile)

- To learn more about docker file you can visit the following link [docker file](https://spring.io/guides/topicals/spring-boot-docker/)


## Types

You can format the project using the plugin : 
```` 
<plugin>
      <groupId>com.coveo</groupId>
      <artifactId>fmt-maven-plugin</artifactId>
      <version>2.5.0</version>
      <executions>
          <execution>
                <goals>
                        <goal>format</goal>
                </goals>
          </execution>
      </executions>
</plugin>   
````

## Swagger

The API is documented using [Swagger](https://swagger.io/). You can explore the API documentation by following these [url](https://company-management-ao9h.onrender.com/swagger-ui/index.html)

## Health Check

To check if the service is up, we enable to a GET call to our service via the health check Controller.
Please execute the following command to the check the status of the app ```` curl localhost:8080/healthCheck````

If we receive the msg  ```` companyManagement  ```` then the app is ready to be consulted.
If no msg is received, then a problem has occurred.


## Environment Configuration
To configure your environment and access the database in your project, you need to create a .env in the root directory of your project. 
Inside the .env file, define the necessary environment variables required for accessing the database. These include database connection details such database url, username and password.
Then, your environment will be configured to access the database using the provided environment variables.

## Test
Test application is supported by unit tests and integration tests.

Unit tests are found under : src/test/java/com/example/demo
 - The unit tests mocks all dependencies and focus only on the behaviour of the current class in isolation.

Integration tests are found under: src/test/java/com/example/demo/useCase
 - The integration tests focus in the behaviour of end to end interaction.
 
