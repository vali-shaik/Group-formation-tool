package dal.asd.catme.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.Student;
import dal.asd.catme.database.DatabaseAccess;

import static dal.asd.catme.util.DBQueriesUtil.*;

public class StudentDaoImpl implements IStudentDao
{
    DatabaseAccess db;

    Connection con;

    @Override
    public boolean enroll(Student s, Course c, Connection con)
    {
        try {
            PreparedStatement stmt = con.prepareStatement(STUDENT_ENROLL_QUERY);
            stmt.setString(1,s.getBannerId());
            stmt.setString(2,c.getCourseId());

            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
