package com.miklos.notemanager.backend.liveedit;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.miklos.notemanager.backend.services.NoteService;

public class Persistor {
	private NoteService service;
	
	private BlockingQueue<MergedNoteEvent> eventsToPersist;
	
	public Persistor(NoteService service) {
		this.service = service;
		eventsToPersist = new LinkedBlockingQueue<MergedNoteEvent>();
	}
	
	
	public void addEditEventToPersist(MergedNoteEvent event) {
		eventsToPersist.add(event);
	}
	
	
}
