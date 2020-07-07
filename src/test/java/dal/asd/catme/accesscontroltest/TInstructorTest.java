package dal.asd.catme.accesscontroltest;

import dal.asd.catme.accesscontrol.TInstructor;
import dal.asd.catme.courses.Course;
import dal.asd.catme.coursestest.POJOMock;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TInstructorTest
{
    @Test
    public void getCoursesHandledTest()
    {
        TInstructor tInstructor = new TInstructor();

        List<Course> list = POJOMock.getCourses();
        tInstructor.setCoursesHandled(list);
        assertEquals(tInstructor.getCoursesHandled(), list);
    }

    @Test
    public void setCoursesHandledTest()
    {
        TInstructor tInstructor = new TInstructor();

        List<Course> list = POJOMock.getCourses();
        tInstructor.setCoursesHandled(list);
        assertEquals(tInstructor.getCoursesHandled(), list);
    }
}
