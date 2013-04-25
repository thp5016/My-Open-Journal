package Test;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Messagebox.ClickEvent;
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
		String pass;
		
		pass = SessionManager.saltAndHash(newPass.getText());
		if(manager.IsValidPassword(SessionManager.GetUser(), currentPass.getText())) {
			manager.ChangeProfile(SessionManager.GetUser(), pass, firstName.getText(), lastName.getText());
			EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
				public void onEvent(ClickEvent event)
				{
					Executions.sendRedirect("index.zul");
				}
			};
			Messagebox.show("Your profile has sucessfully been updated!!", "", new Messagebox.Button[]{
		        Messagebox.Button.OK}, Messagebox.INFORMATION, clickListener);
		}
		else {
			Messagebox.show("Invalid password!!");
		}
	}
	
}
