package com.game.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

import com.game.domain.Play;
import com.game.domain.Player;
import com.game.service.PlayService;
import com.game.service.PlayerService;

@Controller
@RequestMapping(path = "/players")
public class MainController implements WebMvcConfigurer {
	PlayerService playerService;
	PlayService playService;

	@Autowired
	public MainController(PlayerService playerService, PlayService playService) {
		super();
		this.playerService = playerService;
		this.playService = playService;
	}

	// Parte de la vista para insertar jugador
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
		playerService.addPlayer(player);

		return "redirect:/results";
	}

	@GetMapping("/web")
	public String getPlayer(Model model) {
		model.addAttribute("players", playerService.getPlayers());
		return "player";
	}

	@PostMapping
	@ResponseBody
	public void createPlayer(@RequestBody Player newPlayer) {
		playerService.addPlayer(newPlayer);
	}

	@PutMapping
	@ResponseBody
	public void updatePlayer(@RequestBody Player player) {
		playerService.updateBy(player);
	}

	@PostMapping(path = "/{idPlayer}/games")
	@ResponseBody
	public void createPlay(@PathVariable("idPlayer") int id, @RequestBody Play newPlay) {
		playService.addPlay(id, newPlay);
	}

	@DeleteMapping(path = "/{idPlayer}")
	@ResponseBody
	public void deletePlayer(@PathVariable("idPlayer") int idPlayer) {

		playerService.deletePlayerById(idPlayer);
	}

	@DeleteMapping(path = "/{idPlay}/games")
	@ResponseBody
	public void deletePlay(@PathVariable("idPlay") int idPlay) {
		playService.deleteById(idPlay);
	}

	@GetMapping
	@ResponseBody
	public List<Player> getPlayer() {
		return playerService.getPlayers();
	}

	@GetMapping(path = "/{idPlayer}/games")
	@ResponseBody
	public List<Play> getPlayId(@PathVariable("idPlayer") int idPlayer) {
		return playService.getPlayById(idPlayer);
	}

	@GetMapping(path = "/ranking")
	@ResponseBody
	public List<Player> getPlayerRanking() {
		return playerService.getRanking();
	}

	@GetMapping(path = "/ranking/loser")
	@ResponseBody
	public List<Player> getPlayerLoser() {
		return playerService.getplayerLast();
	}

	@GetMapping(path = "/ranking/winner")
	@ResponseBody
	public List<Player> getPlayerWinner() {
		return playerService.getPlayerFirst();
	}

}
