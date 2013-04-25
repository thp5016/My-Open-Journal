package Test;

public class ReviewData 
{
	private String upvotes;
	private String downvotes;
	private String author;
	private int id;
	
	public ReviewData(String authorName, String numUpvotes, String numDownvotes, int reviewID) {
		upvotes = numUpvotes;
		downvotes = numDownvotes;
		author = authorName;
		id = reviewID;
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
	
	public String GetAuthor() {
		return author;
	}
	
	public void SetAuthor(String newAuthor) {
		author = newAuthor;
	}
	
	public void SetDownvotes(String votes) {
		downvotes = votes;
	}
	
	public void SetUpvotes(String votes) {
		upvotes = votes;
	}
}
