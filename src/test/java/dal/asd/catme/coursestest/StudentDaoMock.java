package dal.asd.catme.coursestest;

import dal.asd.catme.accesscontrol.IUser;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.courses.ICourse;
import dal.asd.catme.courses.IStudentDao;
import dal.asd.catme.courses.Course;

import java.sql.Connection;

public class StudentDaoMock implements IStudentDao
{
    @Override
    public boolean enroll(IUser s, ICourse c, Connection con)
    {
        if(s.getBannerId().equals("")||c.getCourseId().equals(""))
        {
            return false;
        }
        return true;
    }
}
