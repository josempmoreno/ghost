package es.josempmoreno.ghost.dto;

import java.io.Serializable;

import es.josempmoreno.ghost.domain.GameLevel;

public class Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2264090314126281175L;
	private String letters;
	private GameLevel gameLevel;
	private String word;

	public Game(String letters, GameLevel gameLevel, String word) {
		super();
		this.letters = letters;
		this.gameLevel = gameLevel;
		this.word = word;
	}

	public String getLetters() {
		return letters;
	}

	public void setLetters(String letters) {
		this.letters = letters;
	}

	public GameLevel getGameLevel() {
		return gameLevel;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (gameLevel != other.gameLevel)
			return false;
		if (letters == null) {
			if (other.letters != null)
				return false;
		} else if (!letters.equals(other.letters))
			return false;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}

}
