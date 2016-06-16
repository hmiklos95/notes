package com.miklos.notemanager.frontend.main;
import com.miklos.notemanager.backend.entities.Note;
import com.vaadin.ui.VerticalLayout;

public abstract class NoteViewer extends VerticalLayout {
	public abstract void open(Note note);
}
