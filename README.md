# Morax
Morax is a backend service for Foocation application - Big project in UI UX class that use Kotlin Springboot 

## How to run this project on local machine 

### 1. Prerequisite 

- [Java 17 SE](https://www.java.com/en/download/) and [Kotlin compiler](https://kotlinlang.org/docs/command-line.html) for the compiler
- Editor like [Visual Studio Code](https://code.visualstudio.com/download) or IDE like [IntelliJ](https://www.jetbrains.com/idea/download/?section=windows)
- [Gradle](https://gradle.org/install/) for library management
- [Studio 3T](https://studio3t.com/download/) for MongoDB GUI
- [Docker Engine](https://docs.docker.com/engine/install/), [Docker Desktop](https://www.docker.com/products/docker-desktop/) for creating database server 

### 2. How to run Morax application 

#### Create database server 

- Run `docker-compose up -d` under mongodb to start MongoDB

#### Run Morax springboot

- Clone project 
- Open project in Text Editor or IDE 
- In terminal run this command: `./gradlew bootRun`
