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

public class ViewReviews extends SelectorComposer<Grid> {

	@Wire
	Grid reviewTable;
	
	int paperID = SessionManager.GetPaper();
	
	// Displays the top 10 papers
	public void DisplayResult(Grid myGrid, List<ReviewData> data)
	{
	    Rows rows = new Rows();
	    rows.setParent(myGrid);

	    for(ReviewData d : data)
	    {
		    final int id = d.GetID();
	        Label author = new Label(d.GetAuthor());
	        author.addEventListener("onClick", new EventListener<Event>()
	        {
				@Override
				public void onEvent(Event event) throws Exception {
					SessionManager.SetReviewID(id);
					Executions.sendRedirect("review.zul");
				}
	        }
	        );
	        Label upvotes = new Label("" + d.GetUpvotes());
	        Label downvotes = new Label("" + d.GetDownvotes());

	        Row row = new Row();    

	        author.setParent(row);
	        upvotes.setParent(row);
	        downvotes.setParent(row);

	        row.setParent(rows);
	    }
	}

	@Listen("onClick = #backToPaper")
	public void goBack() {
		Executions.sendRedirect("paper.zul");
	}
	
	@Listen("onClick = #addReviewLink")
	public void addReview() {
		Executions.sendRedirect("addreview.zul");
	}
	
	public void doAfterCompose(Grid comp) {
		DBManager manager = new DBManager();
		try {
			super.doAfterCompose(comp);
			DisplayResult(reviewTable, manager.GetReviews(paperID));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //wire variables and event listners
		//do whatever you want (you could access wired variables here)
	}
}
