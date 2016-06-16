package com.miklos.notemanager.backend.services;

import java.io.File;

import javax.enterprise.inject.Model;

import com.miklos.notemanager.backend.entities.Note;
import com.miklos.notemanager.backend.entities.Notebook;



@Model
public interface NoteService {
	public void createTextNote(String title, String text, Notebook containerNotebook);
	public void createPDFNote(String title, File pdfFile, Notebook containerNotebook) throws InvalidPDFFileException;
	public Note sync(Note note);
	public static class InvalidPDFFileException extends Exception {}
}
