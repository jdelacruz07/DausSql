package com.daus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.game.entity.Play;
import com.game.entity.Player;
import com.game.repository.PlayRepository;
import com.game.repository.PlayerRepository;

@Controller
@RequestMapping(path = "/players")
public class MainController implements WebMvcConfigurer {
	
	@Autowired
	 JdbcTemplate jdbcTemplate;
	
	 Player player = new Player();
	 Play play = new Play();
	 
	///////////////////// Parte de la vista para insertar jugador   //////////////////////
	///////////////////// ////////////////////////////////////////////////////////////////
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/results").setViewName("results");
	}

	@GetMapping("/")

	public String showForm(Player player) {
		return "form";
	}

	@PostMapping("/")
	public String checkPersonInfo(@Valid Player player, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "form";
		}
		String name = player.getName();
		double avg = player.getAvg();
		jdbcTemplate.update("INSERT INTO player(name,avg) VALUES (?,?)", 
				name,avg);
//		playerRepository.save(player);
		
		return "redirect:/results";
	}

	@GetMapping("/web")
	public String getPlayer(Model model) {
		List<Player> player2 = new ArrayList<>();
		jdbcTemplate.query(
	   			"SELECT * FROM player ",  new Object[] {  },
	   			(rs, rowNum) -> new Player(rs.getInt("id_player"), rs.getString("name"), rs.getDouble("avg"))
	    		).forEach(player -> player2.add(player));
		model.addAttribute("players", player2);
//		model.addAllAttributes("players", playerRepository.findAll());
		
		return "player";
	}
	////////////////////////////////////////////////////////////////////////////////////////

	//#################################          1           ###############################
	@PostMapping
	@ResponseBody
	public void createPlayer(@RequestBody Player newPlayer) {
		String name = newPlayer.getName();
		double avg = newPlayer.getAvg();
		
		jdbcTemplate.update("INSERT INTO player(name,avg) VALUES (?,?)", 
				name,avg);
//		playerRepository.save(newPlayer);
	}

	//#################################          2           ###############################
	@PutMapping
	@ResponseBody
	public void updatePlayer(@RequestBody Player newPlayer) {
		int  idPlayer = newPlayer.getIdPlayer();
		String name = newPlayer.getName();
		double avg = newPlayer.getAvg();
		jdbcTemplate.update("update player set name = ? where id_player = ?",
				name, idPlayer );
//		playerRepository.save(newPlayer);
	}

	//#################################          3           ###############################
	@PostMapping(path = "/{idPlayer}/games")
	@ResponseBody
	public void createPlay(@PathVariable("idPlayer") int id, @RequestBody Play newPlay) {
		Player player = new Player();
		player.setIdPlayer(id);
		int diceOne = newPlay.getDiceOne();
		int diceTwo = newPlay.getDiceTwo();
		int isWin = newPlay.getIsWin();
		jdbcTemplate.update("INSERT INTO play(dice_one, dice_two, is_Win, id_player) VALUES (?,?,?,?)", 
				diceOne,diceTwo, isWin, id);
//		playRepository.save(newPlay);
	}

	//#################################          4           ###############################
	@DeleteMapping(path = "/{idPlayer}")
	@ResponseBody
	public void deletePlayer(@PathVariable("idPlayer") int idPlayer) {
		
		System.out.println("idplayer /////////////////////////////" +idPlayer);
		jdbcTemplate.update(
    			"DELETE FROM player where id_Player = ?",   idPlayer );
//		playerRepository.deleteById(id);
	}

	//#################################          5           ###############################
	@DeleteMapping(path = "/{idPlay}/games")
	@ResponseBody
	public void deletePlay(@PathVariable("idPlay") int idPlay) {
		jdbcTemplate.update(
    			"DELETE FROM play where id_play = ?",   idPlay );
//		playRepository.deleteById(id);
	}


	//#################################          6           ###############################
	@GetMapping
	@ResponseBody
	public List<Player> getPlayer() {
		List<Player> player2 = new ArrayList<>();
		jdbcTemplate.query(
				"SELECT * FROM player order by id_player ",  new Object[] {  },
				(rs, rowNum) -> new Player(rs.getInt("id_player"), rs.getString("name"), rs.getDouble("avg"))
				).forEach(Player -> player2.add(Player));
//		return playerRepository.findAll();
		return player2;
	}

	//#################################          7           ###############################   NOOOOOOOOOOOOOOOOO Funciona
	@GetMapping(path = "/{idPlayer}/games")
	@ResponseBody
	public List<Play> getPlayId(@PathVariable("idPlayer") int idPlayer) {
		System.out.println("id " + idPlayer);
		List<Play> play = new ArrayList<>();
		play = jdbcTemplate.query(
				"SELECT * FROM play where id_player = ?",  new Object[] { idPlayer },
				(rs, rowNum) -> new Play(rs.getInt("id_play"), rs.getInt("dice_one"), rs.getInt("dice_two"), rs.getInt("is_win"),  
						rs.getInt("id_player"))
				);
		return play;
	}

	//#################################          8           ###############################   Noooooooooooooooooooo Funciona
	@GetMapping(path = "/ranking")
	@ResponseBody
	public List<Player> getPlayerRanking() {
		List<Play> play1 = new ArrayList<>();
		
		play1 = jdbcTemplate.query(
				"SELECT * FROM play order by id_player",  new Object[] {  },
				(rs, rowNum) -> new Play(rs.getInt("id_play"), rs.getInt("dice_one"), rs.getInt("dice_two"), rs.getInt("is_win"),  
						rs.getInt("id_player"))
				);
//		List<Play> play1 = playRepository.OrderByPlayer();
		
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
			avg = 100*i/total;
			player2.setAvg(avg);
			play2.setPlayer((Player) player2);
			play8 = play2;
			
			if (total2 == count) {
				play4.add(play2);
			}
			jdbcTemplate.update("update player set avg = ? where id_player = ?",
					avg, idPlayer );
		}
		List<Player> player1 = new ArrayList<>();
		jdbcTemplate.query(
				"SELECT * FROM player order by avg desc",  new Object[] { },
				(rs, rowNum) -> new Player(rs.getInt("id_Player"), rs.getString("name"), rs.getDouble("avg"))
				).forEach(Player -> player1.add(Player));
		
		return player1;
	}
	

	//#################################          9           ###############################
	@GetMapping(path = "/ranking/loser")
	@ResponseBody
	public List<Player> getPlayerLoser() {
		List<Player> player1 = new ArrayList<>();
		int avg = jdbcTemplate.queryForObject("SELECT min(avg) FROM player ", Integer.class);
		System.out.println("Promedio Minimo " + avg );		
		jdbcTemplate.query(
				"SELECT * FROM player where avg = ?",  new Object[] { avg },
				(rs, rowNum) -> new Player(rs.getInt("id_Player"), rs.getString("name"), rs.getDouble("avg"))
				).forEach(Player -> player1.add(Player));
//		List<Player> player = playerRepository.OrderByAvgAsc();
		
		return player1;
	}

	//#################################          10          ###############################
	@GetMapping(path = "/ranking/winner")
	@ResponseBody
	public List<Player> getPlayerWinner() {
		List<Player> player1 = new ArrayList<>();
		int avg = jdbcTemplate.queryForObject("SELECT max(avg) FROM player ", Integer.class);
		System.out.println("Promedio Maximo " + avg );		
		jdbcTemplate.query(
				"SELECT * FROM player where avg = ?",  new Object[] { avg },
				(rs, rowNum) -> new Player(rs.getInt("id_Player"), rs.getString("name"), rs.getDouble("avg"))
				).forEach(Player -> player1.add(Player));
//		List<Player> player = playerRepository.OrderByAvgDesc();
		
		return player1;
	}

}
