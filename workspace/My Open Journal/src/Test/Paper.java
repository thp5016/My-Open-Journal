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

public class Paper extends GenericForwardComposer 
{
  //need to implement a funciton in session manager
  int paperID = SessionManager.GetPaper(paper);
  String user = SessionManager.GetUser(user);

  @Listen("onClick = #downloadLink")
  public void viewPaper()
  {
    // redirect to the pdf id specified by the page url
    // Executions.sendRedirect(/* pdf url */);
    String path;
    
    DBManager manager = new DBManager();
    path = manager.GetPaperPath(int paperID);
    
    Executions.sendRedirect(path);
  }
  @Listen("onClick = #upVotes")
  public void upVote()
  {
    // add a new entry to the paper votes table
    // update the paper's upvotes count
    int userID;
    DBManager manager = new DBManager();
    userID = manager.GetPaperID(paper);
    
    if(manager.CanVote(paperID, userID))
    {
      manager.InsertUpvote(paperID, userID);
    }
    
  }
  @Listen("onClick = #downVotes")
  public void downVote()
  {
    // add a new entry to the papers votes table
    // update the paper's downvotes count
    int userID;
    DBManager manager = new DBManager();
    userID = manager.GetPaperID(paper);
    
    if(manager.CanVote(paperID, userID))
    {
      manager.InsertDownvote(paperID, userID);
    }
    
  }
  @Listen("onClick = #reviewLink")
  public void viewReview()
  {
    // redirect to reviews page
  }
  @Listen("onClick = #addReviewLink")
  public void addReview()
  {
    // redirect to add review page
  }
}
