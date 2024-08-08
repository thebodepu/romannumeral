# Getting Started
This is a REST API to convert a given integer into Roman numeral
* When input parameter 'query' is present, the equivalent Roman numeral would be returned in JSON format
* If the input parameters 'min' and 'max' are present, an array of Roman numerals starting from 'min' and upto 'max' would be returned in JSON format

Note: This service is designed to run within a Docker container.

### Pre-requisites
* [Docker](https://docs.docker.com/installation/#installation) 
* [Java 17](https://www.oracle.com/java/technologies/downloads/#java17)  
* [Maven 3.9.x](https://maven.apache.org/download.cgi) 

### Build and Run the service
* Make sure that Docker is running to execute below commands.
* Open a terminal (command prompt, in case of Windows) and go to the root directory of the project.
* Build docker image using below command
   * <code>docker build -t romannumeral .</code>
* Run the service on Docker
   * <code>docker run -p 8080:8080 romannumeral</code>

### Testing
After running the service on Docker, you can validate the service using below endpoints :
* Check if the webservice is running
  * http://localhost:8080/romannumeral/health
* Endpoint to convert integer to Roman numeral
  * http://localhost:8080/romannumeral?query={integer}
    * e.g., http://localhost:8080/romannumeral?query=1
* Endpoint to get Roman numeral for a range of numbers
  * http://localhost:8080/romannumeral?min={integer}&max={integer}
    * e.g., http://localhost:8080/romannumeral?min=1&max=3

How to run the Unit Tests
* Open a terminal and go to the root directory of the project
* Run maven test using below command
  * <code>mvn clean test</code>

#### End to End Test and Unit Test Reports
Please refer to [Test Reports](docs/TestReport.md)

### Engineering and Test Methodology
Engineering Methodology
* Identified the key functionalities defined in the assessment document
* Architected the API using RESTful principles
* Set up the SpringBoot project structure
* Implemented the RESTful endpoints using SpringBoot. [Package Structure](README.md#package-structure) listed below.

Testing Methodology
* Wrote unit tests for service class using JUnit and Mockito
* Used Postman to do the end-to-end testing

### Package Structure
├── Dockerfile <br />
├── src/main/java/com/erb/assess/romannumeral <br />
│   ├──dto <br />
│   ├──config <br />
│   ├──api <br />
│   ├──service <br />
│   ├──exception <br />
│   ├──validation <br />
├── src/main/resources <br />
│   ├──application.properties <br />
├── src/test/java/com/erb/assess/romannumeral <br />
├── src/test/resources <br />
│   ├──queryOne.json <br />
│   ├──minmaxOneToThree.json <br />

### How to Troubleshoot common issues
* If you encounter access issues while building docker image, make sure that you launch the terminal and/or Docker as an administrator.
* Docker build command : The DOT (.) at the end of docker build command represents the current directory.
  * Ref: <code>docker build -t romannumeral .</code>
  * If you are not running above command from the project root, replace the DOT (.) with path to the project root

### Reference Documentation
For further reference, please consider the following sections:
* [How do Roman Numerals work?](https://historylearning.com/a-history-of-ancient-rome/history-of-roman-numerals/how-do-roman-numerals-work)
* [The History of Roman Numerals](https://historylearning.com/a-history-of-ancient-rome/history-of-roman-numerals)
* [Roman numerals - Wiki](https://en.wikipedia.org/wiki/Roman_numerals)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.3.2/reference/htmlsingle/index.html#web)
* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.2/maven-plugin)


