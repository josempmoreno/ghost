package es.josempmoreno.ghost.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.josempmoreno.ghost.domain.GameLevel;
import es.josempmoreno.ghost.dto.Game;
import es.josempmoreno.ghost.dto.Word;
import es.josempmoreno.ghost.repository.WordRepository;
import es.josempmoreno.ghost.services.GhostService;
import lombok.extern.slf4j.Slf4j;
import utils.Utils;

@Service
@Slf4j
public class GhostServiceImpl implements GhostService {



	@Autowired
	WordRepository wordRepository;
	
	Utils utils;

	Logger log = LoggerFactory.getLogger(this.getClass());

//		a ver, ahora mismo tengo 3 requisitos del juego, que se sacan rápido del texto 
//		1.-Si introduces una letra que haga que no se pueda continuar se pierde, o sin sentido ejemplo “qq”
//		2.-gana el que genere una pálabra válida mayor que 3 letras
//		3.-Empieza el usuario
//
//		y ahora
//
//		0.- El usuario escoje modo fácil o modo díficil
//		1.-el usuario intruduce una letra 1
//		2.-la cpu busca todas las palabras que empiecen por esa letra generando una lista con el resultado, 
//		2.1.- SI es modo fácil devolverá la segunda letra de la palabra más larga
//		2.2.- Si es modo díficil devolvera la segunda letra de la palabra más corta
//		3.-El usuario introduce la letra 3
//		3.1.-Si existe esa palabra el usuario gana, si no existe,la cpu busca la palabra más corta/larga con esas tres letras,si hay una palabra de 4 letras con esa combinación la CPU gana, si no,devuelve la 4 letra de la palabra más corta/larga..
//		4.- El usuario introduce la letra N…
//		4.1.-Si existe esa palabra el usuario gana, si no existe,la cpu busca la palabra más corta/larga con esas N letras,si hay una palabra de N letras con esa combinación la CPU gana, si no,devuelve la N+1 letra de la palabra más corta/larga..

	/**
	 * 
	 */
	@Override
	public Game findNextLetterValid(Game game) {

		String letters = game.getLetters();
		if (letters.length() > NUMBER_OF_LETTERS_FOR_A_VALID_WORD) {
			// check if exists the word
			Word word = wordRepository.findByLetters(letters);
			log.info("find word with letters :" + letters);
			if (word != null) {
				log.info("User win with the word :" + word.getValue());
				game.setLetters(null);
				game.setWord(null);
				return game;
			} 
		}

		List<Word> result = searchWordsFromLettersAndGameLevel(game);

		log.debug("size result for letters " + letters + " : " + result.size());

		return findNextLetterInSearchResult(game, result);

	}

	/**
	 * Find the next valid letter in a search result
	 * <ul>
	 * <li>If the next letter complete a valid word CPU Win.</li>
	 * <li>If not exists words in the search result CPU Win.</li>
	 * <li>In other case the method return the next valid letter for a random word
	 * that exists in the search result.</li>
	 * </ul>
	 * 
	 * @param letters Group of letters
	 * @param result  List with valid words
	 * @return the next letter when it's possible or null when not exist word
	 */
	public Game findNextLetterInSearchResult(Game game, List<Word> result) {
		if (result.size() > 0) {
			String letters = game.getLetters();
			Word word = result.get(0);
			if (word.getLength() == letters.length() + 1) {
				log.info("CPU win with the word :" + word.getValue());
				game.setLetters(null);
				game.setWord(word.getValue());
				return game;
			}

			// if the word not exists yet
			utils = new Utils();
			int randomNumber = utils.getRandomIntBetweenRange(result.size());
			Word randomWord = result.get(randomNumber);

			log.debug("found " + randomWord.getValue() + " return : "
					+ randomWord.getValue().substring(letters.length(), letters.length() + 1));

			String nextLetter = randomWord.getValue().substring(letters.length(), letters.length() + 1);
			game.setLetters(nextLetter);
			return game;
		} else {
			log.info("words not found!");
			log.info("CPU WIN!!!");
			game.setLetters(null);
			game.setWord("");
			return game;
		}
	}

	/**
	 * Search in the repository valid word with letter
	 * <p>
	 * Easy Mode:
	 * <ul>
	 * <li>Search words with the max length</li>
	 * </ul>
	 * </p>
	 * <p>
	 * Hard Mode:
	 * <ul>
	 * <li>Search words with the min length when length more bigger
	 * than @numLettersValidWord</li>
	 * </ul>
	 * </p>
	 * 
	 * @param gameLevel GameLevel selected by the user
	 * @param letters   Group of letters
	 * @return the next letter when it's possible or null when not exist word
	 * @See application.properties for configure the value of @numLettersValidWord
	 */
	@Override
	public List<Word> searchWordsFromLettersAndGameLevel(Game game) {
		List<Word> result;
		switch (game.getGameLevel()) {
		case EASY:
			log.debug("EASY Mode");
			result = wordRepository.findByLettersWithMaxLength(game.getLetters());
			break;
		case HARD:
		default:
			log.debug("HARD Mode ");
			result = wordRepository.findByLettersWithMinLength(game.getLetters(), NUMBER_OF_LETTERS_FOR_A_VALID_WORD);
			break;
		}
		return result;
	}

}
