package com.miklos.notemanager.backend.liveedit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.miklos.notemanager.backend.entities.Notebook;
import com.miklos.notemanager.backend.services.NoteService;

@Model
@Default
public class BroadcasterFactoryImpl implements BroadcasterFactory {
	
	private static Map<Long, Broadcaster> broadcasters = new ConcurrentHashMap<Long, Broadcaster>();
	
	@Inject
	private NoteService service;
	
	@Override
	public Broadcaster createForNotebook(Notebook notebook) {
		if(!broadcasters.containsKey(notebook.getId())) {
			broadcasters.put(notebook.getId(), new Broadcaster(notebook, service));
		}
		
		return broadcasters.get(notebook.getId());
	}

}
