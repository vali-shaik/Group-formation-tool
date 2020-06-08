package dal.asd.catme.dao;

import java.sql.Connection;
import java.sql.Statement;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.Student;
import dal.asd.catme.database.DatabaseAccess;

public class StudentDaoImpl implements IStudentDao
{
    DatabaseAccess db;

    Connection con;

    @Override
    public boolean enroll(Student s, Course c, Connection con)
    {
        try {
            String enrollQuery = "INSERT INTO Enrollment " +
                    "(BannerId, CourseId) " +
                    "VALUES('"+s.getBannerId()+"', '"+c.getCourseId()+"');";

            Statement stmt = con.createStatement();
            stmt.executeUpdate(enrollQuery);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
