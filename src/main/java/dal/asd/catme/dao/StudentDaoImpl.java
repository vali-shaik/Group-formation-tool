package dal.asd.catme.dao;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.Student;
import dal.asd.catme.beans.User;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.util.CatmeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
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
