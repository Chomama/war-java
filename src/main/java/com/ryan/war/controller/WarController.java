package com.ryan.war.controller;

import com.ryan.war.game.WarGame;
import com.ryan.war.player.Player;
import com.ryan.war.player.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Controller
public class WarController {

    @Autowired
    private PlayerRepository repository;

    @Autowired
    private WarGame warGame;

    /** REST endpoints **/

    //starts and plays out the game
    @GetMapping(value = "/startGame")
    public @ResponseBody String startGame(Model model) throws Exception {
        Player winner = warGame.startGame();
        return String.format("The game has finished %s has won!", winner.getPlayerId());
    }

    //gets the number of wins of the given player
    @GetMapping(value = "/getPlayerWins")
    public @ResponseBody int getPlayerWins(String playerId) {
        return repository.getPlayerWins(playerId);
    }

    //gets all of the players and their respective wins
    @GetMapping(value = "/getAllPlayerWins")
    public @ResponseBody Map<String, Integer> getAllPlayerWins() {
        List<Player> players = repository.findAll();
        Map<String, Integer> playerWins = new HashMap<>();
        players.stream().forEach(p -> {
            playerWins.put(p.getPlayerId(), p.getWins());
        });
        return playerWins;
    }

    /** Template endpoints **/

    //starts the game and returns the game page
    @GetMapping(value = "/startGamePage")
    public String startGamePage(Model model) throws Exception {
        Player winner = warGame.startGame();
        model.addAttribute("playerId", winner.getPlayerId());
        return "gamePage";
    }

    //gets wins for each player and returns the player wins page
    @GetMapping(value = "/getAllPlayerWinsPage")
    public String getAllPlayerWinsPage(Model model) {
        List<Player> players = repository.findAll();
        Map<String, Integer> playerWins = new HashMap<>();
        players.stream().forEach(p -> {
            playerWins.put(p.getPlayerId(), p.getWins());
        });
        model.addAttribute("playerWins", playerWins);
        return "playerWinsPage";
    }


}
    