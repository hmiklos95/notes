package com.miklos.notemanager.backend.assist;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;

@Model
@Default
public class RootPathProviderImpl implements RootPathProvider {

	@Override
	public String getRootPath() {
		return "C:\\notemanager";
	}

}
