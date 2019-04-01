package es.josempmoreno.ghost.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.junit4.SpringRunner;

import es.josempmoreno.ghost.GhostApplication;
import es.josempmoreno.ghost.dto.Word;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@DataJpaTest
@BootstrapWith(SpringBootTestContextBootstrapper.class)
@SpringBootTest(classes = GhostApplication.class)
@Slf4j
public class WordRepositoryIntegrationTest {

	@Autowired
	WordRepository wordRepository;

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Before
	public void setup() {
		populateDatabase();
	}

	@Test
	public void findWordByGroupOfLetters() throws Exception {
		assertNotNull(wordRepository.findByLetters("hyoglossus"));
	}

	@Test
	public void getZeroResultWhenTryToFindWordByInvalidGroupOfLetters() throws Exception {
		assertNull(wordRepository.findByLetters("hxx"));
	}

	@Test
	public void findWordWithMaxLenghByOneLetter() throws Exception {

		List<Word> result = wordRepository.findByLettersWithMaxLength("h");
		assertEquals(result.size(), 1);
		assertEquals(result.get(0).getValue(), "hyperenthusiastically");
	}

	@Test
	public void findWordWithMaxLenghByGroupOfLetters() throws Exception {

		List<Word> result = wordRepository.findByLettersWithMaxLength("hyoc");
		assertEquals(result.size(), 1);
		assertEquals(result.get(0).getValue(), "hyocholalic");
	}

	@Test
	public void findWordWithMinLenghByOneLetter() throws Exception {

		List<Word> result = wordRepository.findByLettersWithMinLength("h", 3);
		assertEquals(result.size(), 2);
		assertEquals(result.get(0).getValue(), "hynd");
	}

	@Test
	public void findWordWithMinLenghByGroupOfLetters() throws Exception {

		List<Word> result = wordRepository.findByLettersWithMinLength("hyoglo", 3);
		assertEquals(result.size(), 1);
		assertEquals(result.get(0).getValue(), "hyoglossi");
	}

	private void populateDatabase() {
		log.info("Populate database for testing purposes");
		wordRepository.save(new Word("hun", 3));
		wordRepository.save(new Word("hyp", 3));
		wordRepository.save(new Word("hye", 3));
		wordRepository.save(new Word("hynd", 4));
		wordRepository.save(new Word("hyne", 4));
		wordRepository.save(new Word("hynde", 5));
		wordRepository.save(new Word("hynder", 6));
		wordRepository.save(new Word("hyoglossi", 9));
		wordRepository.save(new Word("hyoglossus", 10));
		wordRepository.save(new Word("hyoglossal", 10));
		wordRepository.save(new Word("hyocholalic", 11));
		wordRepository.save(new Word("hyobranchial", 12));
		wordRepository.save(new Word("hyperessence", 12));
		wordRepository.save(new Word("hyperesthete", 12));
		wordRepository.save(new Word("hyoepiglottic", 13));
		wordRepository.save(new Word("hypererethism", 13));
		wordRepository.save(new Word("hyperesthesia", 13));
		wordRepository.save(new Word("hyoglycocholic", 14));
		wordRepository.save(new Word("hyperepinephry", 14));
		wordRepository.save(new Word("hyperequatorial", 15));
		wordRepository.save(new Word("hyperepinephria", 15));
		wordRepository.save(new Word("hyoepiglottidean", 16));
		wordRepository.save(new Word("hypereosinophilia", 17));
		wordRepository.save(new Word("hyperepinephrinemia", 19));
		wordRepository.save(new Word("hyperenthusiastically", 21));

	}
}