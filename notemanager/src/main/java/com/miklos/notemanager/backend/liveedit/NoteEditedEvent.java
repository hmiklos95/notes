package com.miklos.notemanager.backend.liveedit;

import com.miklos.notemanager.backend.entities.Note;
import com.miklos.notemanager.backend.entities.TextNote;
import com.miklos.notemanager.backend.entities.User;

public class NoteEditedEvent {
	private final TextNote newVersion;
	private final User editor;
	public NoteEditedEvent(TextNote newVersion, User editor) {
		super();
		this.newVersion = newVersion;
		this.editor = editor;
	}
	public TextNote getNewVersion() {
		return newVersion;
	}
	public User getEditor() {
		return editor;
	}
	
}
