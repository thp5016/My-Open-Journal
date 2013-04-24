package Test;

import java.util.List;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.*;

public class Home {
	
	public static void GoHome()
	{
		Executions.sendRedirect("index.zul");
	}
	
	// Displays the top 10 papers
	public static void DisplayResult(Grid myGrid, List<Data> data)
	{
	    Rows rows = new Rows();
	    rows.setParent(myGrid);
	    for(Data d : data)
	    {
	    	final int id = d.GetID();
	        Label title= new Label(d.GetTitle());
	        title.addEventListener("onClick", new EventListener()
	        {
				@Override
				public void onEvent(Event event) throws Exception {
					DBManager manager = new DBManager();
					Executions.sendRedirect("paper.zul");
				}
	        }
	        );
	        Label upvotes = new Label(d.GetUpvotes());
	        Label downvotes = new Label(d.GetDownvotes());

	        Row row = new Row();    

	        title.setParent(row);
	        upvotes.setParent(row);
	        downvotes.setParent(row);

	        row.setParent(rows);
	    }
	}

}
