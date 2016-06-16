package com.miklos.notemanager.backend.entities;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


@Entity
@Table
@Inheritance( strategy = InheritanceType.JOINED )

public class Note extends BaseModel  {
	private String title;

	public Note(String title) {
		super();
		this.title = title;
	}
	
	public Note() {
		
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
	
}
