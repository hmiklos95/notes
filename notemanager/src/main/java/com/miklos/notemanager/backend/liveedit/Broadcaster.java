package com.miklos.notemanager.backend.liveedit;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import com.miklos.notemanager.backend.entities.Note;
import com.miklos.notemanager.backend.entities.Notebook;
import com.miklos.notemanager.backend.liveedit.lib.diff_match_patch;
import com.miklos.notemanager.backend.services.NoteService;

public class Broadcaster {
	private Note monitored;
	
	private LinkedList<BroadcastReceiver> receivers =
	        new LinkedList<BroadcastReceiver>();
	
	
	private diff_match_patch diff = new diff_match_patch();
	
	public Broadcaster(Note note, NoteService service) {
		monitored = note;
	}
	
	public void register(BroadcastReceiver receiver) {
		receivers.add(receiver);
	}
	
	public void unregister(BroadcastReceiver receiver) {
		receivers.remove(receiver);
	}

	public void broadcast(NoteEditedEvent event) {
		
	}
}
