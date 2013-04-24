package Test;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.impl.InputElement;




public class AddComment extends GenericForwardComposer {
  @Wire
  Textbox typedComment;

  @Listen("onClick = #submitReview")
  public void submitComment() {
    int id;           // user id commenting
    int reviewId;     // review id comment is being posted in response to
    String user;      // username posting the comment
    String comment;   // the comment
    DBManager manager = new DBManager();
  
    user = SessionManager.GetUser();
    id = manager.GetID(user);
    comment = manager.GetTypedReview(user);
    manager.InsertComment(reviewId, id, comment);
  }

  @Listen("onClick = #backToReview")
  public void goBack() {
    Executions.sendRedirect("index.zul");
  }
  
}
