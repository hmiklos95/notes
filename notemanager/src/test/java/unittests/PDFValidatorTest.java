package unittests;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.miklos.notemanager.backend.assist.PDFValidator;
import com.miklos.notemanager.backend.assist.PDFValidatorImpl;

import static org.junit.Assert.*;

public class PDFValidatorTest {
	
	PDFValidator validator = new PDFValidatorImpl();
	
	@Test
	public void testValidFile() {
		URI url;
		try {
			url = this.getClass().getResource("/resources/konzultacio.pdf").toURI();
			
			File validFile = new File(url);
			assertEquals(true, validator.isValidPDF(validFile));
		} catch (URISyntaxException e) {
			fail();
		}
		
		
	}
	
	@Test
	public void testInvalidFile() {
		URI url;
		try {
			url = this.getClass().getResource("/resources/ca.key").toURI();
			
			File validFile = new File(url);
			assertEquals(false, validator.isValidPDF(validFile));
		} catch (URISyntaxException e) {
			fail();
		}
	}
}
