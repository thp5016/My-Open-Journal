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
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.util.Initiator;

// This class provides Cookie functionality for the website so that users can login
// and logout, and so that the website can provide user-specific functionality
public class SessionManager extends SelectorComposer<Component> implements Initiator {
	
	// Creates a new session that means that the user has logged in
	public static void setSession(String username, String password) {
		try {
			Sessions.getCurrent().setAttribute("user", username);
			Sessions.getCurrent().setAttribute("password", password);
		} 
		finally {
		}
	}
	
	public static void SetPaper(int paperID) {
		Sessions.getCurrent().setAttribute("paper", paperID);
	}
	
	public static int GetPaper(){
		Session zkSession = Executions.getCurrent().getDesktop().getSession();
		int paperID = (int) zkSession.getAttribute("paper");
		return paperID;
	}
	
	public static void SetReviewID(int reviewID) {
		Sessions.getCurrent().setAttribute("review", reviewID);
	}
	
	public static int GetReviewID() {
		Session zkSession = Executions.getCurrent().getDesktop().getSession();
		int id = (int) zkSession.getAttribute("review");
		return id;
	}

	// Returns the username of the user currenty logged in
	public static String GetUser()
	{
		Session zkSession = Executions.getCurrent().getDesktop().getSession();
		String username = (String) zkSession.getAttribute("user");
		return username;
	}
	
	public static void Logout()
	{
		Sessions.getCurrent().removeAttribute("user");
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
		
		// User is not logged in so show navbar allowing them to login/register
		if (username == null) {
	        final HashMap<String, Object> map = new HashMap<String, Object>();
	        Executions.createComponents("navbar.zul", null, map);
			return;
		} 
		
		// User is logged in so show navbar that allows them to view profile/submit paper
		else {
	        final HashMap<String, Object> map = new HashMap<String, Object>();
	        Executions.createComponents("navbarlogin.zul", null, map);
			return;
		}

	}
	// Applies a salt to the passed input and hashes the input with MD5
	// Returns the hashed and salted input as a String
	public static String saltAndHash(String input)
	{
		String md5 = null;
		String salt = "Random$SaltValue#WithSpecialCharacters12@$@4&#%^$*";
        String saltedInput = input + salt;
        
        if(null == input) return null;
         
        try {
             
	        //Create MessageDigest object for MD5
	        MessageDigest digest = MessageDigest.getInstance("MD5");
	         
	        //Update input string in message digest
	        digest.update(saltedInput.getBytes(), 0, saltedInput.length());
	 
	        //Converts message digest value in base 16 (hex)
	        md5 = new BigInteger(1, digest.digest()).toString(16);
 
        } catch (NoSuchAlgorithmException e) {
 
            e.printStackTrace();
        }
        return md5;
	}
}
