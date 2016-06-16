package com.miklos.notemanager.backend.assist;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;

@Model
public interface RootPathProvider {
	public String getRootPath();
}	
