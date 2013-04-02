package Test;

import java.sql.Statement;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.*;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Textbox;

public class Login extends SelectorComposer<Component>{
	@Wire 
	Longbox username;
	Longbox pwd;
	
    @Listen("onClick = #register")
    public void LoginUser(){
    	DBManager manager = new DBManager();
    	if(manager.IsValidUser(username.getText()))
    	{
    		if(manager.IsValidPassword(username.getText(), pwd.getText()))
    			System.out.println("Correct Password!!");
    		else
    			System.out.println("Incorrect Password!!");
    	}
    }

}
