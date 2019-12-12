package com.game.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.game.domain.Player;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
public class PlayerServiceTest {

	@Autowired
	PlayerService playerService;

	@Test
	public void addPlayerTest() {
		Player player = new Player();
		player.setName("TeresaFour");
		playerService.addPlayer(player);
		Player newPlayer = playerService.findPlayerByName("TeresaFour");
		assertNotNull(newPlayer);
		assertEquals("TeresaFour", newPlayer.getName());

	}

	@Test
	public void deleteByIdPlayerTest() {
		int idPlayer = 16;
		playerService.deletePlayerById(idPlayer);
	}
}
