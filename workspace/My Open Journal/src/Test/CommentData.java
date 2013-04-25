package Test;

public class CommentData 
{
	private int upvotes;
	private int downvotes;
	private String author;
	private int commentID;
	private int reviewID;
	private String comment;
	
	public CommentData(String newComment, String authorName, int numUpvotes, int numDownvotes, int idReview, int idComment) {
		upvotes = numUpvotes;
		downvotes = numDownvotes;
		author = authorName;
		reviewID = idReview;
		commentID = idComment;
		comment = newComment;
	}
	
	public String GetComment() {
		return comment;
	}
	
	public int GetUpvotes() {
		return upvotes;
	}
	
	public int GetReviewID() {
		return reviewID;
	}
	
	public int GetCommentID() {
		return commentID;
	}
	
	public int GetDownvotes() {
		return downvotes;
		
	}
	
	public String GetAuthor() {
		return author;
	}
	
	public void SetAuthor(String newAuthor) {
		author = newAuthor;
	}
	
	public void SetDownvotes(int votes) {
		downvotes = votes;
	}
	
	public void SetUpvotes(int votes) {
		upvotes = votes;
	}
}
