package com.miklos.notemanager.frontend.login;



import java.io.Serializable;

import javax.inject.Inject;

import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;

import com.vaadin.ui.Button;

import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

/**
 * UI content when the user is not logged in yet.
 */
public class LoginScreen extends LoginScreenDesign implements View {

    
	private LoginListener loginListener;
    
    
    private AccessControl accessControl;

    private UI ui;
    
    public LoginScreen(UI ui, AccessControl accessControl, LoginListener loginListener) {
        this.loginListener = loginListener;
        this.accessControl = accessControl;
        this.ui = ui;
        addListeners();
        username.focus();
    }


    private void addListeners() {
        
        login.setDisableOnClick(true);
        login.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    login();
                } finally {
                    login.setEnabled(true);
                }
            }
        });
        
        login.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        login.addStyleName(ValoTheme.BUTTON_FRIENDLY);

        register.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                ui.getNavigator().navigateTo("register");
            }
        });
    }

    private void login() {
        if (accessControl.signIn(username.getValue(), password.getValue())) {
            loginListener.loginSuccessful();
        } else {
            showNotification(new Notification("Login failed",
                    "Please check your username and password and try again.",
                    Notification.Type.HUMANIZED_MESSAGE));
            username.focus();
        }
    }

    private void showNotification(Notification notification) {
        // keep the notification visible a little while after moving the
        // mouse, or until clicked
        notification.setDelayMsec(2000);
        notification.show(Page.getCurrent());
    }

    public interface LoginListener extends Serializable {
        void loginSuccessful();
    }

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
