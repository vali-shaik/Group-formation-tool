package dal.asd.catme.courses;

import dal.asd.catme.accesscontrol.Student;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.exception.EnrollmentException;

import java.util.ArrayList;

public interface IEnrollStudentService
{
    public boolean enrollStudentsIntoCourse(ArrayList<Student> students, Course c);

    public void enrollStudent(Student s, Course c) throws EnrollmentException;

    public void assignStudentRole(User student) throws EnrollmentException;

//    public void createNewStudent(User student) throws EnrollmentException;
//
//    public void sendCredentials(User student, Course c) throws MailException;
}
