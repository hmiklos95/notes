package com.miklos.notemanager.backend.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.PrimaryKeyJoinColumn;

@PrimaryKeyJoinColumn(referencedColumnName="id")

@Entity
public class TextNote extends Note {

	@Lob
	@Column
	private String text;
	
	private String title;
	public TextNote(String title, String text) {
		super(title);
		this.text = text;
		this.title = title;
	}
	
	public TextNote() {
		
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

}
