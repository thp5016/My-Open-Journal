package Test;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.*;
import org.zkoss.zul.Captcha;
import org.zkoss.zul.Textbox;

// Registers the user with info specified in registration form and inserts a new
// entry into the databse
public class Register extends SelectorComposer<Component> {
	
	// Textbox components from registration page
	@Wire
	Textbox first;	
	@Wire
	Textbox last;
	@Wire
	Textbox username;
	@Wire
	Textbox email;
	@Wire
	Textbox pwd;
	
	@Wire
	Captcha cpa;
	
	// Runs when user clicks register button
    @Listen("onClick = #registerButton")
    public void InsertUser() {
    	DBManager manager = new DBManager();
    	// Insert User into database
    	manager.InsertUser(username.getText(), first.getText(), last.getText(), pwd.getText(), "0"); 	
    }
	
}
