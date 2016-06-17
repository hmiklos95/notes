package com.miklos.notemanager;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;

import com.miklos.notemanager.backend.liveedit.BroadcasterFactory;
import com.miklos.notemanager.backend.services.NoteService;
import com.miklos.notemanager.backend.services.NotebookService;
import com.miklos.notemanager.backend.services.UserService;
import com.miklos.notemanager.frontend.login.AccessControl;
import com.miklos.notemanager.frontend.login.BasicAccessControl;
import com.miklos.notemanager.frontend.login.LoginScreen;
import com.miklos.notemanager.frontend.login.LoginScreen.LoginListener;
import com.miklos.notemanager.frontend.main.MainScreen;
import com.miklos.notemanager.frontend.register.RegisterScreen;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.cdi.CDIUI;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("theme")
@Widgetset("com.miklos.notemanager.MainWidgetset")
@CDIUI("")
@Push(value=PushMode.AUTOMATIC, transport=Transport.LONG_POLLING)
public class MainUI extends UI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private AccessControl accessControl;
	
	@Inject
	private NotebookService notebookService;
	@Inject
	private NoteService noteService;
	@Inject
	private UserService userService;
	
	@Inject 
	private BroadcasterFactory factory;
	
	private Navigator navigator;
	
	private MainScreen mainScreen;


	@Override
	protected void init(VaadinRequest vaadinRequest) {
		Responsive.makeResponsive(this);
		setLocale(vaadinRequest.getLocale());
		initNavigator();
		checkLoggedIn();
	}

	private void initNavigator() {
		navigator = new Navigator(UI.getCurrent(), this);
		navigator.addView("login", new LoginScreen(this, accessControl, new LoginListener() {
			@Override
			public void loginSuccessful() {
				showMainView();
			}
		}));
		
		navigator.addView("register", new RegisterScreen(this, accessControl));
		
		mainScreen = new MainScreen(this, accessControl, noteService, userService, notebookService, factory);
		
		navigator.addView("", mainScreen);
		
		
	}
	
	private void checkLoggedIn() {
		if (!accessControl.isUserSignedIn()) {
			navigator.navigateTo("login");
		} else {
			showMainView();
		}
	}

	
	private void showMainView() {
		navigator.navigateTo("");
	}
	
	
	
	public Navigator getNavigator() {
		return navigator;
	}
}
