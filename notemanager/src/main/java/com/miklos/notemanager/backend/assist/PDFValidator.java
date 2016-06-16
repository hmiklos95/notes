package com.miklos.notemanager.backend.assist;

import java.io.File;


import javax.enterprise.inject.Model;

@Model

public interface PDFValidator {
	public boolean isValidPDF(final File file);
}
