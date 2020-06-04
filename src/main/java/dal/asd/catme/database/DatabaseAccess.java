package dal.asd.catme.database;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Statement;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DatabaseAccess implements DataSource
{
	
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private int result;

	@Value("${spring.datasource.driver-class-name}")
	String driver;
	
	@Value("${spring.datasource.url}")
	String url;
	
	@Value("${spring.datasource.username}")
	String user;
	
	@Value("${spring.datasource.password}")
	String password;

	
	
	public Connection getConnection() throws SQLException
	{
		try 
		{
			//Class.forName("com.mysql.jdbc.driver");
			Class.forName(driver);
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			connection = DriverManager.getConnection(url, user, password);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return connection;
	
	}
//	public ResultSet executeQuery(String query) { try { statement =
//			  connection.createStatement(); resultSet = statement.executeQuery(query); }
//			  catch (SQLException e) { // TODO Auto-generated catch block
//			  e.printStackTrace(); }
//
//			  return resultSet; }



	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.driver");
//			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
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

	public int executeUpdate(String query) {
		try {
			statement = connection.createStatement();
			result = statement.executeUpdate(query);
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			return result;
	}

}
