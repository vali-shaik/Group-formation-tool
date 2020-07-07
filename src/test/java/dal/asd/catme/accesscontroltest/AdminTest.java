package dal.asd.catme.accesscontroltest;

import dal.asd.catme.accesscontrol.Admin;
import dal.asd.catme.accesscontrol.Instructor;
import dal.asd.catme.courses.Course;
import dal.asd.catme.coursestest.POJOMock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AdminTest
{
    @Test
    public void getOverallCoursesTest()
    {
        Admin admin = new Admin();

        List<Course> list = POJOMock.getCourses();
        admin.setOverallCourses(list);
        assertEquals(admin.getOverallCourses(), list);
    }

    @Test
    public void setOverallCoursesTest()
    {
        Admin admin = new Admin();

        List<Course> list = POJOMock.getCourses();
        admin.setOverallCourses(list);
        assertEquals(admin.getOverallCourses(), list);
    }

    @Test
    public void getCourseInstructorTest()
    {
        Admin admin = new Admin();
        Map<Course, List<Instructor>> m = listCourseInstructor();

        admin.setCourseIntrutor(m);
        assertEquals(admin.getCourseIntrutor(),m);
    }

    @Test
    public void setCourseInstructorTest()
    {
        Admin admin = new Admin();
        Map<Course, List<Instructor>> m = listCourseInstructor();

        admin.setCourseIntrutor(m);
        assertEquals(admin.getCourseIntrutor(),m);
    }

    private Map<Course, List<Instructor>> listCourseInstructor()
    {
        Map<Course, List<Instructor>> map = new HashMap<>();
        List<Instructor> instructors = new ArrayList<>();

        Instructor instructor = new Instructor();
        instructor.setBannerId("B00121212");
        instructor.setFirstName("First");
        instructor.setLastName("Last");
        instructors.add(instructor);

        Course c = POJOMock.getCourses().get(0);

        map.put(c,instructors);

        return map;
    }
}
