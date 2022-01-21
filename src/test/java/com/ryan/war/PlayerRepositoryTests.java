package com.ryan.war;

import com.ryan.war.player.Player;
import com.ryan.war.player.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class PlayerRepositoryTests {

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void testSavePlayer() {
        Player testPlayer = new Player("testPlayer", 0, null, null);
        Player savedPlayer = playerRepository.save(testPlayer);
        assertThat(savedPlayer).isNotNull();
    }

    @Test
    public void testGetAllPLayers() {
        List<Player> players = playerRepository.findAll();
        assertThat(players).hasSize(2);
    }

    @Test
    public void testGetPlayerById() {
        Player player = playerRepository.getByPlayerId("playerOne");
        assertThat(player.getPlayerId()).isEqualTo("playerOne");
    }

    @Test
    public void testGetPlayerWins() {
        int wins = playerRepository.getPlayerWins("playerOne");
        assertThat(wins).isNotNull();
    }

    @Test
    public void testGetPlayerByIdInvalidId() {
        Player player = playerRepository.getByPlayerId("1");
        assertThat(player == null);
    }




}
