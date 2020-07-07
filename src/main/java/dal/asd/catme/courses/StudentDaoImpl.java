package dal.asd.catme.courses;

import static dal.asd.catme.util.DBQueriesUtil.STUDENT_ENROLL_QUERY;

import java.sql.Connection;
import java.sql.PreparedStatement;

import dal.asd.catme.accesscontrol.IStudentDao;
import dal.asd.catme.accesscontrol.Student;

public class StudentDaoImpl implements IStudentDao
{
    @Override
    public boolean enroll(Student s, Course c, Connection con)
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
