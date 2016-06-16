package unittests;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.miklos.notemanager.backend.entities.Note;
import com.miklos.notemanager.backend.entities.Notebook;
import com.miklos.notemanager.backend.entities.TextNote;
import com.miklos.notemanager.backend.entities.User;
import com.miklos.notemanager.backend.repositories.GenericRepository;
import com.miklos.notemanager.backend.services.NotebookService;
import com.miklos.notemanager.backend.services.NotebookServiceImpl;



public class NotebookServiceTest {
	@InjectMocks 
	private NotebookService service = new NotebookServiceImpl();

	@Mock
	private GenericRepository<Notebook> notebookRepo;
	
	@Mock
	private GenericRepository<Note> noteRepo;
	
	@Mock
	private GenericRepository<User> userRepo;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void createNotebook() {
		User user = new User("miki", "Password", "Miklós", 10);
		Notebook created = new Notebook("Irtech");
		
		service.createNotebook(user, created);
		
		assertEquals(user.getNoteBooks().get(0), created);
		verify(notebookRepo, times(1)).persist(created);
		verify(userRepo, times(1)).save(user);
	}

	@Test
	public void addNoteToNotebook() {
		Note note = new TextNote("title", "this is a long text");
		Notebook notebook = new Notebook("notebook title");
		
		service.addNoteToNotebook(notebook, note);
		
		assertEquals(note, notebook.getNotes().get(0));
		verify(noteRepo, times(1)).persist(note);
	}
	
	@Test
	public void addUserToNotebook() {
		User user = new User();
		
		Notebook notebook = new Notebook();
		
		service.addUserToNotebook(notebook, user);
		
		assertEquals(notebook, user.getNoteBooks().get(0));
		verify(userRepo, times(1)).save(user);
		verify(notebookRepo, times(1)).save(notebook);
	}
}
