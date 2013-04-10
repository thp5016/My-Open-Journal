package Test;

public class Data 
{
	private String upvotes;
	private String downvotes;
	private String title;
	
	public Data(String paperTitle, String numUpvotes, String numDownvotes)
	{
		upvotes = numUpvotes;
		downvotes = numDownvotes;
		title = paperTitle;
	}
	
	public String GetUpvotes()
	{
		return upvotes;
	}
	
	public String GetDownvotes()
	{
		return downvotes;
		
	}
	
	public String GetTitle()
	{
		return title;
	}
	
	public void SetTitle(String newTitle)
	{
		title = newTitle;
	}
	
	public void SetDownvotes(String votes)
	{
		downvotes = votes;
	}
	
	public void SetUpvotes(String votes)
	{
		upvotes = votes;
	}
}
