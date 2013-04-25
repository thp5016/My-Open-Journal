package Test;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Messagebox.ClickEvent;

public class Review extends SelectorComposer<Grid> {
	
	@Wire
	Label reviewTitle;
	
	@Wire
	Label byAuthor;
	
	@Wire
	Toolbarbutton upVotes;
	
	@Wire
	Toolbarbutton dnVotes;
	
	@Wire
	Textbox text;
	
	int paperID = SessionManager.GetPaper();
	int reviewID = SessionManager.GetReviewID();
	String user = SessionManager.GetUser();

	
	@Listen("onClick = #backToPaper")
	public void goBack() {
		Executions.sendRedirect("paper.zul");
	}
	
	@Listen("onClick = #addComment")
	public void addComment() {
		if(SessionManager.GetUser() == null)
		{
			EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
				public void onEvent(ClickEvent event) {
					Executions.sendRedirect("login.zul");
				}
			};
			Messagebox.show("Please login to add a comment!!", "", new Messagebox.Button[] {
					Messagebox.Button.OK}, Messagebox.INFORMATION, clickListener);
		}
		else
			Executions.sendRedirect("addcomment.zul");
	}
	
	@Listen("onClick = #toComments")
	public void viewComments() {
		Executions.sendRedirect("viewcomments.zul");
	}
	
	@Listen("onClick = #upVotes")
	public void upVote() {
		// add a new entry to the paper votes table
		// update the paper's upvotes count
		int userID;
		DBManager manager = new DBManager();
		userID = manager.GetID(user);
		if(userID != -1) {
			if(manager.CanVoteReview(reviewID, userID)) {
				manager.InsertReviewUpvote(reviewID, userID);
				Executions.sendRedirect("review.zul");
			}
			else
				Messagebox.show("You can only upvote a review one time!!");
		}
		else
		{
			EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
				public void onEvent(ClickEvent event) {
					Executions.sendRedirect("login.zul");
				}
			};
			Messagebox.show("Please login to upvote a review!!", "", new Messagebox.Button[] {
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
			if(manager.CanVoteReview(reviewID, userID)) {
				manager.InsertReviewDownvote(reviewID, userID);
				Executions.sendRedirect("review.zul");
			}
			else
				Messagebox.show("You can only downvote a review one time!!");
		}
		else {
			EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
				public void onEvent(ClickEvent event) {
					Executions.sendRedirect("login.zul");
				}
			};
			Messagebox.show("Please login to downvote a review!!", "", new Messagebox.Button[] {
					Messagebox.Button.OK}, Messagebox.INFORMATION, clickListener);
		}
	}
	
	public void doAfterCompose(Grid comp) {
		DBManager manager = new DBManager();
		try {
			super.doAfterCompose(comp);
			reviewTitle.setValue("Re: " + manager.GetPaperTitle(paperID));
			byAuthor.setValue("by " + manager.GetReviewAuthor(reviewID));
			text.setText(manager.GetText(reviewID));
			upVotes.setLabel(manager.GetReviewUpvotes(reviewID) + "▲ up");
			dnVotes.setLabel(manager.GetReviewDownvotes(reviewID) + "▼ dn");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //wire variables and event listners
		//do whatever you want (you could access wired variables here)
	}
}
