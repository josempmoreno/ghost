package es.josempmoreno.ghost.conf;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import es.josempmoreno.ghost.GhostApplication;
import es.josempmoreno.ghost.repository.WordRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = GhostApplication.class, initializers = ConfigFileApplicationContextInitializer.class)
public class LoadDatabaseTest {

	static String RESOURCES_PATH = "src/test/resources";
	static String DICTIONARY_FILE_PATH = "static/ghostGameDictTest.txt";

	@Autowired
	GhostApplication ghostApplication;

	@Autowired
	ApplicationContext ctx;

	@Autowired
	WordRepository wordRepository;

	@Before
	public void setup() {
		ReflectionTestUtils.setField(LoadDatabase.class, "RESOURCES_PATH", RESOURCES_PATH);
		ReflectionTestUtils.setField(LoadDatabase.class, "DICTIONARY_FILE_PATH", DICTIONARY_FILE_PATH);
	}

	@SuppressWarnings("static-access")
	@Test
	public void generateAndPopulateDatabase() {
		ghostApplication.main(new String[0]);
	}

	@Test
	public void generateErrorWhenThePathOfDictionaryNotExists() {
		//Given
		LoadDatabase runner = ctx.getBean(LoadDatabase.class);
		ReflectionTestUtils.setField(LoadDatabase.class, "DICTIONARY_FILE_PATH", "fakepath");
		//When		
		List<String> dictionary = runner.loadDictionary();
		//Then
		Assert.assertNull(dictionary);
	}

	@Test
	public void openExistsDatabase() {
		LoadDatabase runner = ctx.getBean(LoadDatabase.class);
		ReflectionTestUtils.setField(LoadDatabase.class, "DATABASE_FILE_PATH", LoadDatabase.DATABASE_FILE_PATH);
		Assert.assertTrue(runner.CheckExistsDatabaseIsEmpty());
	}

	@Test
	public void generateDatabseWhenNotExists() {
		LoadDatabase runner = ctx.getBean(LoadDatabase.class);
		ReflectionTestUtils.setField(LoadDatabase.class, "DATABASE_FILE_PATH", "fakepath");
		Assert.assertTrue(runner.CheckExistsDatabaseIsEmpty());
	}

}
