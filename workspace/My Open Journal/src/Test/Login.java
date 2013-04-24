package Test;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.*;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Messagebox.ClickEvent;

public class Login extends SelectorComposer<Component>{
	
	// Textbox containing inputed username
	@Wire 
	Textbox username;
	
	// Textbox containing inputed password
	@Wire
	Textbox pwd;
	
	// Checks to see if the password matches the username. If so it
	// logs the user in and redirects to homepage
    @Listen("onClick = #login")
    public void LoginUser(){
    	DBManager manager = new DBManager();
    	// Check to see if username is already registered
    	if(manager.IsValidUser(username.getText())) {
    		
    		// Check to see if password matches username
    		if(manager.IsValidPassword(username.getText(), pwd.getText())) {
    			SessionManager.setSession(username.getText(), pwd.getText());
    			EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
    				public void onEvent(ClickEvent event)
    				{
    					Executions.sendRedirect("index.zul");
    				}
    			};
    			Messagebox.show(username.getText() + " has successfully logged in!!", "", new Messagebox.Button[]{
    		        Messagebox.Button.OK}, Messagebox.INFORMATION, clickListener);
    		}
    		else
    			Messagebox.show("Invalid password!!");    	
    	}
    	else
			Messagebox.show("Invalid username!!");    	
    }
    
    // Redirect user to register page if they click register button
    @Listen("onClick = #register")
    public void RegisterUser() {
    	Executions.sendRedirect("register.zul");
    }

}
