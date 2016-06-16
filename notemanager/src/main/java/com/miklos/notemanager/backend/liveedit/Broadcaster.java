package com.miklos.notemanager.backend.liveedit;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import com.miklos.notemanager.backend.entities.Notebook;
import com.miklos.notemanager.backend.services.NoteService;

public class Broadcaster {
	private Notebook monitored;
	
	private Persistor persistor;
	
	private ExecutorService updaterService =
		        Executors.newSingleThreadExecutor();
	
	private LinkedList<BroadcastReceiver> receivers =
	        new LinkedList<BroadcastReceiver>();
	
	public Broadcaster(Notebook notebook, NoteService service) {
		monitored = notebook;
	}
	
	public void register(BroadcastReceiver receiver) {
		receivers.add(receiver);
	}
	
	public void unregister(BroadcastReceiver receiver) {
		receivers.remove(receiver);
	}

	public void broadcast(EditEvent event) {
		for (final BroadcastReceiver listener: receivers)
			updaterService.execute(new Runnable() {
                @Override
                public void run() {
                    listener.receiveBroadcast(event);
                }
            });
	}
}
