package com.miklos.notemanager.frontend.register;

import java.io.Serializable;

import com.miklos.notemanager.frontend.login.AccessControl;
import com.miklos.notemanager.frontend.login.LoginScreen.LoginListener;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

public class RegisterScreen extends RegisterScreenDesign implements View {

	private UI ui;
	
	private AccessControl accessControl;
    
    public RegisterScreen(UI ui, AccessControl accessControl) {
        this.ui = ui;
        this.accessControl = accessControl;
    	addListeners();
    }


    private void addListeners() {
        
        register.addClickListener((event) -> {
        	register();
        });

    }


    private void register() {
    	if(accessControl.register(username.getValue(), password.getValue(), displayName.getValue(), Integer.parseInt(age.getValue()))) {
    		ui.getNavigator().navigateTo("login");
    	}else{
    		showNotification(new Notification("Register failed",
                    "Already existing user with that username",
                    Notification.Type.HUMANIZED_MESSAGE));
    	}
    }
    
    private void showNotification(Notification notification) {
        
        notification.setDelayMsec(2000);
        notification.show(Page.getCurrent());
    }
    
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

    
}
