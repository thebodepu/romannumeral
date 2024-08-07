# Getting Started


### Pre-requisites

* Install JDK version 17 or above
* Install Docker Desktop
* Install Maven

### Build and Run the service

* Make sure that Docker Desktop is running to execute below commands.
* Open a terminal and go to the root directory of the project
* Build docker image using below command
   * <code>docker build -t romannumeral .</code>
* Run the service on Docker
   * <code>docker run -p 8080:8080 romannumeral</code>

### Validate the service using below endpoints
* Check the health of the system
  * http://localhost:8080/romannumeral/health
* 

### Reference Documentation

For further reference, please consider the following sections:
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.3.2/reference/htmlsingle/index.html#web)
* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.2/maven-plugin)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.3.2/reference/htmlsingle/index.html#using.devtools)


