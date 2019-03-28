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

	public Word() {
		super();
	}

	public Word(Long id, String value) {
		super();
		this.id = id;
		this.value = value;
	}

	public Word(String value) {
		super();
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
