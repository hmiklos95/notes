package com.miklos.notemanager.backend.entities;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@PrimaryKeyJoinColumn(referencedColumnName="id")
@Entity
@DiscriminatorValue( value="PDFN" )
public class PDFNote extends Note {
	private String pdfURL;
	private String title;
	
	public PDFNote(String title, String pdfURL) {
		super(title);
		this.pdfURL = pdfURL;
	}
	
	public PDFNote() {
		this.pdfURL = "";
	}

	public String getPdfURL() {
		return pdfURL;
	}
}
