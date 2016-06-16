package com.miklos.notemanager.backend.repositories;

import java.io.File;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;

@Model
public interface FileRepository {
	public String saveFile(File toSave);
	
	
	public void deleteFiles();
}
