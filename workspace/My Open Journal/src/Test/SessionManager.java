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

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Window;

// This class provides Cookie functionality for the website so that users can login
// and logout, and so that the website can provide user-specific functionality
public class SessionManager extends Window
{
  public void setSession(String username, String password)
  {
    try{
      Session s = Sessions.getCurrent();
      s.setAttribute("user", username);
      Executions.sendRedirect("index.zul");
    }finally {}
  }	
  public void checkSession() 
  {
	  boolean valid = Sessions.getCurrent().getAttribute("user") != null ? true : false;
	  if(!valid)
	  {
		  Executions.sendRedirect("timeout.zul");
	  }
  }
}