package dal.asd.catme.database;

import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Logger;

@Configuration
public class DatabaseAccess implements DataSource,IDatabaseAccess
{

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private int result;

    private static final String url = System.getenv("DB_URL");
    private static final String username = System.getenv("DB_USERNAME");
    private static final String password = System.getenv("DB_PASSWORD");

    public Connection getConnection() throws SQLException
    {
        connection = DriverManager.getConnection(url, username, password);

        return connection;

    }

    @Override
    public PrintWriter getLogWriter() throws SQLException
    {
        return null;
    }


    @Override
    public void setLogWriter(PrintWriter out) throws SQLException
    {

    }


    @Override
    public void setLoginTimeout(int seconds) throws SQLException
    {

    }


    @Override
    public int getLoginTimeout() throws SQLException
    {
        return 0;
    }


    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException
    {
        return null;
    }


    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException
    {
        return null;
    }


    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException
    {
        return false;
    }


    @Override
    public Connection getConnection(String username, String password) throws SQLException
    {

        return null;

    }


    public ResultSet executeQuery(String query)
    {
        try
        {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return resultSet;
    }

    public int executeUpdate(String query)
    {
        try
        {
            statement = connection.createStatement();
            result = statement.executeUpdate(query);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }

}
