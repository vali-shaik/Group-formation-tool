package dal.asd.catme.accesscontroltest;

import dal.asd.catme.accesscontrol.IStudentDao;
import dal.asd.catme.accesscontrol.Student;
import dal.asd.catme.courses.Course;

import java.sql.Connection;

public class StudentDaoMock implements IStudentDao
{
    Student s;

    public StudentDaoMock(Student s)
    {
        this.s = s;
    }

    @Override
    public boolean enroll(Student s, Course c, Connection con)
    {
        s.enrolledCourses.add(c);
        return true;
    }
}
