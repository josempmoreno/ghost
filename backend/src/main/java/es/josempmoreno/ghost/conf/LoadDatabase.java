package es.josempmoreno.ghost.conf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.josempmoreno.ghost.dto.Word;
import es.josempmoreno.ghost.interfaces.WordRepository;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
class LoadDatabase {

	Logger log = LoggerFactory.getLogger(this.getClass());
	static String DATABASE_FILE_LOCATION = "src/main/resources/db/ghost.mv.db";
	static String DICTIONARY_FILE_LOCATION = "src/main/resources/static/ghostGameDict.txt";

	@Bean
	CommandLineRunner initDatabase(WordRepository repository) {

		return args -> {

			// check if exists the H2 database
			if (!CheckExistsDatabase()) {
				log.info("Generate the database");
				// load file
				List<String> dictionary = loadDictionary();
				dictionary.forEach(s -> {
					log.info("Save word: " + s + repository.save(new Word(s)));
				});
			} else {
				log.info("The database was loaded successfully");
			}
		};
	}

	private boolean CheckExistsDatabase() {
		return Files.exists(Paths.get(DATABASE_FILE_LOCATION), LinkOption.NOFOLLOW_LINKS);
	}

	private List<String> loadDictionary() {
		// read file into stream
		try (Stream<String> stream = Files.lines(Paths.get(DICTIONARY_FILE_LOCATION))) {
			return stream.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

}
