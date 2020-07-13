package dal.asd.catme.courses;

import dal.asd.catme.accesscontrol.IUser;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.exception.EnrollmentException;

import java.util.ArrayList;

public interface IEnrollStudentService
{
    boolean enrollStudentsIntoCourse(ArrayList<IUser> students, ICourse c);

    void enrollStudent(IUser s, ICourse c) throws EnrollmentException;

    void assignStudentRole(IUser student) throws EnrollmentException;
}
