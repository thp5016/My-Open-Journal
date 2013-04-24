package Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBManager {

	private DBConnection connection;
	
	// Creates a new entry in the User database with the specified values
	public boolean InsertUser(String user, String firstName, String lastName, String pass, String email, String admin, String date) {
		String query;
		
		connection = new DBConnection("10.2.65.20", "myopenjournal", "sa", "umaxistheman");
    	query = "INSERT INTO Users (Username, First_Name, Last_Name,  Password, Email, Register_Date) VALUES (?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement stmt = connection.GetConnection().prepareStatement(query);
			stmt.setString(1, user);
			stmt.setString(2, firstName);
			stmt.setString(3, lastName);
			stmt.setString(4, pass);
			stmt.setString(5, email);
			stmt.setString(6, date);
			stmt.executeUpdate();
			stmt.close();
	    	connection.Disconnect();
		} 
		catch (SQLException e) {
			System.out.println("Failure to insert user: " + e.getMessage());
			return false;
		}
		return true;
	}
	
	public boolean ChangeProfile(String user, String password, String firstName, String lastName)
	{
		String query;
		connection = new DBConnection("10.2.65.20", "myopenjournal", "sa", "umaxistheman");
    	query = "update Users set Password = ?, First_Name = ?, Last_Name = ? where Username = ?;";
    	try {
			PreparedStatement stmt = connection.GetConnection().prepareStatement(query);
			stmt.setString(1, password);
			stmt.setString(2, firstName);
			stmt.setString(3, lastName);
			stmt.setString(4, user);
			stmt.executeUpdate();
			stmt.close();
	    	connection.Disconnect();
	    	return true;
		} 
		catch (SQLException e) {
			System.out.println("Failure to update profile: " + e.getMessage());
			return false;
		}

	}
	
	public int GetID(String user)
	{
		int id;
		String query;
		ResultSet rs;
		
		connection = new DBConnection("10.2.65.20", "myopenjournal", "sa", "umaxistheman");
    	query = "select User_ID from Users where Username= ?;";
		try {
			PreparedStatement stmt = connection.GetConnection().prepareStatement(query);
			stmt.setString(1, user);
			rs = stmt.executeQuery();
			rs.next();
			id = rs.getInt(1);
	    	rs.close();
			stmt.close();
	    	connection.Disconnect();
    		return id;
		} 
		catch (SQLException e) {
	    	connection.Disconnect();
			return -1;
		}
	}
	
	public String GetUsername(int id)
	{
		String user;
		String query;
		ResultSet rs;
		connection = new DBConnection("10.2.65.20", "myopenjournal", "sa", "umaxistheman");
    	query = "select Username from Users where User_ID = ?;";
    	try {
			PreparedStatement stmt = connection.GetConnection().prepareStatement(query);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			rs.next();
			user = rs.getString(1);
			rs.close();
			stmt.close();
	    	connection.Disconnect();
	    	return user;
		} 
		catch (SQLException e) {
			System.out.println("Failure to Get Top Papers: " + e.getMessage());
			return null;
		}
	}
	
	public String GetPaperDescription(int id)
	{
		String description;
		String query;
		ResultSet rs;
		connection = new DBConnection("10.2.65.20", "myopenjournal", "sa", "umaxistheman");
    	query = "select Description from Papers where Paper_ID = ?;";
    	try {
			PreparedStatement stmt = connection.GetConnection().prepareStatement(query);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			rs.next();
			description = rs.getString(1);
			rs.close();
			stmt.close();
	    	connection.Disconnect();
	    	return description;
		} 
		catch (SQLException e) {
			System.out.println("Failure to Get Top Papers: " + e.getMessage());
			return null;
		}
	}
	
	public boolean InsertPaper(int authorID, String title, String path, String description, String date)
	{
		String query;
		
		connection = new DBConnection("10.2.65.20", "myopenjournal", "sa", "umaxistheman");
    	query = "INSERT INTO Papers (Author_ID, Title, File_Path, Description, Upload_Date, Weight, Upvotes, Downvotes, Reports) VALUES (?, ?, ?, ?, ?, 0, 0, 0, 0)";
		try {
			PreparedStatement stmt = connection.GetConnection().prepareStatement(query);
			stmt.setInt(1, authorID);
			stmt.setString(2, title);
			stmt.setString(3, path);
			stmt.setString(4, description);
			stmt.setString(5, date);
			stmt.executeUpdate();
			stmt.close();
	    	connection.Disconnect();
	    	return true;
		} 
		catch (SQLException e) {
			System.out.println("Failure to insert user: " + e.getMessage());
			return false;
		}
	}
	
	public int GetPaperAuthor(int id)
	{
		int author;
		String query;
		ResultSet rs;
		connection = new DBConnection("10.2.65.20", "myopenjournal", "sa", "umaxistheman");
    	query = "select Author_ID from Papers where Paper_ID = ?;";
    	try {
			PreparedStatement stmt = connection.GetConnection().prepareStatement(query);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			rs.next();
			author = rs.getInt(1);
			rs.close();
			stmt.close();
	    	connection.Disconnect();
	    	return author;
		} 
		catch (SQLException e) {
			System.out.println("Failure to Get Top Papers: " + e.getMessage());
			return -1;
		}
	}
	
	public String GetPaperTitle(int id)
	{
		String title;
		String query;
		ResultSet rs;
		connection = new DBConnection("10.2.65.20", "myopenjournal", "sa", "umaxistheman");
    	query = "select Title from Papers where Paper_ID = ?;";
    	try {
			PreparedStatement stmt = connection.GetConnection().prepareStatement(query);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			rs.next();
			title = rs.getString(1);
			rs.close();
			stmt.close();
	    	connection.Disconnect();
	    	return title;
		} 
		catch (SQLException e) {
			System.out.println("Failure to Get Top Papers: " + e.getMessage());
			return null;
		}
	}
	
	public String GetPaperPath(int id)
	{
		String path;
		String query;
		ResultSet rs;
		
		connection = new DBConnection("10.2.65.20", "myopenjournal", "sa", "umaxistheman");
    	query = "select File_Path from Papers where Paper_ID = ?;";
    	try {
			PreparedStatement stmt = connection.GetConnection().prepareStatement(query);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			rs.next();
			path = rs.getString(1);
			rs.close();
			stmt.close();
	    	connection.Disconnect();
	    	return path;
		} 
		catch (SQLException e) {
			System.out.println("Failure to Get Top Papers: " + e.getMessage());
			return null;
		}
	}
	
	public List<Data> GetTopPapers()
	{
		String query;
		ResultSet rs;
		connection = new DBConnection("10.2.65.20", "myopenjournal", "sa", "umaxistheman");
    	query = "select top 10 * from Papers order by Upvotes desc;";
		try {
			PreparedStatement stmt = connection.GetConnection().prepareStatement(query);
			rs = stmt.executeQuery();
	    	List<Data> rowValues = new ArrayList<Data>();

	    	while (rs.next()) {
	    		Data data = new Data(rs.getString(4), rs.getString(9), rs.getString(10), rs.getInt(1));
	    	    rowValues.add(data);
	    	}

	    	rs.close();
			stmt.close();
	    	connection.Disconnect();
	    	return rowValues;
		} 
		catch (SQLException e) {
			System.out.println("Failure to Get Top Papers: " + e.getMessage());
		}
		return null;
	}
	
	public String GetFirstName(String user)
	{
		String name;
		String query;
		ResultSet rs;
		
		connection = new DBConnection("10.2.65.20", "myopenjournal", "sa", "umaxistheman");
    	query = "select First_Name from Users where Username= ?;";
		try {
			PreparedStatement stmt = connection.GetConnection().prepareStatement(query);
			stmt.setString(1, user);
			rs = stmt.executeQuery();
			rs.next();
			name = rs.getString(1);
	    	rs.close();
			stmt.close();
	    	connection.Disconnect();
    		return name;
		} 
		catch (SQLException e) {
	    	connection.Disconnect();
			return null;
		}
	}
		
	public String GetLastName(String user)
	{
		String name;
		String query;
		ResultSet rs;
		
		connection = new DBConnection("10.2.65.20", "myopenjournal", "sa", "umaxistheman");
    	query = "select Last_Name from Users where Username= ?;";
		try {
			PreparedStatement stmt = connection.GetConnection().prepareStatement(query);
			stmt.setString(1, user);
			rs = stmt.executeQuery();
			rs.next();
			name = rs.getString(1);
	    	rs.close();
			stmt.close();
	    	connection.Disconnect();
    		return name;
		} 
		catch (SQLException e) {
	    	connection.Disconnect();
			return null;
		}
	}
	
	public List<Data> GetNewPapers()
	{
		String query;
		ResultSet rs;
		connection = new DBConnection("10.2.65.20", "myopenjournal", "sa", "umaxistheman");
    	query = "select top 10 * from Papers order by Upload_Date desc;";
		try {
			PreparedStatement stmt = connection.GetConnection().prepareStatement(query);
			rs = stmt.executeQuery();
	    	List<Data> rowValues = new ArrayList<Data>();

	    	while (rs.next()) {
	    		Data data = new Data(rs.getString(4), rs.getString(9), rs.getString(10), rs.getInt(1));
	    	    rowValues.add(data);
	    	}

	    	rs.close();
			stmt.close();
	    	connection.Disconnect();
	    	return rowValues;
		} 
		catch (SQLException e) {
			System.out.println("Failure to Get Top Papers: " + e.getMessage());
		}
		return null;
	}
	
	public boolean IsValidPassword(String user, String pass){
		String query;
		ResultSet rs;
		String saltedAndHashedPass = SessionManager.saltAndHash(pass);
		
		connection = new DBConnection("10.2.65.20", "myopenjournal", "sa", "umaxistheman");
    	query = "select Password from Users where Username= ?;";
		try {
			PreparedStatement stmt = connection.GetConnection().prepareStatement(query);
			stmt.setString(1, user);

			rs = stmt.executeQuery();
			rs.next();
			if(saltedAndHashedPass.equals(rs.getString(1)))
	    	{
				System.out.println(user + " has successfully logged in!!");
		    	rs.close();
				stmt.close();
		    	connection.Disconnect();
	    		return true;
	    	}
	    	else
	    	{
				System.out.println("Invalid Password!!");
		    	rs.close();
				stmt.close();
		    	connection.Disconnect();
	    		return false;
	    	}
		} 
		catch (SQLException e) {
	    	connection.Disconnect();
			return false;
		}
	}
	
	// checks to see if the specified username has been registered/exists in database
	public boolean IsValidUser(String user) {
		String query;
		ResultSet rs;
		boolean isEmpty;
		
		connection = new DBConnection("10.2.65.20", "myopenjournal", "sa", "umaxistheman");
    	query = "select Username from Users where Username= ?";
		try {
			PreparedStatement stmt = connection.GetConnection().prepareStatement(query);
			stmt.setString(1, user);
			rs = stmt.executeQuery();
			if(!rs.next())
				isEmpty = false;
			else
				isEmpty = true;
	    	rs.close();
	    	stmt.close();
	    	connection.Disconnect();
	    	return isEmpty;
		} 
		catch (SQLException e) {
			System.out.println("Invalid Username!!");
			return false;
		}
	}
	
	// This function inserts a new comment into the database given a review_ID the comment is
	// posted on, user_ID that is submitting the comment, and a String comment
	public boolean InsertComment(int reviewID, int userID, String comment) {
		String query;
		
		connection = new DBConnection("10.2.65.20", "myopenjournal", "sa", "umaxistheman");
    	query = "INSERT INTO Comments (Review_ID, Commenter_ID, Text) VALUES (?, ?, ?)";
		try {
			PreparedStatement stmt = connection.GetConnection().prepareStatement(query);
			stmt.setInt(1, reviewID);
			stmt.setInt(2, userID);
			stmt.setString(3, comment);
			stmt.executeUpdate();
			stmt.close();
	    	connection.Disconnect();
	    	return true;
		} 
		catch (SQLException e) {
			System.out.println("Failure to insert user: " + e.getMessage());
			return false;
		}
	}
	
	// This function inserts a new review into the database given a paper_ID the review is
	// posted on, user_ID that is submitting the comment, and a String review
	public boolean InsertReview(int paperID, int userID, String review) {
		String query;
		
		connection = new DBConnection("10.2.65.20", "myopenjournal", "sa", "umaxistheman");
    	query = "INSERT INTO Comments (Paper_ID, Commenter_ID, Text) VALUES (?, ?, ?)";
		try {
			PreparedStatement stmt = connection.GetConnection().prepareStatement(query);
			stmt.setInt(1, paperID);
			stmt.setInt(2, userID);
			stmt.setString(3, review);
			stmt.executeUpdate();
			stmt.close();
	    	connection.Disconnect();
	    	return true;
		} 
		catch (SQLException e) {
			System.out.println("Failure to insert user: " + e.getMessage());
			return false;
		}
	}
	
	public int GetUpvotes(int id)
	{
		int upvotes;
		String query;
		ResultSet rs;
		connection = new DBConnection("10.2.65.20", "myopenjournal", "sa", "umaxistheman");
    	query = "select Upvotes from Papers where Paper_ID = ?;";
    	try {
			PreparedStatement stmt = connection.GetConnection().prepareStatement(query);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			rs.next();
			upvotes = rs.getInt(1);
			rs.close();
			stmt.close();
	    	connection.Disconnect();
	    	return upvotes;
		} 
		catch (SQLException e) {
			System.out.println("Failure to Get Top Papers: " + e.getMessage());
			return -1;
		}
	}
	
	public int GetDownvotes(int id)
	{
		int downvotes;
		String query;
		ResultSet rs;
		connection = new DBConnection("10.2.65.20", "myopenjournal", "sa", "umaxistheman");
    	query = "select Downvotes from Papers where Paper_ID = ?;";
    	try {
			PreparedStatement stmt = connection.GetConnection().prepareStatement(query);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			rs.next();
			downvotes = rs.getInt(1);
			rs.close();
			stmt.close();
	    	connection.Disconnect();
	    	return downvotes;
		} 
		catch (SQLException e) {
			System.out.println("Failure to Get Top Papers: " + e.getMessage());
			return -1;
		}
	}
	
	// This function inserts a upvote and updates the corresponding paper table
	public boolean InsertUpvote(int paperID, int userID) {
		String query1;
		String query2;
		
		connection = new DBConnection("10.2.65.20", "myopenjournal", "sa", "umaxistheman");
    		query1 = "INSERT INTO PaperVotes (Paper_ID, User_ID, Up_Down, Report) VALUES (?, ?, 1, 0)";
    		query2 = "UPDATE Papers SET Upvotes = Upvotes+1 WHERE Paper_ID = ?";
		try {
			PreparedStatement stmt = connection.GetConnection().prepareStatement(query1);
			stmt.setInt(1, paperID);
			stmt.setInt(2, userID);
			stmt.executeUpdate();
	    		stmt = connection.GetConnection().prepareStatement(query2);
			stmt.setInt(1, paperID);
			stmt.executeUpdate();
			stmt.close();
	    		connection.Disconnect();
	    		return true;
		}
		catch (SQLException e) {
			System.out.println("Failure to insert user: " + e.getMessage());
			return false;
		}
	}
	
	// This function inserts a downvote and updates the corresponding paper table
	public boolean InsertDownvote(int paperID, int userID) {
		String query1;
		String query2;
		
		connection = new DBConnection("10.2.65.20", "myopenjournal", "sa", "umaxistheman");
    		query1 = "INSERT INTO PaperVotes (Paper_ID, User_ID, Up_Down, Report) VALUES (?, ?, 0, 0)";
    		query2 = "UPDATE Papers SET Downvotes = Downvotes+1 WHERE Paper_ID = ?";
		try {
			PreparedStatement stmt = connection.GetConnection().prepareStatement(query1);
			stmt.setInt(1, paperID);
			stmt.setInt(2, userID);
			stmt.executeUpdate();
	    		stmt = connection.GetConnection().prepareStatement(query2);
			stmt.setInt(1, paperID);
			stmt.executeUpdate();
			stmt.close();
	    		connection.Disconnect();
	    		return true;
		}
		catch (SQLException e) {
			System.out.println("Failure to insert user: " + e.getMessage());
			return false;
		}
	}
	
	// checks to see if the specified username has been registered/exists in database
	public boolean CanVote(int paperID, int userID) {
		String query;
		ResultSet rs;
		boolean canVote;
		
		connection = new DBConnection("10.2.65.20", "myopenjournal", "sa", "umaxistheman");
    		query = "SELECT * FROM PaperVotes WHERE Paper_ID = ? AND User_ID = ?";
		try {
			PreparedStatement stmt = connection.GetConnection().prepareStatement(query);
			stmt.setInt(1, paperID);
			stmt.setInt(2, userID);
			rs = stmt.executeQuery();
			if(rs.next())
				canVote = false;
			else
				canVote = true;
	    		rs.close();
	    		stmt.close();
	    		connection.Disconnect();
	    		return canVote;
		} 
		catch (SQLException e) {
			System.out.println("Invalid Username!!");
			return false;
		}
	}
}
