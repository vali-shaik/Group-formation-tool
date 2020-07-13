package dal.asd.catme.courses;

import dal.asd.catme.accesscontrol.IUser;
import dal.asd.catme.accesscontrol.User;

import static dal.asd.catme.util.DBQueriesUtil.STUDENT_ENROLL_QUERY;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class StudentDaoImpl implements IStudentDao
{
    @Override
    public boolean enroll(IUser s, ICourse c, Connection con)
    {
        try
        {
            PreparedStatement stmt = con.prepareStatement(STUDENT_ENROLL_QUERY);
            stmt.setString(1, s.getBannerId());
            stmt.setString(2, c.getCourseId());

            stmt.executeUpdate();
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
