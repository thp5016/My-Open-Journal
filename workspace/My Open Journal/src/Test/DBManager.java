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
			PreparedStatement stmt = connection.GetConnection().prepareStatement("INSERT INTO Users (Username, First_Name, Last_Name,  Password) VALUES (?, ?, ?, ?)");
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
		JOptionPane.showMessageDialog(null, user +  "has been successfully registered!!");
		return true;
	}
	
	public List GetTopPapers()
	{
		String query;
		ResultSet rs;

		connection = new DBConnection("10.2.65.20", "myopenjournal", "sa", "umaxistheman");
    	query = "select top 10 * from Papers order by Upvotes desc;";
		try {
			PreparedStatement stmt = connection.GetConnection().prepareStatement(query);
			rs = stmt.executeQuery();
			stmt.close();
	    	connection.Disconnect();
	    	/*List<Data> rowValues = new ArrayList<Data>();
	    	while (rs.next()) {
	    		Data data = new Data(rs.getString(1), rs.getString(2), rs.getString(3));
	    	    rowValues.add(data);
	    	}
	    	rs.close();
	    	return rowValues;*/
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
    	query = "select Passoword from Users where Username= ?;";
		try {
			PreparedStatement stmt = connection.GetConnection().prepareStatement(query);
			stmt.setString(1, user);
			rs = stmt.executeQuery();
			stmt.close();
	    	connection.Disconnect();
	    	if(pass.equals(rs))
	    	{
				JOptionPane.showMessageDialog(null, user + " has successfully logged in!!");
		    	rs.close();
	    		return true;
	    	}
	    	else
	    	{
				JOptionPane.showMessageDialog(null, "Invalid password!!");
		    	rs.close();
	    		return false;
	    	}
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Invalid Username!!");
			return false;
		}
	}
	
	// checks to see if the specified username has been registered/exists in database
	public boolean IsValidUser(String user) {
		String query;
		ResultSet rs;
		
		connection = new DBConnection("10.2.65.20", "myopenjournal", "sa", "umaxistheman");
    	query = "select * from Users where Username= ?;";
		try {
			PreparedStatement stmt = connection.GetConnection().prepareStatement(query);
			stmt.setString(1, user);
			rs = stmt.executeQuery();
	    	connection.Disconnect();
	    	System.out.println(rs);
		} 
		catch (SQLException e) {
			System.out.println("Invalid Username!!");
			return false;
		}

		return true;
	}
}