package com.game.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.game.entity.Play;
import com.game.entity.Player;

@Service
public class PlayerService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void addPlayer(Player player) {
		String name = player.getName();
		double avg = player.getAvg();
		String sql = "INSERT INTO player(name,avg) VALUES (?,?)";

		jdbcTemplate.update(sql, name, avg);
	}

	public void updateBy(Player player) {
		int idPlayer = player.getIdPlayer();
		String name = player.getName();
		double avg = player.getAvg();
		String sql = "update player set name = ? where id_player = ?";

		jdbcTemplate.update(sql, name, idPlayer);
	}

	public void deletePlayerById(int idPlayer) {
		String sql = "DELETE FROM player where id_Player = ?";

		jdbcTemplate.update(sql, idPlayer);
	}

	public List<Player> getPlayers() {
		List<Player> player2 = new ArrayList<>();
		String sql = "SELECT * FROM player order by id_player ";
		jdbcTemplate
				.query(sql, new Object[] {},
						(rs, rowNum) -> new Player(rs.getInt("id_player"), rs.getString("name"), rs.getDouble("avg")))
				.forEach(Player -> player2.add(Player));
		return player2;
	}

	public List<Player> getRanking() {
		List<Play> play1 = new ArrayList<>();

		play1 = jdbcTemplate.query("SELECT * FROM play order by id_player", new Object[] {},
				(rs, rowNum) -> new Play(rs.getInt("id_play"), rs.getInt("dice_one"), rs.getInt("dice_two"),
						rs.getInt("is_win"), rs.getInt("id_player")));

		int lastPlayer = 0;
		int total = 0;
		int total2 = 0;
		int count = play1.size();
		int i = 0;
		List<Play> play4 = new ArrayList<>();
		Play play8 = new Play();

		for (Play play2 : play1) {
			total2 = 1 + total2;
			total = 1 + total;
			Player player2 = new Player();
			Play play6 = new Play();
			int isWin = play2.getIsWin();
			int idPlayer = (int) play2.getPlayer();
			player2.setIdPlayer(idPlayer);
			double avg = 0.0;

			if (idPlayer == lastPlayer || lastPlayer == 0) {
				if (lastPlayer != 0 && isWin == 1) {
					i = i + 1;
				} else {
					if (lastPlayer == 0 && isWin == 1) {
						i = i + 1;
					}
				}
			} else {
				play6 = play8;
				play4.add(play6);
				total = 1;
				i = 0;

				if (lastPlayer != 0 && isWin == 1) {
					i = i + 1;
				}
			}

			lastPlayer = idPlayer;
			avg = 100 * i / total;
			player2.setAvg(avg);
			play2.setPlayer((Player) player2);
			play8 = play2;

			if (total2 == count) {
				play4.add(play2);
			}
			jdbcTemplate.update("update player set avg = ? where id_player = ?", avg, idPlayer);
		}
		List<Player> player1 = new ArrayList<>();
		jdbcTemplate
				.query("SELECT * FROM player order by avg desc", new Object[] {},
						(rs, rowNum) -> new Player(rs.getInt("id_Player"), rs.getString("name"), rs.getDouble("avg")))
				.forEach(Player -> player1.add(Player));

		return player1;
	}

	public List<Player> getplayerLast() {
		List<Player> player1 = new ArrayList<>();
		int avg = jdbcTemplate.queryForObject("SELECT min(avg) FROM player ", Integer.class);
		System.out.println("Promedio Minimo " + avg);
		jdbcTemplate
				.query("SELECT * FROM player where avg = ?", new Object[] { avg },
						(rs, rowNum) -> new Player(rs.getInt("id_Player"), rs.getString("name"), rs.getDouble("avg")))
				.forEach(Player -> player1.add(Player));

		return player1;
	}

	public List<Player> getPlayerFirst() {
		List<Player> player1 = new ArrayList<>();
		int avg = jdbcTemplate.queryForObject("SELECT max(avg) FROM player ", Integer.class);
		System.out.println("Promedio Maximo " + avg);
		jdbcTemplate
				.query("SELECT * FROM player where avg = ?", new Object[] { avg },
						(rs, rowNum) -> new Player(rs.getInt("id_Player"), rs.getString("name"), rs.getDouble("avg")))
				.forEach(Player -> player1.add(Player));

		return player1;
	}
}
