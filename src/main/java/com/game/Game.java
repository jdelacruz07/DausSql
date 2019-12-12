package com.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.game.entity.Play;
import com.game.entity.Player;

@Service
public class Game {
	static JdbcTemplate jdbcTemplate;

	@Autowired
	public Game(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	public static String playGame() {
		String wishPlay = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Dame tu IdPlayer ");
		int id = sc.nextInt();
		System.out.println("idPlayer " + id);
		List<Player> player2 = new ArrayList<>();

		String sql = "SELECT * FROM player where id_player = ?";
		jdbcTemplate
				.query(sql, new Object[] { id },
						(rs, rowNum) -> new Player(rs.getInt("id_Player"), rs.getString("name"), rs.getDouble("avg")))
				.forEach(player -> player2.add(player));
		System.out.println(player2);

		if (player2.size() > 0) {
			Player play4 = player2.get(0);
			int idPlayer = play4.getIdPlayer();
			String name = play4.getName();
			System.out.println("IdPlayer Correcto ");
			System.out.println(idPlayer);
			System.out.println("Lanza dados (y/n)");
			String isThrow = sc.next();
			System.out.println(isThrow);
			throwDice(isThrow, idPlayer, name);
		} else {
			System.out.println("idPlayer inCorrecto ");
		}
		System.out.println("Quieres volver a jugar (y/n) ");
		return wishPlay = sc.next();

	}

	public static void throwDice(String isThrow, int idPlayer, String name) {

		if (isThrow.equals("y")) {
			Play play = new Play();
			Player player2 = new Player();
			int diceOne = (int) Math.floor(Math.random() * 6 + 1);
			int diceTwo = (int) Math.floor(Math.random() * 6 + 1);
			int isWin = 0;
			System.out.println("diceOne " + diceOne);
			System.out.println("diceTwo " + diceTwo);

			if (diceOne + diceTwo == 7) {
				isWin = 1;
				System.out.println("Jugada Ganadora ");
			} else {
				isWin = 0;
				System.out.println("Jugada Perdedora ");
			}
			play.setDiceOne(diceOne);
			play.setDiceTwo(diceTwo);
			play.setIsWin(isWin);
			player2.setIdPlayer(idPlayer);
			player2.setName(name);
			play.setPlayer(player2);
			String sql = "INSERT INTO play(dice_One, dice_Two, is_Win, id_player) VALUES (?,?,?,?)";
			jdbcTemplate.update(sql, diceOne, diceTwo, isWin, idPlayer);
		}

	}
}
