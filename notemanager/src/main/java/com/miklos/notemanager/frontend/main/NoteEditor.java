package com.miklos.notemanager.frontend.main;

import javax.inject.Inject;

import com.miklos.notemanager.backend.entities.Note;
import com.miklos.notemanager.backend.entities.TextNote;
import com.miklos.notemanager.backend.services.NoteService;
import com.vaadin.data.Property.ReadOnlyException;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.converter.Converter.ConversionException;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextArea;

public class NoteEditor extends NoteViewer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TextNote currentNote;
	
	private RichTextArea textArea;
	
	private TextArea area;
	
	public NoteEditor() {
		textArea = new RichTextArea();
		area = new TextArea();
		textArea.setSizeFull();
		textArea.setImmediate(true);
		//addComponent(textArea);
		
		area.setSizeFull();
		area.setImmediate(true);
		addComponent(area);
	}
	
	public void open(Note note) {
		this.currentNote = (TextNote)note;
		BeanItem<Note> item = new BeanItem<Note>(note);
		area.setPropertyDataSource(item.getItemProperty("text"));
		
	}

	public void setValue(String newFieldValue) throws ReadOnlyException, ConversionException {
		currentNote.setText(newFieldValue);
		area.markAsDirty();
	}

	public void focus() {
		area.focus();
	}

	public void addTextChangeListener(TextChangeListener listener) {
		area.addTextChangeListener((event)->{
			currentNote.setText(event.getText());
			listener.textChange(event);
		});
	}
	

	public Note getCurrentNote() {
		return currentNote;
	}

	
	
	
}