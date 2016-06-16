package unittests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.miklos.notemanager.backend.assist.PDFValidator;
import com.miklos.notemanager.backend.entities.Note;
import com.miklos.notemanager.backend.entities.Notebook;
import com.miklos.notemanager.backend.entities.PDFNote;
import com.miklos.notemanager.backend.entities.TextNote;
import com.miklos.notemanager.backend.repositories.FileRepository;
import com.miklos.notemanager.backend.repositories.GenericRepository;
import com.miklos.notemanager.backend.services.NoteService;
import com.miklos.notemanager.backend.services.NoteService.InvalidPDFFileException;
import com.miklos.notemanager.backend.services.NoteServiceImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import java.io.File;

@RunWith(MockitoJUnitRunner.class)
public class NoteServiceTest {

	@InjectMocks
	private NoteService noteService = new NoteServiceImpl();

	@Mock
	private GenericRepository<Notebook> notebookRepo;

	@Mock
	private GenericRepository<Note> noteRepo;

	@Mock
	private FileRepository fileRepo;

	@Mock
	private PDFValidator pdfValidator;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void addTextNote() {
		Notebook notebook = new Notebook("notebook title");

		noteService.createTextNote("notetitle", "notetext very such long wow", notebook);

		assertEquals("notetitle", notebook.getNotes().get(0).getTitle());
		assertEquals("notetext very such long wow", ((TextNote)notebook.getNotes().get(0)).getText());
		verify(noteRepo, times(1)).persist(notebook.getNotes().get(0));
		verify(notebookRepo, times(1)).save(notebook);
	}

	@Test
	public void successAddPDFNote() {
		Notebook notebook = new Notebook("notebook title");
		File file = new File("asd");

		when(pdfValidator.isValidPDF(any())).thenReturn(true);
		when(fileRepo.saveFile(any())).thenReturn("copyed");

		try {
			noteService.createPDFNote("notetitle", file, notebook);
		} catch (InvalidPDFFileException e) {
			fail("should not throw exception");
		}finally{
			assertEquals("notetitle", notebook.getNotes().get(0).getTitle());
			assertEquals("copyed", ((PDFNote)notebook.getNotes().get(0)).getPdfURL());

			verify(noteRepo, times(1)).persist(notebook.getNotes().get(0));
			verify(notebookRepo, times(1)).save(notebook);
		}
	}
	@Test
	public void unsuccessAddPDFNote() {
		Notebook notebook = new Notebook("notebook title");
		File file = new File("asd");

		when(pdfValidator.isValidPDF(any())).thenReturn(false);
		when(fileRepo.saveFile(any())).thenReturn("copyed");

		try {
			noteService.createPDFNote("notetitle", file, notebook);
			fail("should throw exception");
		} catch (InvalidPDFFileException e) {

		}finally{

		}
	}
	
	@Test
	public void syncUpdated() {
		Note note = new TextNote();
	}
	
	@Test
	public void syncCommit() {
		
	}
	
	@Test
	public void syncMerge() {
		
	}

}
