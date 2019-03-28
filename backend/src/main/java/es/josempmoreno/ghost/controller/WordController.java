package es.josempmoreno.ghost.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import es.josempmoreno.ghost.services.GhostService;

@RestController

public class WordController {

	// private WordRepository wordRepository;
	Logger log = LoggerFactory.getLogger(this.getClass());

	private GhostService ghostService;
	@Autowired
	public WordController(GhostService ghostService) {//WordRepository wordRepository ) {
		//this.WordRepository = wordRepository;
		this.ghostService = ghostService;
	}

	//@RequestMapping(value = "/{letters}", method = RequestMethod.POST)
	@PostMapping("/{level}/{letters}")
	public String readersBooks(@PathVariable("level") String level,@PathVariable("letters") String letters, Model model) {
		//List<Book> readingList = readingListRepository.findByReader(reader);
//		if (readingList != null) {
//			model.addAttribute("books", readingList);
//		}
		log.info("");
		return ghostService.findWord(level, letters);
		//return new Word(1L,"hola");
		//return letters + " letters";
	}
}
