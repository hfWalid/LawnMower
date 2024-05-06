## Project Overview
MowItNow is a Java application designed to simulate an automated lawnmower system. It takes input from a file, processes the instructions for each lawnmower, and outputs their final positions.

### Architecture
The project follows the hexagonal architecture pattern, separating concerns into layers:

* Infrastructure/Adapters: Contains classes for input and output operations, reading from and writing to files.
* Domain: Contains domain model classes representing the lawn, lawnmower, and instructions also the business logic for moving lawnmowers on the lawn.
* Application: Implements the Spring Batch job configuration for reading, processing, and writing lawnmower data and also some Aspect oriented Logging configuration.

### Versions Used
Java: JDK 17
Spring Boot: 2.6.4
Gradle: 7.2
Docker: 25.0.3

NB: when executing the project, the output.txt file should be generated under src/main/resources containing results

## Building and Deployment