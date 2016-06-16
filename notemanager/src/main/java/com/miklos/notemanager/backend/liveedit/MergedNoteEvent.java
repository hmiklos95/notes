package com.miklos.notemanager.backend.liveedit;

import com.miklos.notemanager.backend.entities.Note;
import com.miklos.notemanager.backend.entities.User;

public class MergedNoteEvent {
	private final Note merged;
	private final User editor;
	public MergedNoteEvent(Note merged, User editor) {
		super();
		this.merged = merged;
		this.editor = editor;
	}
	public Note getMerged() {
		return merged;
	}
	public User getEditor() {
		return editor;
	}
	
}
