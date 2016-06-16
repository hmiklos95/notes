package unittests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.miklos.notemanager.backend.entities.User;
import com.miklos.notemanager.backend.repositories.GenericRepository;
import com.miklos.notemanager.backend.services.UserService;
import com.miklos.notemanager.backend.services.UserService.InvalidCredentialsException;
import com.miklos.notemanager.backend.services.UserService.UserAlreadyExistsException;
import com.miklos.notemanager.backend.services.UserServiceImpl;

import static org.mockito.Mockito.*;



@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	
	@InjectMocks 
	private UserService service = new UserServiceImpl();
	
	@Mock
	private GenericRepository<User> mockRepo;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void successfulRegister() {
		String username = "miki";
		String password = "123456";
		String displayName = "Miklós";
		int age = 10;
		
		User user = new User(username, password, displayName, age);
		
		try {
			service.registerUser(user);
		} catch (UserAlreadyExistsException e) {
			fail("an exception should not be thrown");
		}
		
		verify(mockRepo, times(1)).persist(user);
	}
	
	@Test
	public void alreadyExistingUser() {
		String username = "miki";
		String password = "123456";
		String displayName = "Miklós";
		int age = 10;
		
		User user = new User(username, password, displayName, age);
		
		
		when(mockRepo.findBySpecification(any(), any())).thenReturn(user);
		
		
		try {
			service.registerUser(user);
			fail("exception should be thrown");
		} catch (UserAlreadyExistsException e) {
			
		}
		
	}
	
	@Test
	public void successfulLogon() {
		String username = "miki";
		String password = "verysecurepassword";
		String displayName = "Miklós";
		int age = 10;
		
		User user = new User(username, password, displayName, age);
		
		when(mockRepo.findBySpecification(any(), any())).thenReturn(user);
		try {
			service.logonUser(username, password);
		} catch (InvalidCredentialsException e) {
			fail("exception should not be thrown");
		}
	}
	
	@Test
	public void invalidUsername() {
		String username = "miki";
		String password = "verysecurepassword";
		
		when(mockRepo.findBySpecification(any(), any())).thenReturn(null);
		try {
			service.logonUser(username, password);
			fail("exception should be thrown");
		} catch (InvalidCredentialsException e) {
		}
	}
}
