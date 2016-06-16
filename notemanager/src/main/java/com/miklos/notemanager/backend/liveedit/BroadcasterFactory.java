package com.miklos.notemanager.backend.liveedit;

import javax.enterprise.inject.Model;

import com.miklos.notemanager.backend.entities.Notebook;

@Model
public interface BroadcasterFactory {
	public Broadcaster createForNotebook(Notebook notebook);
}
