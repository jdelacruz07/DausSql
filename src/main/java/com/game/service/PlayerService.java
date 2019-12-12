package com.game.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.game.domain.Play;
import com.game.domain.Player;
import com.game.domain.PlayerRanking;

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
		List<PlayerRanking> playerRanking = new ArrayList<>();
		String sql = "SELECT id_player, avg(is_Win)*100 as average FROM play group by id_player";
		playerRanking = jdbcTemplate.query(sql, new Object[] {},
				(rs, rowNum) -> new PlayerRanking(rs.getInt("id_player"), rs.getDouble("average")));

		playerUpdateAvg(playerRanking);

		List<Player> player1 = playerRankingOrder();

		return player1;
	}

	private void playerUpdateAvg(List<PlayerRanking> playerRanking) {
		for (PlayerRanking player : playerRanking) {
			double avg = player.getAvg();
			int idPlayer = player.getIdPlayer();
			String sql2 = "update player set avg = ? where id_player = ?";
			jdbcTemplate.update(sql2, avg, idPlayer);
		}
	}

	private List<Player> playerRankingOrder() {
		List<Player> player1 = new ArrayList<>();
		String sql3 = "SELECT * FROM player order by avg desc";
		jdbcTemplate
				.query(sql3, new Object[] {},
						(rs, rowNum) -> new Player(rs.getInt("id_Player"), rs.getString("name"), rs.getDouble("avg")))
				.forEach(Player -> player1.add(Player));
		return player1;
	}

	public List<Player> getplayerLast() {
		List<Player> player1 = new ArrayList<>();
		String sql = "SELECT min(avg) FROM player ";
		double avg = jdbcTemplate.queryForObject(sql, Double.class);
		String sql2 = "SELECT * FROM player where avg = ?";
		jdbcTemplate
				.query(sql2, new Object[] { avg },
						(rs, rowNum) -> new Player(rs.getInt("id_Player"), rs.getString("name"), rs.getDouble("avg")))
				.forEach(Player -> player1.add(Player));

		return player1;
	}

	public List<Player> getPlayerFirst() {
		List<Player> player1 = new ArrayList<>();
		String sql = "SELECT max(avg) FROM player ";
		double avg = jdbcTemplate.queryForObject(sql, Double.class);
		String sql2 = "SELECT * FROM player where avg = ?";
		jdbcTemplate
				.query(sql2, new Object[] { avg },
						(rs, rowNum) -> new Player(rs.getInt("id_Player"), rs.getString("name"), rs.getDouble("avg")))
				.forEach(Player -> player1.add(Player));

		return player1;
	}

	public Player findPlayerByName(String name) {
		Player player2 = new Player();
		String sql = "SELECT * FROM player where name = ?";
		player2 = jdbcTemplate.queryForObject(sql, new Object[] { name },
				(rs, rowNum) -> new Player(rs.getInt("id_Player"), rs.getString("name"), rs.getDouble("avg")));
		System.out.println(player2);
		return player2;
	}
}
