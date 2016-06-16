package com.miklos.notemanager.frontend.login;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.miklos.notemanager.backend.entities.User;
import com.miklos.notemanager.backend.services.UserService;
import com.miklos.notemanager.backend.services.UserService.InvalidCredentialsException;
import com.miklos.notemanager.backend.services.UserService.UserAlreadyExistsException;

/**
 * Default mock implementation of {@link AccessControl}. This implementation
 * accepts any string as a password, and considers the user "admin" as the only
 * administrator.
 */

@Model
@Default
public class BasicAccessControl implements AccessControl {

	@Inject
	private UserService userService;
	
	@Inject
	private CurrentUser currentUser;
	
    @Override
    public boolean signIn(String username, String password) {
        if (username == null || username.isEmpty())
            return false;
        try {
			User user = userService.logonUser(username, password);
			currentUser.set(user);
			return true;
		} catch (InvalidCredentialsException e) {
			return false;
		}
    	
    }

    @Override
    public boolean isUserSignedIn() {
        return currentUser.get() != null;
    }

	@Override
	public boolean register(String username, String password, String fullName, int age) {
		try {
			userService.registerUser(new User(username, password, fullName, age));
			return true;
		} catch (UserAlreadyExistsException e) {
			return false;
		}
	}

	@Override
	public void logout() {
		currentUser.remove();
		
	}

	@Override
	public User getLoggedInUser() {
		return currentUser.get();
	}


}
