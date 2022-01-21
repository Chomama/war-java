# WAR

This project runs the card game war and is written with Java, Spring, and connects to a MySql database deployed on AWS.
Thymeleaf is leveraged for a simple template based UI. 
The homepage runs on the root url (localhost:8080).

## Run with Docker

```bash
docker pull chomama/war-docker:version1
docker run --publish 8080:8080 chomama/war-docker:version1
```
The app will be running on localhost:8080. 


## Installation 
The app requires Java 17 to run. 

```bash
https://github.com/Chomama/war-java.git
cd war-java
```

Once installed, open ./src/main/resources/application.properties and fill in the password dataosource password.

## Testing

```bash
./gradlew test
```

## Start the application on localhost:8080

```bash
./gradlew run
```

## API's

**/start**

The start endpoint starts the game and will return the results. 

**/getPlayerWins**

This endpoint gets an individual player's number of wins and takes the player's id as parameter.
eg) http://localhost:8080/getPlayerWins?playerId=playerOne

**/getAllPlayerWins**

This endpoint returns all of the players and their corresponding number of wins.

