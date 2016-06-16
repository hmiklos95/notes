package com.miklos.notemanager.frontend.liveedit;

import com.miklos.notemanager.backend.entities.Notebook;

public interface BroadcasterFactory {
	public Broadcaster createForNotebook(Notebook notebook);
}
