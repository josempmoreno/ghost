package es.josempmoreno.ghost.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.josempmoreno.ghost.dto.Word;

public interface WordRepository extends JpaRepository<Word, Long> {
	
	@Query(value = "SELECT * FROM Word w WHERE w.value LIKE '?1%'",
			nativeQuery = true)
	List<Word> findByLetters(String letters);
}
