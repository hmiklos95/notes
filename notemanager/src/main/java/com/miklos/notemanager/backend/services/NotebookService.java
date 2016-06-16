package com.miklos.notemanager.backend.services;

import javax.enterprise.inject.Model;

import com.miklos.notemanager.backend.entities.Note;
import com.miklos.notemanager.backend.entities.Notebook;
import com.miklos.notemanager.backend.entities.User;


@Model
public interface NotebookService {
	public void createNotebook(User creator, Notebook notebook);
	public void addNoteToNotebook(Notebook notebook, Note note);
	public void addUserToNotebook(Notebook notebook, User user);
}
