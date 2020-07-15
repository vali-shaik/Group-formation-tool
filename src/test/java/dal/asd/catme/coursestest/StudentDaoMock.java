package dal.asd.catme.coursestest;

import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.courses.Course;
import dal.asd.catme.courses.IStudentDao;

import java.sql.Connection;

public class StudentDaoMock implements IStudentDao
{
    @Override
    public boolean enroll(User s, Course c, Connection con)
    {
        return !s.getBannerId().equals("") && !c.getCourseId().equals("");
    }
}
