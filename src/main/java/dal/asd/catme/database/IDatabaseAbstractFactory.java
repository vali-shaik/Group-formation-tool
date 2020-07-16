package dal.asd.catme.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDatabaseAbstractFactory
{
    IDatabaseAccess makeDatabaseAccess();
}
