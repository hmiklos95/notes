package com.miklos.notemanager.frontend.login;

import java.io.Serializable;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;

import com.miklos.notemanager.backend.entities.User;

/**
 * Simple interface for authentication and authorization checks.
 */

@Model
public interface AccessControl {

    public boolean signIn(String username, String password);
    
    public void logout();
    
    public User getLoggedInUser();

    public boolean isUserSignedIn();

	boolean register(String username, String password, String fullName, int age);
}
