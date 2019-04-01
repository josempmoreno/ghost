package es.josempmoreno.ghost.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import es.josempmoreno.ghost.domain.GameLevel;
import es.josempmoreno.ghost.dto.Game;
import es.josempmoreno.ghost.dto.Word;
import es.josempmoreno.ghost.repository.WordRepository;
import es.josempmoreno.ghost.services.impl.GhostServiceImpl;

@RunWith(SpringRunner.class)
public class GhostServiceTest {

	@TestConfiguration
	static class GhostServiceTestContextConfiguration {

		@Bean
		public GhostService ghostService() {
			return new GhostServiceImpl();
		}
	}

	@Autowired
	private GhostService ghostService;

	@MockBean
	private WordRepository wordRepository;

	@Test
	public void findValidLetterFromAValidGroupLetterWithOneLetterInGameLevelEasy() throws Exception {
		// Given
		List<Word> wordList = new ArrayList<>();
		Word word = new Word("hello", 4);
		wordList.add(word);
		Game game = new Game("h", GameLevel.EASY, "");

		// When
		Mockito.when(wordRepository.findByLettersWithMaxLength(game.getLetters())).thenReturn(wordList);
		Game gameResponse = ghostService.findNextLetterValid(game);

		// Then
		assertEquals(word.getValue().substring(game.getLetters().length(), game.getLetters().length() + 1),
				gameResponse.getLetters());
	}

	@Test
	public void getEmptyResultFromAInvalidGroupLetterInGameLevelEasy() throws Exception {
		// Given
		List<Word> wordList = new ArrayList<>();
		Game game = new Game("hypp", GameLevel.EASY, "");

		// When
		Mockito.when(wordRepository.findByLettersWithMaxLength(game.getLetters())).thenReturn(wordList);
		Game gameResponse = ghostService.findNextLetterValid(game);

		// Then
		assertNull(gameResponse.getLetters());
	}

	@Test
	public void findValidGroupLettersFromAValidGroupLetterInGameLevelEasy() throws Exception {
		// Given
		List<Word> wordList = new ArrayList<>();
		Word word = new Word("hypereosinophilia", 17);
		wordList.add(word);
		Game game = new Game("hypereosinophilia", GameLevel.EASY, "");

		// When
		Mockito.when(wordRepository.findByLetters(game.getLetters())).thenReturn(word);
		Mockito.when(wordRepository.findByLettersWithMaxLength(game.getLetters())).thenReturn(wordList);
		Game gameResponse = ghostService.findNextLetterValid(game);

		// Then
		assertNull(gameResponse.getWord());
	}

	@Test
	public void findWordWithAValidGroupLetterWhenTheNextWordInTheResultIsOneLetterMoreBigger() {
		// Given
		List<Word> wordList = new ArrayList<>();
		Word word = new Word("hyoglossus", 10);
		wordList.add(word);
		Game game = new Game("hyoglossu", GameLevel.EASY, "");

		// When
		Mockito.when(wordRepository.findByLettersWithMaxLength(game.getLetters())).thenReturn(wordList);
		Game gameResponse = ghostService.findNextLetterValid(game);

		// Then
		assertEquals(gameResponse.getWord(), word.getValue());
	}

	@Test
	public void findValidLetterFromAValidGroupLetterWithOneLetterInGameLevelHard() throws Exception {
		// Given
		List<Word> wordList = new ArrayList<>();
		Word word = new Word("hello", 4);
		wordList.add(word);
		Game game = new Game("h", GameLevel.HARD, "");

		// When
		Mockito.when(wordRepository.findByLettersWithMinLength(game.getLetters(),
				GhostService.NUMBER_OF_LETTERS_FOR_A_VALID_WORD)).thenReturn(wordList);
		Game gameResponse = ghostService.findNextLetterValid(game);

		// Then
		assertEquals(word.getValue().substring(game.getLetters().length(), game.getLetters().length() + 1),
				gameResponse.getLetters());
	}

}
