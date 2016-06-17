package unittests;

import org.junit.Test;

import com.miklos.notemanager.backend.entities.Note;
import com.miklos.notemanager.backend.entities.TextNote;
import com.miklos.notemanager.backend.entities.User;
import com.miklos.notemanager.backend.liveedit.BroadcastReceiver;
import com.miklos.notemanager.backend.liveedit.Broadcaster;
import com.miklos.notemanager.backend.liveedit.MergedNoteEvent;
import com.miklos.notemanager.backend.liveedit.NoteEditedEvent;

import junit.framework.Assert;

public class BroadcasterTest {
	
	String lastMerged;
	
	@Test
	public void parallelEditingTest() {
		TextNote note = new TextNote("title", "The goat is hit by a big green car");
		Broadcaster broadcaster = new Broadcaster(note, null);
		
		
		
		BroadcastReceiver testReceiver = new BroadcastReceiver() {
			
			@Override
			public void receiveBroadcast(MergedNoteEvent event) {
				lastMerged = event.getMerged().getText();
			}
		};
		
		broadcaster.register(testReceiver);
		
		Thread a = new Thread(() -> {
			TextNote newVersion = new TextNote("title", "The white goat is hit by a big green car");
			broadcaster.onEdit(new NoteEditedEvent(newVersion, new User()));
		});
		
		Thread b = new Thread(() -> {
			TextNote newVersion = new TextNote("title", "The goat is hit by a big red car");
			broadcaster.onEdit(new NoteEditedEvent(newVersion, new User()));
		});
		
		a.start();
		b.start();
		
		try {
			a.join();
			b.join();
		
		
			Assert.assertEquals("The white goat is hit by a big red car", lastMerged);
		} catch (InterruptedException e) {
			
		}
	}
	
	@Test
	public void parallelEditingTest1() {
		TextNote note = new TextNote("title", "I love drinking beer");
		Broadcaster broadcaster = new Broadcaster(note, null);
		
		BroadcastReceiver testReceiver = new BroadcastReceiver() {
			
			@Override
			public void receiveBroadcast(MergedNoteEvent event) {
				lastMerged = event.getMerged().getText();
			}
		};
		
		broadcaster.register(testReceiver);
		
		Thread a = new Thread(() -> {
			TextNote newVersion = new TextNote("title", "I hate drinking wine");
			broadcaster.onEdit(new NoteEditedEvent(newVersion, new User()));
		});
		
		Thread b = new Thread(() -> {
			TextNote newVersion = new TextNote("title", "I love eating beer");
			broadcaster.onEdit(new NoteEditedEvent(newVersion, new User()));
		});
		
		a.start();
		b.start();
		
		try {
			a.join();
			b.join();
		
		
			Assert.assertEquals("I hate eating wine", lastMerged);
		} catch (InterruptedException e) {
			
		}
	}
	
	
	@Test
	public void parallelEditingTest2() {
		TextNote note = new TextNote("title", "Szeretem az őszt, de még a nyarat is");
		Broadcaster broadcaster = new Broadcaster(note, null);
		
		
		
		BroadcastReceiver testReceiver = new BroadcastReceiver() {
			
			@Override
			public void receiveBroadcast(MergedNoteEvent event) {
				lastMerged = event.getMerged().getText();
			}
		};
		
		broadcaster.register(testReceiver);
		
		Thread a = new Thread(() -> {
			TextNote newVersion = new TextNote("title", "Szeretem a nyarat, de még a tavaszt is");
			broadcaster.onEdit(new NoteEditedEvent(newVersion, new User()));
		});
		
		Thread b = new Thread(() -> {
			TextNote newVersion = new TextNote("title", "Utálom az őszt, de még a nyarat is");
			broadcaster.onEdit(new NoteEditedEvent(newVersion, new User()));
		});
		
		Thread c = new Thread(() -> {
			TextNote newVersion = new TextNote("title", "Utálom az őszt, de a nyarat nem");
			broadcaster.onEdit(new NoteEditedEvent(newVersion, new User()));
		});
		
		a.start();
		b.start();
		c.start();
		try {
			a.join();
			b.join();
			c.join();
		
			Assert.assertEquals("Utálom a nyarat, de a tavaszt nem", lastMerged);
		} catch (InterruptedException e) {
			
		}
	}
}
