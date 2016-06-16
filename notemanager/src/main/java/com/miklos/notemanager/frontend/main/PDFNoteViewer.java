package com.miklos.notemanager.frontend.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.miklos.notemanager.backend.entities.Note;
import com.miklos.notemanager.backend.entities.PDFNote;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Window;

public class PDFNoteViewer extends NoteViewer {

	@Override
	public void open(Note note) {

		String fileName = ((PDFNote)note).getPdfURL();
		
		StreamResource.StreamSource s = new StreamResource.StreamSource() {

            /**
             * 
             */
            private static final long serialVersionUID = 9138325634649289303L;

            public InputStream getStream() {
                try {

                	
            		File f = new File(fileName);
                    FileInputStream fis = new FileInputStream(f);
                    return fis;

                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
		};

		StreamResource r = new StreamResource(s, fileName);
		Embedded e = new Embedded();
		e.setSizeFull();
		e.setType(Embedded.TYPE_BROWSER);
		r.setMIMEType("application/pdf");
		e.setSource(r);
		addComponent(e);
		

	}
}
