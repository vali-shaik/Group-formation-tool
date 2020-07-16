package dal.asd.catme.courses;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.database.IDatabaseAbstractFactory;
import dal.asd.catme.database.IDatabaseAccess;

import java.sql.PreparedStatement;

import static dal.asd.catme.util.DBQueriesUtil.STUDENT_ENROLL_QUERY;

public class StudentDaoImpl implements IStudentDao
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryImpl.instance();
    IDatabaseAbstractFactory databaseAbstractFactory = baseAbstractFactory.makeDatabaseAbstractFactory();

    @Override
    public boolean enroll(User s, Course c)
    {
        IDatabaseAccess databaseAccess = databaseAbstractFactory.makeDatabaseAccess();
        try
        {
            PreparedStatement stmt = databaseAccess.getPreparedStatement(STUDENT_ENROLL_QUERY);
            stmt.setString(1, s.getBannerId());
            stmt.setString(2, c.getCourseId());

            stmt.executeUpdate();
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        } finally
        {
            databaseAccess.cleanUp();
        }
    }
}
