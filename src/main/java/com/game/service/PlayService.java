package com.game.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.game.entity.Play;
import com.game.entity.Player;

@Service
public class PlayService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void addPlay(int id, Play newPlay) {
		Player player = new Player();
		player.setIdPlayer(id);
		int diceOne = newPlay.getDiceOne();
		int diceTwo = newPlay.getDiceTwo();
		int isWin = newPlay.getIsWin();
		String sql = "INSERT INTO play(dice_one, dice_two, is_Win, id_player) VALUES (?,?,?,?)";

		jdbcTemplate.update(sql, diceOne, diceTwo, isWin, id);
	}

	public void deleteById(int idPlay) {
		String sql = "DELETE FROM play where id_play = ?";

		jdbcTemplate.update(sql, idPlay);
	}

	public List<Play> getPlayById(int idPlayer) {
		System.out.println("id " + idPlayer);
		List<Play> play = new ArrayList<>();
		String sql = "SELECT * FROM play where id_player = ?";

		play = jdbcTemplate.query(sql, new Object[] { idPlayer }, (rs, rowNum) -> new Play(rs.getInt("id_play"),
				rs.getInt("dice_one"), rs.getInt("dice_two"), rs.getInt("is_win"), rs.getInt("id_player")));
		return play;
	}
}
