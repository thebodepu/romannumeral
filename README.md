# Getting Started
This is a REST API to convert a given integer into Roman numeral
* When input parameter 'query' is present, the equivalent Roman numeral would be returned in JSON format
* If the input parameters 'min' and 'max' are present, an array of Roman numerals starting from 'min' and upto 'max' would be returned in JSON format

### Pre-requisites
* Install JDK v 17
* Install Docker Desktop v 4.32.0
* Install Maven 3.9.8

### Build and Run the service
* Make sure that Docker Desktop is running to execute below commands.
* Open a terminal and go to the root directory of the project
* Build docker image using below command
   * <code>docker build -t romannumeral .</code>
* Run the service on Docker
   * <code>docker run -p 8080:8080 romannumeral</code>

### Testing
Validate the service using below endpoints :
* Check if the webservice is running
  * http://localhost:8080/romannumeral/health
* Endpoint to convert integer to Roman numeral
  * http://localhost:8080/romannumeral?query={integer}
    * e.g., http://localhost:8080/romannumeral?query=1
* Endpoint to get Roman numeral for a range of numbers
  * http://localhost:8080/romannumeral?min={integer}&max={integer}
    * e.g., http://localhost:8080/romannumeral?min=1&max=3

To run the Unit Tests :
* Open a terminal and go to the root directory of the project
* Run maven test
  * <code>mvn clean test</code>

### Reference Documentation
For further reference, please consider the following sections:
* [How do Roman Numerals work?](https://historylearning.com/a-history-of-ancient-rome/history-of-roman-numerals/how-do-roman-numerals-work)
* [The History of Roman Numerals](https://historylearning.com/a-history-of-ancient-rome/history-of-roman-numerals)
* [Roman numerals - Wiki](https://en.wikipedia.org/wiki/Roman_numerals)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.3.2/reference/htmlsingle/index.html#web)
* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.2/maven-plugin)


