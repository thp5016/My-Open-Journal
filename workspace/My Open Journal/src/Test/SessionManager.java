/* SessionManager.java
 * This class defines functionality to manage
 * user sessions within the MyOpenJournal site
 * 
 * Change History
 * 
 * 4/9/13 - John Larimer - Initial Development
 * 
 */

package Test;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Initiator;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Include;

// This class provides Cookie functionality for the website so that users can login
// and logout, and so that the website can provide user-specific functionality
public class SessionManager extends SelectorComposer<Component> implements Initiator {

	@Wire("#loggedIn")
	Hbox loggedIn;
	
	@Wire("#notLoggedIn")
	Hbox notLoggedIn;
	
	// Creates a new session that means that the user has logged in
	public static void setSession(String username, String password) {
		try {
			Sessions.getCurrent().setAttribute("user", username);
			Sessions.getCurrent().setAttribute("password", password);
			Executions.sendRedirect("index.zul");
		} 
		finally {
		}
	}

	// Checks to see if the user is logged in
	public boolean checkSession() {
		Session zkSession = Executions.getCurrent().getDesktop().getSession();
		String username = (String) zkSession.getAttribute("user");
		if (username == null) {
			return false;
		} 
		else {
			return true;
		}
	}

	// Checks to see if user is logged in and shows the correct navbar
	@Override
	public void doInit(Page arg0, Map<String, Object> arg1) throws Exception {
		Session zkSession = Executions.getCurrent().getDesktop().getSession();
		String username = (String) zkSession.getAttribute("user");
		if (username == null) {
	        final HashMap<String, Object> map = new HashMap<String, Object>();
	        Executions.createComponents("navbar.zul", null, map);
			return;
		} 
		else {
	        final HashMap<String, Object> map = new HashMap<String, Object>();
	        Executions.createComponents("navbarlogin.zul", null, map);
			return;
		}

	}
}
