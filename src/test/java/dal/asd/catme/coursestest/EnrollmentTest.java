package dal.asd.catme.coursestest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.courses.ICourseModelAbstractFactory;
import dal.asd.catme.courses.IEnrollment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnrollmentTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    ICourseModelAbstractFactory courseModelAbstractFactory = baseAbstractFactory.makeCourseModelAbstractFactory();

    @Test
    public void getBannerIdTest()
    {
        IEnrollment e = courseModelAbstractFactory.makeEnrollment();

        e.setBannerId("B00121212");
        assertEquals(e.getBannerId(), "B00121212");
    }

    @Test
    public void setBannerIdTest()
    {
        IEnrollment e = courseModelAbstractFactory.makeEnrollment();

        e.setBannerId("B00121212");
        assertEquals(e.getBannerId(), "B00121212");
    }

    @Test
    public void getCourseIdTest()
    {
        IEnrollment e = courseModelAbstractFactory.makeEnrollment();

        e.setCourseId("5308");
        assertEquals(e.getCourseId(), "5308");
    }

    @Test
    public void setCourseIdTest()
    {
        IEnrollment e = courseModelAbstractFactory.makeEnrollment();

        e.setCourseId("5308");
        assertEquals(e.getCourseId(), "5308");
    }
}
