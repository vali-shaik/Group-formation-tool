package dal.asd.catme.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDatabaseAccess
{
    public Connection getConnection() throws SQLException;

    public ResultSet executeQuery(String query);

    public int executeUpdate(String query);
}
