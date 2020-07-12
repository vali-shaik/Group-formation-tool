package dal.asd.catme.coursestest;

import dal.asd.catme.accesscontrol.Instructor;
import dal.asd.catme.accesscontrol.Student;
import dal.asd.catme.courses.Course;
import org.junit.jupiter.api.Test;
import dal.asd.catme.coursestest.POJOMock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest
{
    @Test
    public void getCourseIdTest()
    {
        Course c = new Course();

        c.setCourseId("5307");
        assertEquals(c.getCourseId(),"5307");
    }

    @Test
    public void setCourseIdTest()
    {
        Course c = new Course();

        c.setCourseId("5307");
        assertEquals(c.getCourseId(),"5307");
    }

    @Test
    public void getCourseNameTest()
    {
        Course c = new Course();

        c.setCourseName("ABCD");
        assertEquals(c.getCourseName(),"ABCD");
    }

    @Test
    public void setCourseNameTest()
    {
        Course c = new Course();

        c.setCourseName("ABCD");
        assertEquals(c.getCourseName(),"ABCD");
    }

    @Test
    public void getStudentsTest()
    {
        Course c = new Course();

        List<Student> list = POJOMock.getStudents();

        c.setStudents(list);
        assertEquals(c.getStudents(),list);
    }

    @Test
    public void setStudentsTest()
    {
        Course c = new Course();

        List<Student> list = POJOMock.getStudents();

        c.setStudents(list);
        assertEquals(c.getStudents(),list);
    }



    @Test
    public void getInstructorTest()
    {
        Course c = new Course();

        List<Instructor> list = POJOMock.getInstructors();

        c.setInstructors(list);
        assertEquals(c.getInstructors(),list);
    }

    @Test
    public void setInstructorTest()
    {
        Course c = new Course();

        List<Instructor> list = POJOMock.getInstructors();

        c.setInstructors(list);
        assertEquals(c.getInstructors(),list);
    }
}
