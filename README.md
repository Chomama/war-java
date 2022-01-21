# WAR

This project runs the card game War (based on the rules described [here](https://bicyclecards.com/how-to-play/war/)).  This project was aimed to create a RESTful service with two endpoints - one to start the game with two simulated players and an endpoint to get lifetime wins for each player. A simple UI is running on the root URL with buttons to call each of these endpoints.  

## Features
* Java 17 (required version to run)
* Spring Boot
* Spring Data JPA
* ThymeLeaf
* Gradle 7.3.3
* MySQL


# To Run with Docker

```bash
docker pull chomama/war-docker:version1
docker run --publish 8080:8080 chomama/war-docker:version1
```
The homepage will be running on localhost:8080. 


# Installation 

```bash
https://github.com/Chomama/war-java.git
cd war-java
```

Once installed, open ./src/main/resources/application.properties and fill in the datasource password.

## Testing

```bash
./gradlew test
```

## Run 

```bash
./gradlew run
```

## API's

**/startGame**

The start endpoint starts the game and will return the results. 

**/getPlayerWins**

This endpoint gets an individual player's number of wins and takes the player's id as parameter.
eg) http://localhost:8080/getPlayerWins?playerId=playerOne

**/getAllPlayerWins**

This endpoint returns all of the players and their corresponding number of wins.

