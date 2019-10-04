package com.daus;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.game.entity.Customer;
import com.game.entity.Play;
import com.game.entity.Player;
import com.game.repository.PlayRepository;
import com.game.repository.PlayerRepository;

@SpringBootApplication
public class DausSqlApplication implements CommandLineRunner {

	@Autowired
    JdbcTemplate jdbcTemplate;
	
//	PlayRepository playRepository;
//	PlayerRepository playerRepository;
//	@Autowired
//	public DausSqlApplication(PlayRepository playRepository, PlayerRepository playerRepository) {
//		super();
//		this.playRepository = playRepository;
//		this.playerRepository = playerRepository;
//	}

	public static void main(String[] args) {
		SpringApplication.run(DausSqlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String wishPlay = null;
		do { 
		wishPlay =	playGame();
		}
		while (wishPlay.equals("y"));
	}

	private String playGame(){
		String wishPlay = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Dame tu IdPlayer ");
		int id = sc.nextInt();
		System.out.println("idPlayer "+ id);
		System.out.println("#################   1   #########################");
//		Optional<Player> player = playerRepository.findById(id);
		
//		Player player2 = new Player(); 
		List<Player> player2 = new ArrayList<>(); 
				
       	jdbcTemplate.query(
   			"SELECT * FROM player where id_player = ?",  new Object[] { id },
   			(rs, rowNum) -> new Player(rs.getInt("id_Player"), rs.getString("name"), rs.getDouble("avg"))
//   			).forEach(player -> System.out.println(player));
    		).forEach(player -> player2.add(player));
		System.out.println(player2);
		
		if (player2.size() > 0) {
			Player play4 = player2.get(0);
			int idPlayer = play4.getIdPlayer();
			String name = play4.getName();
			System.out.println("IdPlayer Correcto ");
			System.out.println(idPlayer);
			
			System.out.println("Lanza dados (y/n)");
			String isThrow= sc.next();
			System.out.println(isThrow);
			throwDice(isThrow,idPlayer,name);
		} else {
			System.out.println("idPlayer inCorrecto ");
		}
		System.out.println("Quieres volver a jugar (y/n) ");
		return wishPlay = sc.next();
       	
		
	}
	
	
	
	private void throwDice(String isThrow, int idPlayer, String name) {

		if (isThrow.equals("y")) {
			Play play = new Play();
			Player player2 = new Player();
			int diceOne = (int) Math.floor(Math.random()*6+1); 
			int diceTwo = (int) Math.floor(Math.random()*6+1); 
			int isWin = 0;
			System.out.println("diceOne " + diceOne);
			System.out.println("diceTwo " + diceTwo);
			
			if (diceOne + diceTwo == 7) {
				isWin = 1;
				System.out.println("Jugada Ganadora ");
			}	else {
				isWin = 0;
				System.out.println("Jugada Perdedora ");
				
			}
			play.setDiceOne(diceOne);
			play.setDiceTwo(diceTwo);
			play.setIsWin(isWin);
			player2.setIdPlayer(idPlayer);
			player2.setName(name);
			play.setPlayer(player2);
//			playRepository.save(play);
			jdbcTemplate.update("INSERT INTO play(dice_One, dice_Two, is_Win, id_player) VALUES (?,?,?,?)", 
					diceOne, diceTwo, isWin, idPlayer);
		}
		
	}
}
	
	

