package Test;

import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.zkoss.io.Files;

public class Upload extends SelectorComposer<Component> {
   
	@Wire
	Textbox title;
	
	@Wire
	Textbox description;
	
	@Wire
	Textbox filePath;
	
	//
	@Listen("onClick = #submitPaper")
	public void InsertPaper()
	{
		int id;
		String path;
		String user;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
    	DBManager manager = new DBManager();
    	user = SessionManager.GetUser();
    	System.out.println("filename: " + filePath.getText());
    	id = manager.GetID(user);
    	path = "papers\\" + user + "\\" + filePath.getText();
    	manager.InsertPaper(id, title.getText(), path, description.getText(), dateFormat.format(date));
    	Executions.sendRedirect("index.zul");
	}
	
	// Uploads file to the server to directory C:\tomcat\webapps\ROOT\papers
	@Listen("onUpload = #uploadPaper")
	public void UploadPaper(UploadEvent event){
		
		// Get the path to upload the file to
		String path = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
		path = path + "papers/" + SessionManager.GetUser() + "/";
		System.out.println("Path: " + path);
		Media media = event.getMedia();
		if(media == null)
			System.out.println("NULL!!");
    	filePath.setText(media.getName());		
		// Copy file to server
		try {
			Files.copy(new File(path + media.getName()), media.getStreamData());
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("fail!!");
		  }
    }

}
