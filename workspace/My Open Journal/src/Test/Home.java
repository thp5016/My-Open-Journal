package Test;

import java.util.List;

import org.zkoss.zul.*;

public class Home {
	
	public static void DisplayResult(Grid myGrid, List<Data> data)
	{
		System.out.println("Cookie: " + CookieManager.getCookie());
	    Rows rows = new Rows();
	    rows.setParent(myGrid);
	    for(Data d : data)
	    {
	        Label title= new Label(d.GetTitle());
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
