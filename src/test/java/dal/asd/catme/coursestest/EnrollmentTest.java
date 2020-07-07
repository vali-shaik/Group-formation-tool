package dal.asd.catme.coursestest;

import dal.asd.catme.courses.Enrollment;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnrollmentTest
{
    @Test
    public void getBannerIdTest()
    {
        Enrollment e = new Enrollment();

        e.setBannerId("B00121212");
        assertEquals(e.getBannerId(),"B00121212");
    }

    @Test
    public void setBannerIdTest()
    {
        Enrollment e = new Enrollment();

        e.setBannerId("B00121212");
        assertEquals(e.getBannerId(),"B00121212");
    }

    @Test
    public void getCourseIdTest()
    {
        Enrollment e = new Enrollment();

        e.setCourseId("5308");
        assertEquals(e.getCourseId(),"5308");
    }

    @Test
    public void setCourseIdTest()
    {
        Enrollment e = new Enrollment();

        e.setCourseId("5308");
        assertEquals(e.getCourseId(),"5308");
    }
}
