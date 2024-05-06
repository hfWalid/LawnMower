# Use the official OpenJDK 17 image as a base image
FROM amazoncorretto:17-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file into the container
COPY build/libs/LawnMower-0.0.1-plain.jar /app/lawnmower.jar

# Copy the input.txt and output.txt files into the container
COPY src/main/resources/input.txt /app/input.txt

# Expose the port the application runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "lawnmower.jar"]
