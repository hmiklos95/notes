package com.miklos.notemanager.backend.services;



import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.miklos.notemanager.backend.entities.Note;
import com.miklos.notemanager.backend.entities.Notebook;
import com.miklos.notemanager.backend.entities.User;
import com.miklos.notemanager.backend.repositories.GenericRepository;

@Model
@Default
public class NotebookServiceImpl implements NotebookService {

	@Inject GenericRepository<Notebook> notebookRepo;
	@Inject GenericRepository<Note> noteRepo;
	@Inject GenericRepository<User> userRepo;
	
	@Override
	public void createNotebook(User creator, Notebook notebook) {
		creator.addNotebook(notebook);
		notebook.addUser(creator);
		notebookRepo.persist(notebook);
		userRepo.save(creator);
		
	}

	@Override
	public void addNoteToNotebook(Notebook notebook, Note note) {
		notebook.addNote(note);
		noteRepo.persist(note);
	}

	@Override
	public void addUserToNotebook(Notebook notebook, User user) {
		user.addNotebook(notebook);
		notebook.addUser(user);
		notebookRepo.save(notebook);
		userRepo.save(user);
		
	}

}
