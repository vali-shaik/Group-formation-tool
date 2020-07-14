package dal.asd.catme.coursestest;

import dal.asd.catme.accesscontrol.IUser;
import dal.asd.catme.courses.ICourse;
import dal.asd.catme.courses.IStudentDao;

import java.sql.Connection;

public class StudentDaoMock implements IStudentDao
{
    @Override
    public boolean enroll(IUser s, ICourse c, Connection con)
    {
        return !s.getBannerId().equals("") && !c.getCourseId().equals("");
    }
}
