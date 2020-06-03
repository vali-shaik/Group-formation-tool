package dal.asd.catme.dao;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.Student;
import dal.asd.catme.beans.User;

public interface IStudentDao
{
    public boolean enroll(Student s, Course c);
    public boolean isStudent(User s);
}
