package com.miklos.notemanager.backend.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
public class Notebook extends BaseModel {
	private String name;

	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER, mappedBy="notebook")
	private List<Note> notes;
	
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="notebooks", fetch=FetchType.EAGER)
	private List<User> users;
	
	public Notebook(String name) {
		super();
		this.name = name;
	}
	
	public Notebook() {
		
	}

	public String getName() {
		return name;
	}
	
	public void addNote(Note note) {
		notes.add(note);
	}
	
	public void addUser(User user) {
		if(users != null) {
			users.add(user);
		}
	}

	public List<Note> getNotes() {
		return notes;
	}

	public List<User> getUsers() {
		return users;
	}
	
}
