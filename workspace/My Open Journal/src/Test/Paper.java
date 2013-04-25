package Test;

import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zk.ui.util.Initiator;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Messagebox.ClickEvent;
import org.zkoss.zul.impl.InputElement;

public class Paper extends SelectorComposer<Grid> {

	@Wire
	Label paperTitle;

	@Wire
	Label byAuthor;

	@Wire
	Label description;

	@Wire
	Toolbarbutton upVotes;

	@Wire
	Toolbarbutton dnVotes;

	//need to implement a function in session manager
	int paperID = SessionManager.GetPaper();
	String user = SessionManager.GetUser();

	@Listen("onClick = #downloadLink")
	public void viewPaper() {
		// redirect to the pdf id specified by the page url
		// Executions.sendRedirect(/* pdf url */);
		String path;

		DBManager manager = new DBManager();
		path = manager.GetPaperPath(paperID);

		Executions.sendRedirect(path);
	}

	@Listen("onClick = #upVotes")
	public void upVote() {
		// add a new entry to the paper votes table
		// update the paper's upvotes count
		int userID;
		DBManager manager = new DBManager();
		userID = manager.GetID(user);
		if(userID != -1) {
			if(manager.CanVotePaper(paperID, userID)) {
				manager.InsertPaperUpvote(paperID, userID);
				manager.UpdatePaperWeight(paperID);
				Executions.sendRedirect("paper.zul");
			}
			else
				Messagebox.show("You can only upvote a paper one time!!");
		}
		else
		{
			EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
				public void onEvent(ClickEvent event) {
					Executions.sendRedirect("login.zul");
				}
			};
			Messagebox.show("Please login to upvote a paper!!", "", new Messagebox.Button[] {
					Messagebox.Button.OK}, Messagebox.INFORMATION, clickListener);
		}
	}

	@Listen("onClick = #dnVotes")
	public void downVote() {
		// add a new entry to the papers votes table
		// update the paper's downvotes count
		int userID;
		DBManager manager = new DBManager();
		userID = manager.GetID(user);
		if(userID != -1) {
			if(manager.CanVotePaper(paperID, userID)) {
				manager.InsertPaperDownvote(paperID, userID);
				manager.UpdatePaperWeight(paperID);
				Executions.sendRedirect("paper.zul");
			}
			else
				Messagebox.show("You can only downvote a paper one time!!");
		}
		else {
			EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
				public void onEvent(ClickEvent event) {
					Executions.sendRedirect("login.zul");
				}
			};
			Messagebox.show("Please login to downvote a paper!!", "", new Messagebox.Button[] {
					Messagebox.Button.OK}, Messagebox.INFORMATION, clickListener);
		}
	}

	@Listen("onClick = #reviewLink")
	public void viewReview() {
		Executions.sendRedirect("viewreviews.zul");
	}

	@Listen("onClick = #addReviewLink")
	public void addReview() {
		if(SessionManager.GetUser() != null)
			Executions.sendRedirect("addreview.zul");
		else{
			EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
				public void onEvent(ClickEvent event) {
					Executions.sendRedirect("login.zul");
				}
			};
			Messagebox.show("Please login to submit a review!!", "", new Messagebox.Button[] {
					Messagebox.Button.OK}, Messagebox.INFORMATION, clickListener);
		}
	}

	public void SetTitle() {
		DBManager manager = new DBManager();
		paperTitle.setValue("Title: " + manager.GetPaperTitle(paperID));
	}

	public void SetAuthor() {
		int author;
		String username;
		DBManager manager = new DBManager();
		author = manager.GetPaperAuthor(paperID);
		username = manager.GetUsername(author);
		byAuthor.setValue("Author: " + manager.GetUsername(author));
	}

	public void SetDescription() {
		DBManager manager = new DBManager();
		description.setValue("Description: " + manager.GetPaperDescription(paperID));
	}

	public void SetUpvotes() {
		DBManager manager = new DBManager();
		upVotes.setLabel("" + manager.GetPaperUpvotes(paperID) + "▲ up");
	}

	public void SetDownvotes() {
		DBManager manager = new DBManager();
		dnVotes.setLabel(manager.GetPaperDownvotes(paperID) + "▼ dn");
	}

	public void doAfterCompose(Grid comp) {
		try {
			super.doAfterCompose(comp);
			SetTitle();
			SetAuthor();
			SetDescription();
			SetUpvotes();
			SetDownvotes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //wire variables and event listners
		//do whatever you want (you could access wired variables here)
	}
}
