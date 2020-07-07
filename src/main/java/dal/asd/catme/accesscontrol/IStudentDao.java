package dal.asd.catme.accesscontrol;

import dal.asd.catme.courses.Course;

import java.sql.Connection;

public interface IStudentDao
{
    public boolean enroll(Student s, Course c, Connection con);
}
