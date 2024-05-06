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
### Build Instructions
To build the project, make sure you have JDK 17 and Gradle installed and that terminal pointing to root directory. Then run the following command:

bash -> gradle clean
bash -> gradle build
bash -> gradle bootRun

by executing this series of commands, an output.txt file should be generated under src/main/resources containing results of processing. 

### Running with Docker
To run the application using Docker, follow these steps:

* Build the Docker image:
  bash -> docker build -t lawnmower .
* Run the Docker container:
  bash -> docker run -d -p 8080:8080 -v /Users/neferpitou_/Desktop/kata/HighFi/LawnMower/src/main/resources/input.txt:/app/input.txt --name lawnmower-container lawnmower-app:latest

This will mount the input and output files from your local filesystem into the Docker container and run the MowItNow application.

To stop and remove the Docker container and image
bash -> docker stop lawnmower-container
bash -> docker rm lawnmower-container
