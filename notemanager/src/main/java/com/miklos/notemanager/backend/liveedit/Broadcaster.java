package com.miklos.notemanager.backend.liveedit;

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

import com.miklos.notemanager.backend.entities.Note;
import com.miklos.notemanager.backend.entities.TextNote;
import com.miklos.notemanager.backend.liveedit.lib.diff_match_patch;
import com.miklos.notemanager.backend.services.NoteService;

public class Broadcaster {
	private TextNote monitored;
	
	private String currentText;
	
	private LinkedList<BroadcastReceiver> receivers =
	        new LinkedList<BroadcastReceiver>();
	
	private PatchCalculator calculator = new PatchCalculator();
	
	private diff_match_patch diff = new diff_match_patch();
	
	public Broadcaster(Note note, NoteService service) {
		monitored = (TextNote) note;
		currentText = monitored.getText();
	}
	
	public void register(BroadcastReceiver receiver) {
		receivers.add(receiver);
	}
	
	public void unregister(BroadcastReceiver receiver) {
		receivers.remove(receiver);
	}

	public void onEdit(NoteEditedEvent event) {
		try {
			LinkedList<diff_match_patch.Patch> patches = calculator.calculateDiff(currentText, event.getNewVersion().getText());
			//System.out.println(currentText + " + " + patches);
			System.out.println("calculating patches" + "on thread: " + Thread.currentThread().getName());
			synchronized (currentText) {
				currentText = (String)diff.patch_apply(patches, currentText)[0];
				System.out.println("applying patches"+ "on thread: " + Thread.currentThread().getName());
				notifyReceivers(event);
			}
			
		} catch (InterruptedException e) {
			
		} catch (ExecutionException e) {
			
		}
	}
	
	
	private void notifyReceivers(NoteEditedEvent event) {
		monitored.setText(currentText);
		for (BroadcastReceiver broadcastReceiver : receivers) {
			broadcastReceiver.receiveBroadcast(new MergedNoteEvent(monitored, event.getEditor()));
		}
	}
}
