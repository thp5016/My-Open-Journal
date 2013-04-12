package Test;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.*;
import org.zkoss.zul.Textbox;

public class Login extends SelectorComposer<Component>{
	
	@Wire 
	Textbox username;
	
	@Wire
	Textbox pwd;
	
    @Listen("onClick = #login")
    public void LoginUser(){
    	DBManager manager = new DBManager();
    	if(manager.IsValidUser(username.getText()))
    	{
    		if(manager.IsValidPassword(username.getText(), pwd.getText()))
    		{
    			SessionManager.setSession(username.getText(), pwd.getText());
    			Executions.sendRedirect("index.zul");
    		}
    		else
    			System.out.println("Incorrect Password!!");
    	}
    	else
    		System.out.println("Invalid User!!");
    }
    
    @Listen("onClick = #register")
    public void RegisterUser() {
    	Executions.sendRedirect("register.zul");
    }

}
