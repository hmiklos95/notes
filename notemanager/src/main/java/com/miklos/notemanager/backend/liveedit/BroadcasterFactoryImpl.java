package com.miklos.notemanager.backend.liveedit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.miklos.notemanager.backend.entities.Note;
import com.miklos.notemanager.backend.entities.Notebook;
import com.miklos.notemanager.backend.services.NoteService;

@Model
@Default
public class BroadcasterFactoryImpl implements BroadcasterFactory {
	
	private static Map<Long, Broadcaster> broadcasters = new ConcurrentHashMap<Long, Broadcaster>();
	
	@Inject
	private NoteService service;
	
	@Override
	public Broadcaster createForNote(Note note) {
		if(!broadcasters.containsKey(note.getId())) {
			broadcasters.put(note.getId(), new Broadcaster(note, service));
		}
		
		return broadcasters.get(note.getId());
	}

}
