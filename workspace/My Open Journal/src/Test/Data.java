package Test;

public class Data 
{
	private String upvotes;
	private String downvotes;
	private String title;
	private int id;
	
	public Data(String paperTitle, String numUpvotes, String numDownvotes, int paperID) {
		upvotes = numUpvotes;
		downvotes = numDownvotes;
		title = paperTitle;
		id = paperID;
	}
	
	public String GetUpvotes() {
		return upvotes;
	}
	
	public int GetID() {
		return id;
	}
	
	public String GetDownvotes() {
		return downvotes;
		
	}
	
	public String GetTitle() {
		return title;
	}
	
	public void SetTitle(String newTitle) {
		title = newTitle;
	}
	
	public void SetDownvotes(String votes) {
		downvotes = votes;
	}
	
	public void SetUpvotes(String votes) {
		upvotes = votes;
	}
}
