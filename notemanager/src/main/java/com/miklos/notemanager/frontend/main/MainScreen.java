package com.miklos.notemanager.frontend.main;

import java.util.ArrayList;
import java.util.List;


import com.miklos.notemanager.backend.entities.Note;
import com.miklos.notemanager.backend.entities.Notebook;
import com.miklos.notemanager.backend.entities.TextNote;
import com.miklos.notemanager.backend.entities.User;
import com.miklos.notemanager.backend.liveedit.BroadcastReceiver;
import com.miklos.notemanager.backend.liveedit.Broadcaster;
import com.miklos.notemanager.backend.liveedit.BroadcasterFactory;
import com.miklos.notemanager.backend.liveedit.MergedNoteEvent;
import com.miklos.notemanager.backend.services.NoteService;

import com.miklos.notemanager.backend.services.NotebookService;
import com.miklos.notemanager.backend.services.UserService;
import com.miklos.notemanager.frontend.login.AccessControl;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid;

import com.vaadin.ui.UI;

public class MainScreen extends MainScreenDesign implements View, BroadcastReceiver {

	private UI ui;
	private AccessControl accessControl;
	private User loggedInUser;
	
	private NoteService noteService;
	private UserService userService;
	private NotebookService notebookService;
	
	private NoteViewer noteViewer;
	private Grid notebookGrid;
	private Grid noteGrid;
	
	private BeanItemContainer container;
	
	private List<Broadcaster> registeredBroadcasters;
	
	private BroadcasterFactory factory;

	public MainScreen(UI ui, AccessControl accessControl, NoteService noteService, UserService userService,
			NotebookService notebookService, BroadcasterFactory broadcasterFactory) {
		this.ui = ui;
		this.accessControl = accessControl;
		this.noteService = noteService;
		this.userService = userService;
		this.notebookService = notebookService;
		this.notebookGrid = new Grid();
		this.noteGrid = new Grid();
		this.registeredBroadcasters = new ArrayList<Broadcaster>();
		this.factory = broadcasterFactory;
		
		
		addListeners();
	}

	private void compose() {
		com.vaadin.ui.HorizontalSplitPanel panel = new com.vaadin.ui.HorizontalSplitPanel();
		panel.setFirstComponent(notebookGrid);
		panel.setSecondComponent(noteGrid);
		mainsplitPanel.setFirstComponent(panel);
		notebookGrid.setColumns("name");
		notebookGrid.setSizeFull();
		noteGrid.setSizeFull();
		noteGrid.setColumns("title");
		
		mainsplitPanel.setSizeFull();
		mainsplitPanelWrapper.setSizeFull();
    }
	
	private void addListeners() {
		notebookGrid.addSelectionListener((event) -> {
			loadNotes((Notebook)event.getSelected().iterator().next());
		});
		
		noteGrid.addSelectionListener((event) -> {
			loadNote((Note)event.getSelected().iterator().next());
		});
		
		logout.addClickListener((event) -> {
        	logout();
        });
		

	}
	
	private void loadNotebooks() {
		List<Notebook> notebooks = loggedInUser.getNoteBooks();
		
		BeanItemContainer<Notebook> container = new BeanItemContainer<Notebook>(Notebook.class, notebooks);
		notebookGrid.setContainerDataSource(container);
	}
	
	private void loadNotes(Notebook notebook) {
		List<Note> notes = notebook.getNotes();
		/*BeanItemContainer */container = new BeanItemContainer<>(Note.class, notes);
		noteGrid.setContainerDataSource(container);
		
		
		sync.addClickListener((event) -> {
			//syncNotes(notes);
		});
	}
	
	
	
	private void loadNote(Note note) {
		noteViewer = NoteViewerFactory.createNoteViewer(note);
		noteViewer.setSizeFull();
		noteViewer.setImmediate(true);
		noteViewer.open(note);
		mainsplitPanel.setSecondComponent(noteViewer);
	}

    private void logout() {
    	accessControl.logout();
    	ui.getNavigator().navigateTo("login");
    	for(Broadcaster broadcaster: registeredBroadcasters) {
    		broadcaster.unregister(this);
    	}
    }
    
	@Override
	public void enter(ViewChangeEvent event) {
		if(!accessControl.isUserSignedIn()) {
			ui.getNavigator().navigateTo("login");
		}else{
			loggedInUser = accessControl.getLoggedInUser();
			
			loadNotebooks();
		}
	}
	
	@Override
	public void receiveBroadcast(MergedNoteEvent event) {
		
	}
}
