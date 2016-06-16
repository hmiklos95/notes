package com.miklos.notemanager.backend.services;

import java.io.File;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.miklos.notemanager.backend.assist.PDFValidator;
import com.miklos.notemanager.backend.entities.Note;
import com.miklos.notemanager.backend.entities.Notebook;
import com.miklos.notemanager.backend.entities.PDFNote;
import com.miklos.notemanager.backend.entities.TextNote;
import com.miklos.notemanager.backend.repositories.FileRepository;
import com.miklos.notemanager.backend.repositories.GenericRepository;


@Model
@Default
public class NoteServiceImpl implements NoteService {

	
	@Inject FileRepository fileRepo;
	@Inject PDFValidator pdfValidator;
	@Inject GenericRepository<Note> noteRepo;
	@Inject GenericRepository<Notebook> notebookRepo;
	
	@Override
	public void createTextNote(String title, String text, Notebook containerNotebook) {
		TextNote textNote = new TextNote(title, text);
		containerNotebook.addNote(textNote);
		
		noteRepo.persist(textNote);
		notebookRepo.save(containerNotebook);

	}

	@Override
	public void createPDFNote(String title, File pdfFile, Notebook containerNotebook) throws InvalidPDFFileException {
		if(!pdfValidator.isValidPDF(pdfFile)) {
			throw new InvalidPDFFileException();
		}
		
		PDFNote pdfNote = new PDFNote(title, fileRepo.saveFile(pdfFile));
		
		containerNotebook.addNote(pdfNote);
		noteRepo.persist(pdfNote);
		notebookRepo.save(containerNotebook);
	}

	@Override
	public Note sync(Note note) {
		return noteRepo.refresh(note);
	}
}
