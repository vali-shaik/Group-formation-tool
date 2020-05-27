package dal.asd.catme.dao;


import java.sql.*;

public class DatabaseAccess {

	private Connection connection;
	String url="jdbc:mysql://db-5308.cs.dal.ca:3306/user=", user="CSCI5308_16_DEVINT_USER",password="CSCI5308_16_DEVINT_16175";
	private Statement statement;
	ResultSet resultSet;
	
	public Connection connectToDatabase() {
		try {
			System.out.println("inside connect method");
			Class.forName("com.mysql.jdbc.driver");
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public ResultSet executeQuery(String query) {
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultSet;
	}
	
	
	
}
