package com.ryan.war.api;

import com.ryan.war.game.WarGame;
import com.ryan.war.player.Player;
import com.ryan.war.player.query.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
public class WarController{

    @Autowired
    private PlayerRepository repository;

    @Autowired
    private WarGame warGame;

    @GetMapping(value = "/start")
    public String startGame() throws Exception {
        Player winner = warGame.startGame();
        return String.format("Game has finished and the winner is %s!", winner.getPlayerId());
    }

    @GetMapping(value = "/getPlayerWins")
    public int getPlayerWins(String playerId) {
        return repository.getPlayerWins(playerId);
    }

    @GetMapping(value = "/getAllPlayerWins")
    public Map<String, Integer> getAllPlayerWins() {
        List<Player> players = repository.findAll();
        Map<String, Integer> playerWins = new HashMap<>();
        players.stream().forEach(p -> {
            playerWins.put(p.getPlayerId(), p.getWins());
        });
        return playerWins;
    }



}
    