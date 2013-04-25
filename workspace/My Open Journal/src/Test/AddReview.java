package Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Messagebox.ClickEvent;
import org.zkoss.zul.impl.InputElement;


public class AddReview extends SelectorComposer<Grid> {

	@Wire
	Textbox typedReview;

	@Wire
	Label responsePaper;
	
	int paperID = SessionManager.GetPaper();
	String user = SessionManager.GetUser();


	@Listen("onClick = #submitReview")
	public void submitReview() {
		int id;
		String review;
		DBManager manager = new DBManager();
		id = manager.GetID(user);
		review = typedReview.getText();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		
		if(WordCount(review) > 500) {
			manager.InsertReview(paperID, id, review, dateFormat.format(date));
			EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
				public void onEvent(ClickEvent event) {
					Executions.sendRedirect("index.zul");
				}
			};
			Messagebox.show("Your review has been submitted!!", "", new Messagebox.Button[] {
					Messagebox.Button.OK}, Messagebox.INFORMATION, clickListener);
		}
		else {
			Messagebox.show("Review must be at least 500 words long!!");
		}
	}
	
	 private static int WordCount(String input) {
		    int wordCount = 0;

		    if (input.trim().equals("")) {
		        return wordCount;
		    }
		    else {
		        wordCount = 1;
		    }

		    for (int i = 0; i < input.length(); i++) {
		        char ch = input.charAt(i);
		        String str = new String("" + ch);
		        if (i+1 != input.length() && str.equals(" ") && !(""+ input.charAt(i+1)).equals(" ")) {
		            wordCount++;
		        }
		    }

		    return wordCount;
		 }

	@Listen("onClick = #backToPaper")
	public void goBack() {
		Executions.sendRedirect("paper.zul");
	}

	public void doAfterCompose(Grid comp) {
		DBManager manager = new DBManager();
		try {
			super.doAfterCompose(comp);
			responsePaper.setValue("In Response To: " + manager.GetPaperTitle(paperID));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
