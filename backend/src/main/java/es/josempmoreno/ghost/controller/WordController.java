package es.josempmoreno.ghost.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.josempmoreno.ghost.dto.Game;
import es.josempmoreno.ghost.services.GhostService;

@RestController
@CrossOrigin(origins = "*")
public class WordController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	private GhostService ghostService;
	@Autowired
	public WordController(GhostService ghostService) {
		this.ghostService = ghostService;
	}
	@CrossOrigin(origins = "*")
	@PostMapping("/game")
	public Game findNextLetter(@RequestBody Game game, Model model) {
		log.info("Level " + game.getGameLevel());
		return ghostService.findNextLetterValid(game);
	}
}
