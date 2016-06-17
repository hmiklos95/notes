package com.miklos.notemanager.backend.liveedit;

import com.miklos.notemanager.backend.entities.Note;
import com.miklos.notemanager.backend.entities.TextNote;
import com.miklos.notemanager.backend.entities.User;

public class MergedNoteEvent {
	private final TextNote merged;
	private final User editor;
	public MergedNoteEvent(TextNote merged, User editor) {
		super();
		this.merged = merged;
		this.editor = editor;
	}
	public TextNote getMerged() {
		return merged;
	}
	public User getEditor() {
		return editor;
	}
	
}
