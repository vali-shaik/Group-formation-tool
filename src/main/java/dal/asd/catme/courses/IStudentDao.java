package dal.asd.catme.courses;

import dal.asd.catme.accesscontrol.Student;

import java.sql.Connection;

public interface IStudentDao
{
    public boolean enroll(Student s, Course c, Connection con);
}
