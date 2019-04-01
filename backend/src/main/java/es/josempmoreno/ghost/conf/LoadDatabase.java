package es.josempmoreno.ghost.conf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.josempmoreno.ghost.dto.Word;
import es.josempmoreno.ghost.repository.WordRepository;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
class LoadDatabase {

	Logger log = LoggerFactory.getLogger(this.getClass());
	static String RESOURCES_PATH = "src/main/resources";
	static String DATABASE_FILE_PATH = RESOURCES_PATH + "/db/ghost.mv.db";
	static String DICTIONARY_FILE_PATH = "static/ghostGameDict.txt";

	WordRepository repository;

	@Bean
	CommandLineRunner initDatabase(WordRepository repository) {
		this.repository = repository;
		return args -> {
			populateDatabase(repository);
		};
	}

	public void populateDatabase(WordRepository repository) {
		// check if exists the H2 database
		if (CheckExistsDatabaseIsEmpty()) {
			log.info("Generate the database");
			// load file
			List<String> dictionary = loadDictionary();
			dictionary.forEach(s -> {
				log.trace("Save word: " + s + repository.save(new Word(s, s.length())));
			});
			log.info("The database was populated successfully");
		} else {
			log.info("The database was loaded successfully");
		}
	}

	public boolean CheckExistsDatabaseIsEmpty() {
		if (Files.exists(Paths.get(DATABASE_FILE_PATH), LinkOption.NOFOLLOW_LINKS)) {
			log.info("The database exist");
			log.info("Total words in the database : " + this.repository.count());
			if (repository.count() > 0) {
				return false;
			} else {
				return true;
			}
		} else {
			log.info("the database not exist");
			return true;
		}
	}

	public List<String> loadDictionary() {
		// read file into stream
		log.info("Load Dictionary");
		
		try (InputStream resource =  getClass().getClassLoader().getResourceAsStream(DICTIONARY_FILE_PATH)) {
			  List<String> dictionary =
			      new BufferedReader(new InputStreamReader(resource,
			          StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
			  return dictionary;
			}
		 catch (Exception e) {			
			log.error("Dictionary not found");
			return null;
		}

	}

}
