package dal.asd.catme.dao;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.Student;
import dal.asd.catme.dao.IStudentDao;

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
