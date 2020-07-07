package dal.asd.catme.accesscontroltest;

import dal.asd.catme.accesscontrol.Instructor;
import dal.asd.catme.courses.Course;
import dal.asd.catme.coursestest.POJOMock;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InstructorTest
{
    @Test
    public void getCoursesHandledTest()
    {
        Instructor instructor = new Instructor();

        List<Course> list = POJOMock.getCourses();
        instructor.setCoursesHandled(list);
        assertEquals(instructor.getCoursesHandled(), list);
    }

    @Test
    public void setCoursesHandledTest()
    {
        Instructor instructor = new Instructor();

        List<Course> list = POJOMock.getCourses();
        instructor.setCoursesHandled(list);

        assertEquals(instructor.getCoursesHandled(), list);
    }
}
