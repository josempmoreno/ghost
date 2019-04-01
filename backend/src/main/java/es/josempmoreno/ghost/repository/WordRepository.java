package es.josempmoreno.ghost.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.josempmoreno.ghost.dto.Word;

public interface WordRepository extends JpaRepository<Word, Long> {
	
	/**
	 * Hard GameLevel Mode Search words with min length
	 * 
	 * @param letters
	 * @return List<Word> with the result filtering word by letters
	 */
	@Query("select w1 from Word w1 where LOWER(w1.value) LIKE LOWER(CONCAT(:letters,'%'))"
			+ "  and length = (SELECT min(length) FROM Word w WHERE LOWER(w.value) LIKE LOWER(CONCAT(:letters,'%')) and w.length > :numLettersValidWord)")
	List<Word> findByLettersWithMinLength(@Param("letters") String letters, @Param("numLettersValidWord") int numLettersValidWord);

	/**
	 * Easy GameLevel Mode Search words with max length
	 * 
	 * @param letters
	 * @return List<Word> with the result filtering word by letters
	 */
	@Query("select w1 from Word w1 where LOWER(w1.value) LIKE LOWER(CONCAT(:letters,'%'))"
			+ "  and length = (SELECT max(length) FROM Word w WHERE LOWER(w.value) LIKE LOWER(CONCAT(:letters,'%')))")
	List<Word> findByLettersWithMaxLength(@Param("letters") String letters);

	/**
	 * Search a valid word from a group of letters
	 * @param letters
	 * @return Word
	 */
	@Query("select w1 from Word w1 where LOWER(w1.value) LIKE LOWER(:letters)")
	Word findByLetters(@Param("letters") String letters);

}
