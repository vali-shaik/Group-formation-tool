package dal.asd.catme.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDatabaseAccess
{
    Connection getConnection() throws SQLException;

    ResultSet executeQuery(String query);

    int executeUpdate(String query);
}
