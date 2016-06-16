package com.miklos.notemanager.backend.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
public class User extends BaseModel {
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Notebook> notebooks = new ArrayList<Notebook>();
	
	private String name;
	private String password;
	private String displayName;
	private int age;
	
	public User(String name, String password, String displayName, int age) {
		super();
		this.name = name;
		this.password = password;
		this.displayName = displayName;
		this.age = age;
	}
	
	public User() {
		
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}

	public String getDisplayName() {
		return displayName;
	}

	public List<Notebook> getNoteBooks() {
		return notebooks;
	}
	
	public void addNotebook(final Notebook notebook) {
		notebooks.add(notebook);
	}
	
}
