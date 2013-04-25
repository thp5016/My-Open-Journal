package Test;

import java.util.List;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;

public class ViewComments extends SelectorComposer<Grid> {

	@Wire
	Grid commentTable;
	
	int paperID = SessionManager.GetPaper();
	
	// Displays the top 10 papers
	public void DisplayResult(Grid myGrid, List<CommentData> data)
	{
	    Rows rows = new Rows();
	    rows.setParent(myGrid);

	    for(CommentData d : data)
	    {
	        Label comment = new Label(d.GetComment());
	        Label author = new Label(d.GetAuthor());
	        Label upvotes = new Label("" + d.GetUpvotes());
	        Label downvotes = new Label("" + d.GetDownvotes());
	        System.out.println("downvotes: " + d.GetDownvotes());
	        Row row = new Row();    

	        comment.setParent(row);
	        author.setParent(row);
	        upvotes.setParent(row);
	        downvotes.setParent(row);

	        row.setParent(rows);
	    }
	}

	@Listen("onClick = #backToReview")
	public void backToReview()
	{
		Executions.sendRedirect("review.zul");
	}
	
	@Listen("onClick = #backToPaper")
	public void goBack() {
		Executions.sendRedirect("paper.zul");
	}
	
	@Listen("onClick = #addReviewLink")
	public void addReview() {
		Executions.sendRedirect("addreview.zul");
	}
	
	@Listen("onClick = #addComment")
	public void addComment() {
		Executions.sendRedirect("addcomment.zul");
	}
	
	@Listen("onClick = #backToAllRevs") 
	public void allReviews() {
		Executions.sendRedirect("viewreviews.zul");
	}
	
	public void doAfterCompose(Grid comp) {
		DBManager manager = new DBManager();
		try {
			super.doAfterCompose(comp);
			int reviewID = SessionManager.GetReviewID();
			DisplayResult(commentTable, manager.GetComments(reviewID));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //wire variables and event listners
		//do whatever you want (you could access wired variables here)
	}
}
