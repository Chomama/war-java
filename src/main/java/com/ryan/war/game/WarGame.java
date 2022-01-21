package com.ryan.war.game;

import com.ryan.war.game.cards.Card;
import com.ryan.war.game.cards.Dealer;
import com.ryan.war.player.Player;
import com.ryan.war.player.PlayerRepository;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Getter
@Setter
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Component
public class WarGame {
    @Autowired
    private PlayerRepository playerRepository;

    public Player startGame() throws Exception {
        log.info("Game has started.");
        //creates a dealer and populates the deck
        Dealer dealer = new Dealer();

        //shuffles the deck
        dealer.shuffle();

        //Gets Player One and Player Two from the database
        Player playerOne = playerRepository.getByPlayerId("playerOne");
        Player playerTwo = playerRepository.getByPlayerId("playerTwo");

        //deals the shuffled cards to the players
        dealer.dealCards(playerOne, playerTwo);

        //calls the recursive function playRound until one of the
        //players runs out of cards or the function returns false
        while(playRound(playerOne, playerTwo, null)) {
            if (playerOne.getDeck().size() == 0 || playerTwo.getDeck().size() == 0) {
                break;
            }
        }

        //sets the winning player
        Player winner = playerOne.getDeck().size() > playerTwo.getDeck().size() ? playerOne : playerTwo;
        log.info("Game finished successfully.");

        //adds to the winners lifetime wins and updates the database
        winner.addWin();
        playerRepository.save(winner);
        return winner;
    }

    //executes the game logic and runs recursively in case of war
    public boolean playRound(Player playerOne, Player playerTwo, List<Card> playedCards) {
        if(playedCards == null) {
            playedCards = new ArrayList<>();
        }

        //each player draws a card from their deck
        Card playerOnePlayedCard = playerOne.drawCard();
        Card playerTwoPlayedCard = playerTwo.drawCard();

        log.info("Round started. Player One has %s cards left and draws %s. Player Two has %s cards left and draws %s.".formatted(
                playerOne.getDeck().size(),
                playerOne.getCurrentCard(),
                playerTwo.getDeck().size(),
                playerTwo.getCurrentCard()
        ));

        //if either players has run out of cards end game
        if(playerOnePlayedCard == null || playerTwoPlayedCard == null) {
            return false;
        }

        //adds each player's played cards
        playedCards.add(playerOnePlayedCard);
        playedCards.add(playerTwoPlayedCard);
        int result = playerOne.getCurrentCard().compareTo(playerTwo.getCurrentCard());

        switch (result) {
            //tie -> initiate war
            case 0:
                log.info("War round.");
                //each player draws a facedown card
                Card playerOneFaceDown = playerOne.drawCard();
                Card playerTwoFaceDown = playerTwo.drawCard();

                if(playerOneFaceDown == null || playerTwoFaceDown == null) {
                    return false;
                }

                //adds the face down cards to the played cards
                playedCards.add(playerOneFaceDown);
                playedCards.add(playerTwoFaceDown);
                return playRound(playerOne, playerTwo, playedCards);

            //player one wins the round and adds the played cards to deck
            case 1:
                log.info("Player One won %s cards.".formatted(playedCards.size()));
                playerOne.addCards(playedCards);
                break;

            //player two wins the round and adds the played cards to deck
            case -1:
                log.info("Player two won %s cards.".formatted(playedCards.size()));
                playerTwo.addCards(playedCards);
                break;
        }
        return true;
    }


}
