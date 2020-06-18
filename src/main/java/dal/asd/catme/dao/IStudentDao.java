package dal.asd.catme.dao;

import java.sql.Connection;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.Student;

public interface IStudentDao
{
    public boolean enroll(Student s, Course c, Connection con);
}
