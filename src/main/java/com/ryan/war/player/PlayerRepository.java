package com.ryan.war.player;

import com.ryan.war.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlayerRepository extends JpaRepository<Player, String> {

    Player getByPlayerId(String playerId);

    @Query(value = "SELECT wins FROM Player WHERE playerId = ?1", nativeQuery = true)
    int getPlayerWins(String playerId);

}

