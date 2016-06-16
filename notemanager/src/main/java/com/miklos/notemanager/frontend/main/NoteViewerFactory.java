package com.miklos.notemanager.frontend.main;

import com.miklos.notemanager.backend.entities.Note;
import com.miklos.notemanager.backend.entities.TextNote;

public class NoteViewerFactory {
	public static NoteViewer createNoteViewer(Note note) {
		if(note instanceof TextNote) {
			return new NoteEditor();
		} else {
			return new PDFNoteViewer();
		}
	}
}