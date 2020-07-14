package dal.asd.catme.coursestest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.courses.Course;
import dal.asd.catme.courses.ICourse;
import dal.asd.catme.courses.ICourseAbstractFactory;
import dal.asd.catme.courses.ICourseModelAbstractFactory;
import org.junit.jupiter.api.Test;
import dal.asd.catme.POJOMock;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    ICourseModelAbstractFactory courseModelAbstractFactory  = baseAbstractFactory.makeCourseModelAbstractFactory();
    @Test
    public void getCourseIdTest()
    {
        ICourse c = courseModelAbstractFactory.makeCourse();

        c.setCourseId("5307");
        assertEquals(c.getCourseId(),"5307");
    }

    @Test
    public void setCourseIdTest()
    {
        ICourse c = courseModelAbstractFactory.makeCourse();

        c.setCourseId("5307");
        assertEquals(c.getCourseId(),"5307");
    }

    @Test
    public void getCourseNameTest()
    {
        ICourse c = courseModelAbstractFactory.makeCourse();

        c.setCourseName("ABCD");
        assertEquals(c.getCourseName(),"ABCD");
    }

    @Test
    public void setCourseNameTest()
    {
        ICourse c = courseModelAbstractFactory.makeCourse();

        c.setCourseName("ABCD");
        assertEquals(c.getCourseName(),"ABCD");
    }
}
