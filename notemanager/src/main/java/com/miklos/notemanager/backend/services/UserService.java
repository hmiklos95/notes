package com.miklos.notemanager.backend.services;

import javax.enterprise.inject.Model;

import com.miklos.notemanager.backend.entities.User;

@Model
public interface UserService {
	public void registerUser(User user) throws UserAlreadyExistsException;
	public User logonUser(String username, String password) throws InvalidCredentialsException;
	
	public User getByUserName(String username);
	
	public static class UserAlreadyExistsException extends Exception {}
	public static class InvalidCredentialsException extends Exception {}
}