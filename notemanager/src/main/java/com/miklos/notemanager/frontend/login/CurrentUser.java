package com.miklos.notemanager.frontend.login;

import com.miklos.notemanager.backend.entities.User;

public interface CurrentUser {

	/**
	 * Returns the name of the current user stored in the current session, or an
	 * empty string if no user name is stored.
	 * 
	 * @throws IllegalStateException
	 *             if the current session cannot be accessed.
	 */
	User get();

	/**
	 * Sets the name of the current user and stores it in the current session.
	 * Using a {@code null} username will remove the username from the session.
	 * 
	 * @throws IllegalStateException
	 *             if the current session cannot be accessed.
	 */
	void set(User currentUser);
	
	void remove();

}