package com.miklos.notemanager.backend.repositories;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import static java.nio.file.StandardCopyOption.*;

import com.miklos.notemanager.backend.assist.RootPathProvider;

@Model
@Default
public class FileRepositoryImpl implements FileRepository {
	
	@Inject RootPathProvider provider;
	
	@Override
	public String saveFile(File toSave) {
		String path = provider.getRootPath();
		
		try {
			Files.copy(toSave.toPath(), new File(path + File.separator + toSave.getName()).toPath());
			return path + File.separator + toSave.getName();
		} catch (IOException e) {
			return "";
		}
	}

	@Override
	public void deleteFiles() {
		// TODO Auto-generated method stub
		
	}

}
