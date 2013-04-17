package Test;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.impl.InputElement;




public class EditProfile extends GenericForwardComposer {
	
	@Wire
	Textbox firstName;

	@Wire
	Textbox lastName;
	
	@Wire
	Textbox newPass;
	
	@Wire
	Textbox currentPass;
	
	public void onCreate$firstName() {
		String user;
		String first;
		DBManager manager = new DBManager();

		user = SessionManager.GetUser();
		first = manager.GetFirstName(user);
		firstName.setText(first);
	}
	
	public void onCreate$lastName() {
		String user;
		String last;
		DBManager manager = new DBManager();

		user = SessionManager.GetUser();
		last = manager.GetLastName(user);
		lastName.setText(last);
	}
	
	public void onClick$saveChanges() {
		DBManager manager = new DBManager();
		if(manager.IsValidPassword(SessionManager.GetUser(), currentPass.getText())) {
			manager.ChangeProfile(SessionManager.GetUser(), newPass.getText(), firstName.getText(), lastName.getText());
		}
		else
			System.out.println("Invalid Password!!");
		Executions.sendRedirect("index.zul");
	}
	
}
