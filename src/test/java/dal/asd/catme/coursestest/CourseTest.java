package dal.asd.catme.coursestest;

import dal.asd.catme.courses.Course;
import org.junit.jupiter.api.Test;
import dal.asd.catme.POJOMock;
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
}
