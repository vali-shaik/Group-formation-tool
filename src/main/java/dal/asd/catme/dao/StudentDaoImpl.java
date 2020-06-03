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

@Component
public class StudentDaoImpl implements IStudentDao
{
    @Autowired
    DatabaseAccess db;

    Connection con;

    @Override
    public boolean enroll(Student s, Course c)
    {
        try {
            con = db.getConnection();
            String enrollQuery = "INSERT INTO Enrollment " +
                    "(BannerId, CourseId) " +
                    "VALUES('"+s.getBannerId()+"', '"+c.getCourseId()+"');";

            db.executeUpdate(enrollQuery);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) { e.printStackTrace(); }
            }
        }

    }

    @Override
    public boolean isStudent(User u)
    {
        try {
            con = db.getConnection();
            String isStudentQuery = "SELECT * " +
                    "FROM UserRole WHERE BannerId='"+u.getBannerId()+"' and RoleId=2; ";


            ResultSet rs = db.executeQuery(isStudentQuery);

            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }
}
