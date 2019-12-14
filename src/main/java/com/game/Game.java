package com.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.game.domain.Player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class Game {
	static JdbcTemplate jdbcTemplate;
	private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);

	@Autowired
	public Game(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	public static String playGame() {
		String wishPlay = null;
		Scanner sc = new Scanner(System.in);
		LOGGER.warn("Dame tu IdPlayer ");
		int id = sc.nextInt();
		System.out.println("idPlayer " + id);

		List<Player> player2 = findPlayerById(id);

		if (player2.size() > 0) {
			Player play4 = player2.get(0);
			int idPlayer = play4.getIdPlayer();
			String name = play4.getName();
			LOGGER.warn("IdPlayer Correcto ");
			LOGGER.warn("Lanza dados (y/n)");
			String isThrow = sc.next();
			throwDice(isThrow, idPlayer, name);
		} else {
			LOGGER.warn("idPlayer inCorrecto ");
		}
		LOGGER.warn("Quieres volver a jugar (y/n) ");
		return wishPlay = sc.next();

	}

	public static List<Player> findPlayerById(int id) {
		List<Player> player2 = new ArrayList<>();
		String sql = "SELECT * FROM player where id_player = ?";
		jdbcTemplate
				.query(sql, new Object[] { id },
						(rs, rowNum) -> new Player(rs.getInt("id_Player"), rs.getString("name"), rs.getDouble("avg")))
				.forEach(player -> player2.add(player));
		System.out.println(player2);
		return player2;
	}

	public static void throwDice(String isThrow, int idPlayer, String name) {

		if (isThrow.equals("y")) {
			int diceOne = (int) Math.floor(Math.random() * 6 + 1);
			int diceTwo = (int) Math.floor(Math.random() * 6 + 1);
			int isWin = 0;
			LOGGER.warn("diceOne " + diceOne);
			LOGGER.warn("diceTwo " + diceTwo);

			if (diceOne + diceTwo == 7) {
				isWin = 1;
				LOGGER.warn("Jugada Ganadora ");
			} else {
				isWin = 0;
				LOGGER.warn("Jugada Perdedora ");
			}
			String sql = "INSERT INTO play(dice_One, dice_Two, is_Win, id_player) VALUES (?,?,?,?)";
			jdbcTemplate.update(sql, diceOne, diceTwo, isWin, idPlayer);
		}

	}
}
