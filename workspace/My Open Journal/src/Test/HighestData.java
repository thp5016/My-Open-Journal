package Test;

public class HighestData 
{
	private String upvotes;
	private String downvotes;
	private String title;
	private int id;
	private double paperWeight;
	
	public HighestData(String paperTitle, String numUpvotes, String numDownvotes, int paperID, double weight) {
		upvotes = numUpvotes;
		downvotes = numDownvotes;
		title = paperTitle;
		id = paperID;
		paperWeight = weight;
	}
	
	public double GetWeight() {
		return paperWeight;
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
