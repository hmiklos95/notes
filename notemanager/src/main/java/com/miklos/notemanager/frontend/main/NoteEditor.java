package com.miklos.notemanager.frontend.main;

import com.miklos.notemanager.backend.entities.Note;
import com.miklos.notemanager.backend.entities.TextNote;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.RichTextArea;

public class NoteEditor extends NoteViewer {
	private TextNote currentNote;
	
	private RichTextArea textArea;
	
	public NoteEditor() {
		textArea = new RichTextArea();
		
		textArea.setSizeFull();
		addComponent(textArea);
	}
	
	public void open(Note note) {
		this.currentNote = (TextNote)currentNote;
		BeanItem<Note> item = new BeanItem<Note>(note);
		textArea.setPropertyDataSource(item.getItemProperty("text"));
		
	}

	public Note getCurrentNote() {
		return currentNote;
	}
}