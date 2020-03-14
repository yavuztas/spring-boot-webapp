# Spring Boot Web Application
## Developer Manual

### Getting Started
After you installed `Java 8+` and `Maven 3+`, the fastest way to run **Spring Boot Web Application** without any configuration:
```
mvn spring-boot:run
```
After successfully started, you can access the login page:
```
http://localhost/login
```
If you need to change the default port other than **80**, you can start server with additional `server.port=8000`:
```
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8000
```
Sample credentials for testing the application you can use below.
<br>For `Guest` user:
```
username: bob
password: bob1
```
For `Admin` user:
```
username: john
password: john1
```

### Running Options
To run Spring Boot Web Application over on **Mysql**:
- Make sure you have a running Mysql instance on `localhost:3306`
- Create an empty schema `testwebapp` if you haven't already
- Simply navigate to the project folder and execute command below:
```
mvn spring-boot:run -Dspring-boot.run.profiles=prod,import -Dspring-boot.run.arguments=--spring.datasource.username=root,--spring.datasource.password=password
```
Certainly, you should provide your database username instead of `root` and password instead of `password` in the above command.

If you have already data populated in your database you should skip `import` profile:
```
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

Alternatively, you can run the application without any external database. 
An **H2 in-memory** database and some amount of test data automatically configured if you just use the `dev` profile:
```
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

Additionaly, the default fallback profile is also `dev` profile so you can easily skip `-Dspring-boot.run.profiles=dev` part and run directly:
```
mvn spring-boot:run
```
### Run Using Docker
To run with docker you can simply execute `./build.sh` file which contains the command below:
```
mvn clean install
docker container rm spring-boot-webapp
docker build --build-arg USER=webapp -t boilerplates/spring-boot-webapp-0.0.1 .
docker run -p 80:8000 --name spring-boot-webapp -e JAVA_OPTS=-Dserver.port=8000 boilerplates/spring-boot-webapp-0.0.1
```
If you would like to deploy docker hub, do not forget to change `boilerplates/` to your docker user.
### Application Pages
login page:
```
http://localhost/login
```
user page - `Guest`:
```
http://localhost/user
```
user list page - `Admin`:
```
http://localhost/users
```
user details page - `Admin`:
```
http://localhost/user/{username}
```
### Notes About Security
1. We disabled password encoder to keep it simple in `dev.yavuztas.boilerplate.springbootwebapp.config.SecurityConfig`. Ideally in production environments,
you should use an adaptive one way function like BCryptPasswordEncoder, Pbkdf2PasswordEncoder, or SCryptPasswordEncoder. 