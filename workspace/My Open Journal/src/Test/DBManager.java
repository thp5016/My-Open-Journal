package Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;



public class DBManager {

	private DBConnection connection;
	
	// Creates a new entry in the User database with the specified values
	public boolean InsertUser(String user, String firstName, String lastName, String pass, String admin) {
		String query;
		
		connection = new DBConnection("10.2.65.20", "myopenjournal", "sa", "umaxistheman");
    	query = "INSERT INTO Users (Username, First_Name, Last_Name,  Password) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement stmt = connection.GetConnection().prepareStatement(query);
			stmt.setString(1, user);
			stmt.setString(2, firstName);
			stmt.setString(3, lastName);
			stmt.setString(4, pass);
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
	    		Data data = new Data(rs.getString(3), rs.getString(8), rs.getString(9));
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
		
		connection = new DBConnection("10.2.65.20", "myopenjournal", "sa", "umaxistheman");
    	query = "select Password from Users where Username= ?;";
		try {
			PreparedStatement stmt = connection.GetConnection().prepareStatement(query);
			stmt.setString(1, user);

			rs = stmt.executeQuery();
			rs.next();
			if(pass.equals(rs.getString(1)))
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
}