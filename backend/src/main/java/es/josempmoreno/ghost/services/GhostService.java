package es.josempmoreno.ghost.services;

import java.util.List;

import org.springframework.stereotype.Service;

import es.josempmoreno.ghost.dto.Game;
import es.josempmoreno.ghost.dto.Word;

@Service

public interface GhostService {

	public static final int NUMBER_OF_LETTERS_FOR_A_VALID_WORD = 3;
	
	Game findNextLetterValid(Game game);

	List<Word> searchWordsFromLettersAndGameLevel(Game game);

}
