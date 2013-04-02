package Test;


import java.sql.*;


public class DBConnection {
	
	// Connection to the database
	private Connection connection;
	
	// Creates a new database connection using specified parameters
	public DBConnection(String server, String database, String user, String password) {
		Connect(server, database, user, password);
	}
	
	// Connects to the databse
	public void Connect(String server, String DBName, String user, String pass) {
		//String url = "jdbc:sqlserver://" + server + ";databaseName=" + DBName + ";user=" + user + ";password=" + pass + ";";
		try {
			//Loading the driver... 
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://10.2.65.20;database=myopenjournal;";
			connection = DriverManager.getConnection(url, user, pass);
        }
		catch( Exception e ) {
			System.out.println("Failure to load driver!!");
			return;
		} 
	}
	
	// Executes the specified query
	public void Execute(String query){
		Statement stmt;
		try {
			stmt = connection.createStatement();
			stmt.execute(query);
		} 
		catch (SQLException e) {
			System.out.println("failure to execute query!!");
		}
	}
	
	public Connection GetConnection()
	{
		return connection;
	}
	
	// Disconnects from the database
	public void Disconnect() {
		try {
			connection.close();
		} 
		catch (SQLException e) {
		}
	}
}
