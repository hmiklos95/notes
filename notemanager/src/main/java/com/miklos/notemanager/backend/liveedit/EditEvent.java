package com.miklos.notemanager.backend.liveedit;

import com.miklos.notemanager.backend.entities.Note;
import com.miklos.notemanager.backend.entities.User;

public class EditEvent {
	private final Note edited;
	private final User editor;
	public EditEvent(Note edited, User editor) {
		super();
		this.edited = edited;
		this.editor = editor;
	}
	public Note getEdited() {
		return edited;
	}
	public User getEditor() {
		return editor;
	}
	
}
