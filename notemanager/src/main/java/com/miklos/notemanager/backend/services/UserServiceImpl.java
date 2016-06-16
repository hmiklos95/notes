package com.miklos.notemanager.backend.services;



import java.util.List;
import java.util.Set;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.miklos.notemanager.backend.entities.User;
import com.miklos.notemanager.backend.entities.User_;
import com.miklos.notemanager.backend.repositories.GenericRepository;


@Model
@Default
public class UserServiceImpl implements UserService{

	@Inject GenericRepository<User> userRepo;

	@Override
	public void registerUser(User user) throws UserAlreadyExistsException {
		User found = userRepo.findBySpecification((root, query, cb) -> cb.equal(root.get(User_.name), user.getName()), User.class);
		if (found != null) {
			throw new UserAlreadyExistsException();
		}else{
			userRepo.persist(user);
		}
		
	}

	@Override
	public User logonUser(String username, String password) throws InvalidCredentialsException{
		User found = userRepo.findBySpecification((root, query, cb) -> cb.equal(root.get(User_.name), username), User.class);
		if(found == null || !password.equals(found.getPassword())) {
			throw new InvalidCredentialsException();
		}
		return found;
	}

	@Override
	public User getByUserName(String username) {
		return userRepo.findBySpecification((root, query, cb) -> cb.equal(root.get(User_.name), username), User.class);
	}
}
