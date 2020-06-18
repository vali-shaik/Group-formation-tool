package dal.asd.catme.service;

import java.util.ArrayList;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.Student;
import dal.asd.catme.beans.User;
import dal.asd.catme.exception.EnrollmentException;

public interface IEnrollStudentService
{
    public boolean enrollStudentsIntoCourse(ArrayList<Student> students, Course c);

    public void enrollStudent(Student s, Course c) throws EnrollmentException;

    public void assignStudentRole(User student) throws EnrollmentException;

//    public void createNewStudent(User student) throws EnrollmentException;
//
//    public void sendCredentials(User student, Course c) throws MailException;
}
