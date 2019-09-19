package com.daus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

//	PlayRepository playRepository;
//	PlayerRepository playerRepository;
//	
//	public MainController(PlayRepository playRepository, PlayerRepository playerRepository) {
//		super();
//		this.playRepository = playRepository;
//		this.playerRepository = playerRepository;
//	}

	///////////////////// Parte de la vista para insertar jugador
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
//		playerRepository.save(player);
		return "redirect:/results";
	}

	@GetMapping("/web")
	public String getPlayer(Model model) {
//		model.addAttribute("players", playerRepository.findAll());
		return "player";
	}
	////////////////////////////////////////////////////////////////////////////////////////

	//#################################          1           ###############################
	@PostMapping
	@ResponseBody
	public void createPlayer(@RequestBody Player newPlayer) {
//		playerRepository.save(newPlayer);
	}

	//#################################          2           ###############################
	@PutMapping
	@ResponseBody
	public void updatePlayer(@RequestBody Player newPlayer) {
//		playerRepository.save(newPlayer);
	}

	//#################################          3           ###############################
	@PostMapping(path = "/{idPlayer}/games")
	@ResponseBody
	public void createPlay(@PathVariable("idPlayer") int id, @RequestBody Play newPlay) {
		Player player = new Player();
		player.setIdPlayer(id);
		newPlay.setPlayer(player);
//		playRepository.save(newPlay);
	}

	//#################################          4           ###############################
	@DeleteMapping(path = "/{id}")
	@ResponseBody
	public void deletePlayer(@PathVariable("id") int id) {
//		playerRepository.deleteById(id);
	}

	//#################################          5           ###############################
	@DeleteMapping(path = "/{id}/games")
	@ResponseBody
	public void deletePlay(@PathVariable("id") int id) {
//		playRepository.deleteById(id);
	}


	//#################################          6           ###############################
	@GetMapping
	@ResponseBody
	public void getPlayer() {
//		return playerRepository.findAll();
	}

	//#################################          7           ###############################
	@GetMapping(path = "/{id}/games")
	@ResponseBody
	public List<Play> getPlayId(@PathVariable("id") int id) {
//		Iterable<Play> play = playRepository.findAll();
		List<Play> playid = new ArrayList<>();
		Player player = new Player();
//		for (Play play2 : play) {
//			player = play2.getPlayer();
//			if (id == player.getIdPlayer()) {
//				playid.add(play2);
//			}
//		}
		return playid;
	}

	//#################################          8           ###############################
	@GetMapping(path = "/ranking")
	@ResponseBody
	public List<Play> getPlayerRanking() {
//		List<Play> play1 = playRepository.OrderByPlayer();
		int lastPlayer = 0;
		int total = 0;
		int total2 = 0;
//		int count = play1.size();
		int i = 0;
		List<Play> play4 = new ArrayList<>();
		Play play8 = new Play();
//		for (Play play2 : play1) {
//			total2 = 1 + total2;
//			total = 1 + total;
//
//			Player player2 = new Player();
//			Play play6 = new Play();
//			
//			int isWin = play2.getIsWin();
//			player2 = play2.getPlayer();
//			int idPlayer = player2.getIdPlayer(); 
//			double avg = player2.getAvg();
//
//			if (idPlayer == lastPlayer || lastPlayer == 0) {
//				if (lastPlayer != 0 && isWin == 1) {
//					i = i + 1;
//				} else {
//					if (lastPlayer == 0 && isWin == 1) {
//						i = i + 1;
//					}
//				}
//			} else {
//				play6 = play8;
//				play4.add(play6);
//				total = 1;
//				i = 0;
//				if (lastPlayer != 0 && isWin == 1) {
//					i = i + 1;
//				}
//			}
//			
//			lastPlayer = idPlayer;
//			avg = 100*i/total;
//			player2.setAvg(avg);
//			play2.setPlayer(player2);
//			play8 = play2;
//			if (total2 == count) {
//				play4.add(play2);
//			}
//		}
		return play4;
	}
	

	//#################################          9           ###############################
	@GetMapping(path = "/ranking/loser")
	@ResponseBody
	public void getPlayerLoser() {
//		List<Player> player = playerRepository.OrderByAvgAsc();
		
//		return player.get(0);
	}

	//#################################          10          ###############################
	@GetMapping(path = "/ranking/winner")
	@ResponseBody
	public void getPlayerWinner() {
//		List<Player> player = playerRepository.OrderByAvgDesc();
		
//		return player.get(0);
	}

}
