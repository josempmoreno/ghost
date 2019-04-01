package es.josempmoreno.ghost.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.josempmoreno.ghost.domain.GameLevel;
import es.josempmoreno.ghost.dto.Game;
import es.josempmoreno.ghost.services.GhostService;

@RunWith(SpringRunner.class)
@WebMvcTest(WordController.class)
public class WordControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private GhostService ghostService;

	@Test
	public void getNextLetterFromSingleLetterWhenGameLevelIsEasy() throws Exception {
		// given
		Game game = new Game("h", GameLevel.EASY, null);
		Game gameResponse = new Game("he", GameLevel.HARD, null);
		// when
		Mockito.when(ghostService.findNextLetterValid(game)).thenReturn(gameResponse);

		// then
		mvc.perform(post("/game")
				.content(asJsonString(game))
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())				
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.letters", is(gameResponse.getLetters())));
	}

	@Test
	public void getNextLetterFromSingleLetterWhenGameLevelIsHard() throws Exception {
		// given
		Game game = new Game("h", GameLevel.HARD, null);
		Game gameResponse = new Game("he", GameLevel.HARD, null);
		

		// when
		Mockito.when(ghostService.findNextLetterValid(game)).thenReturn(gameResponse);

		// Then
		mvc.perform(post("/game")
				.content(asJsonString(game))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.letters", is(gameResponse.getLetters())));
	}

	@Ignore
	@Test
	public void getPageNotFoundWhenLetterIsEmptyAndGameLevelNotExist() throws Exception {

		mvc.perform(post("/w/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

	}
	
	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
