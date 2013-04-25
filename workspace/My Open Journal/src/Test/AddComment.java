package Test;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Messagebox.ClickEvent;
import org.zkoss.zul.impl.InputElement;




public class AddComment extends SelectorComposer<Grid> {
  @Wire
  Textbox typedComment;

  @Listen("onClick = #submitComment")
  public void submitComment() {
    int id;           // user id commenting
    int reviewId;     // review id comment is being posted in response to
    String user;      // username posting the comment
    String comment;   // the comment
    DBManager manager = new DBManager();
   
    reviewId = SessionManager.GetReviewID();
    user = SessionManager.GetUser();
    id = manager.GetID(user);
    comment = typedComment.getText();
    manager.InsertComment(reviewId, id, comment);
	EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
		public void onEvent(ClickEvent event) {
			Executions.sendRedirect("index.zul");
		}
	};
	Messagebox.show("Your comment has been saved!!", "", new Messagebox.Button[] {
			Messagebox.Button.OK}, Messagebox.INFORMATION, clickListener);
  }

  @Listen("onClick = #backToReview")
  public void goBack() {
    Executions.sendRedirect("review.zul");
  }
  
}
