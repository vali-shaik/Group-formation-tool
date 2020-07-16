package dal.asd.catme.courses;

import dal.asd.catme.accesscontrol.User;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static dal.asd.catme.util.DBQueriesUtil.STUDENT_ENROLL_QUERY;

public class StudentDaoImpl implements IStudentDao
{
	private static final Logger log = LoggerFactory.getLogger(StudentDaoImpl.class);
    @Override
    public boolean enroll(User s, Course c, Connection con)
    {
    	log.info("Enrolling the student");
        try
        {
            PreparedStatement stmt = con.prepareStatement(STUDENT_ENROLL_QUERY);
            stmt.setString(1, s.getBannerId());
            stmt.setString(2, c.getCourseId());

            stmt.executeUpdate();
            return true;
        } catch (Exception e)
        {
        	log.error("Failed to enroll the student as the details are insufficient");
            e.printStackTrace();
            return false;
        }
    }
}
