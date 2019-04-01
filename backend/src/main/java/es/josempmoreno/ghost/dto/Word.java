package es.josempmoreno.ghost.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Word {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String value;
	
	private int length;

	public Word() {
		super();
	}

	public Word(String value,int length) {
		super();
		this.value = value;
		this.length = length;
	}

	public Long getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public int getLength() {
		return length;
	}

}
