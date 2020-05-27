package dal.asd.catme.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseAccess {
	private Connection connection;
	@Value("${spring.database.test.url}")
	String url;
	@Value("${spring.database.test.user}")
	String user;
	@Value("${spring.database.test.password}")
	String password;
	public Connection getConnection()  {
		try {
			//Class.forName("com.mysql.jdbc.driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
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
}
