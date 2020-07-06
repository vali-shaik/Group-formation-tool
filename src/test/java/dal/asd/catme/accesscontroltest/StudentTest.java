package dal.asd.catme.accesscontroltest;

import dal.asd.catme.accesscontrol.Student;
import dal.asd.catme.courses.Course;
import dal.asd.catme.coursestest.POJOMock;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest
{
    @Test
    public void getEnrolledCoursesTest()
    {
        Student s = new Student();

        List<Course> list = POJOMock.getCourses();
        s.setEnrolledCourses(list);
        assertEquals(s.getEnrolledCourses(), list);
    }

    @Test
    public void setEnrolledCoursesTest()
    {
        Student s = new Student();

        List<Course> list = POJOMock.getCourses();
        s.setEnrolledCourses(list);
        assertEquals(s.getEnrolledCourses(), list);
    }
}
