package com.ryan.war.player;

import com.ryan.war.game.cards.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Player")
public class Player {
    @Id
    private String playerId;

    @Column(name="wins")
    private int wins;

    @Transient
    private LinkedList<Card> deck;

    @Transient
    private Card currentCard;

    //takes a card from front of the deck and sets it as currentCard
    public Card drawCard() {
        if(deck.size() == 0) {
            return null;
        }
        currentCard = deck.remove();
        return currentCard;
    }

    //adds the cards to back of the deck
    public void addCards(List<Card> cards) {
        deck.addAll(cards);
    }

    //adds to the lifetime wins of the player
    public void addWin() {
        this.wins++;
    }

}
