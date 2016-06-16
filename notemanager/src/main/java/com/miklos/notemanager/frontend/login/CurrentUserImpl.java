package com.miklos.notemanager.frontend.login;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.miklos.notemanager.backend.entities.User;
import com.miklos.notemanager.backend.services.UserService;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;

/**
 * Class for retrieving and setting the name of the current user of the current
 * session (without using JAAS). All methods of this class require that a
 * {@link VaadinRequest} is bound to the current thread.
 * 
 * 
 * @see com.vaadin.server.VaadinService#getCurrentRequest()
 */

@Model
@Default
public final class CurrentUserImpl implements CurrentUser {

    /**
     * The attribute key used to store the username in the session.
     */
    public static final String CURRENT_USER_SESSION_ATTRIBUTE_KEY = CurrentUserImpl.class
            .getCanonicalName();

    @Inject
    UserService userService;

    /* (non-Javadoc)
	 * @see com.miklos.notemanager.frontend.login.CurrentUser#get()
	 */
    @Override
	public User get() {
        String currentUser = (String) getCurrentRequest().getWrappedSession()
                .getAttribute(CURRENT_USER_SESSION_ATTRIBUTE_KEY);
        if (currentUser == null) {
            return null;
        } else {
            return userService.getByUserName(currentUser);
        }
    }

    /* (non-Javadoc)
	 * @see com.miklos.notemanager.frontend.login.CurrentUser#set(com.miklos.notemanager.backend.entities.User)
	 */
    @Override
	public void set(User currentUser) {
        if (currentUser == null) {
            getCurrentRequest().getWrappedSession().removeAttribute(
                    CURRENT_USER_SESSION_ATTRIBUTE_KEY);
        } else {
            getCurrentRequest().getWrappedSession().setAttribute(
                    CURRENT_USER_SESSION_ATTRIBUTE_KEY, currentUser.getName());
        }
    }

    private VaadinRequest getCurrentRequest() {
        VaadinRequest request = VaadinService.getCurrentRequest();
        if (request == null) {
            throw new IllegalStateException(
                    "No request bound to current thread");
        }
        return request;
    }

	@Override
	public void remove() {
		getCurrentRequest().getWrappedSession().removeAttribute(
                CURRENT_USER_SESSION_ATTRIBUTE_KEY);
		
		getCurrentRequest().getWrappedSession().removeAttribute(
                "counter");
	}

}
