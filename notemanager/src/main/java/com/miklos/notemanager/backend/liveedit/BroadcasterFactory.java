package com.miklos.notemanager.backend.liveedit;

import javax.enterprise.inject.Model;

import com.miklos.notemanager.backend.entities.Note;

@Model
public interface BroadcasterFactory {
	public Broadcaster createForNote(Note note);
}
