package dal.asd.catme.courses;

import dal.asd.catme.accesscontrol.Student;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.exception.CatmeException;

import java.sql.Connection;
import java.util.List;

public interface ICourseDao
{

    public List<Course> getCourses(String role) throws CatmeException;

    public List<Course> getAllCourses();

    public Course displayCourseById(String courseId) throws CatmeException;

    public String findRoleByCourse(User user, String courseId) throws CatmeException;

    public List<Student> getRegisteredStudents(String courseId);

    public int checkCourseRegistration(String bannerId, String courseId, Connection con);

    public int checkCourseExists(String courseId, Connection con);

}
