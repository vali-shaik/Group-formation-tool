package dal.asd.catme.courses;

import dal.asd.catme.accesscontrol.Student;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.exception.CatmeException;

import java.util.List;

public interface ICourseService
{
    public List<Course> getCourses(String role) throws CatmeException;

    public List<Course> getAllCourses();

    public Course displayCourseById(String courseId) throws CatmeException;

    public String findRoleByCourse(User user, String courseId) throws CatmeException;

    public List<Student> getEnrolledStudents(String courseId);
}
