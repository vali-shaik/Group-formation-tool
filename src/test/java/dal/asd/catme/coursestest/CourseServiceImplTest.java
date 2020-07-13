package dal.asd.catme.coursestest;

import dal.asd.catme.POJOMock;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.courses.Course;
import dal.asd.catme.courses.CourseServiceImpl;
import dal.asd.catme.courses.ICourseDao;
import dal.asd.catme.exception.CatmeException;
import dal.asd.catme.util.CatmeUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CourseServiceImplTest
{
    ICourseDao courseDaoMock = new CourseDaoMock(POJOMock.getCourses());

    @Test
    public void getCoursesTest() throws CatmeException
    {
        CourseServiceImpl courseServiceImpl = new CourseServiceImpl(courseDaoMock);
        assertNotNull(courseServiceImpl.getCourses(CatmeUtil.GUEST_ROLE));
    }

    @Test
    public void displayCourseByIdTest() throws CatmeException
    {
        CourseServiceImpl courseServiceImpl = new CourseServiceImpl(courseDaoMock);
        assertEquals("DWDM", courseServiceImpl.displayCourseById("5308").getCourseName());
        assertNotEquals("Adv Cloud", courseServiceImpl.displayCourseById("5308").getCourseName());
    }

    @Test
    public void getEnrolledStudentsTest() throws CatmeException
    {
        CourseServiceImpl courseServiceImpl = new CourseServiceImpl(courseDaoMock);
        assertNotNull(courseServiceImpl.getEnrolledStudents(POJOMock.getCourses().get(0).getCourseId()));
    }

    @Test
    public void displayCourseByIdNotFoundCheckTest() throws CatmeException
    {
        CourseServiceImpl courseServiceImpl = new CourseServiceImpl(courseDaoMock);
        assertEquals(null, courseServiceImpl.displayCourseById("8988").getCourseName());
    }

    @Test
    public void findRoleByCourseTest() throws CatmeException
    {
        User user = new User();
        user.setBannerId("B00835822");
        CourseServiceImpl courseServiceImpl = new CourseServiceImpl(courseDaoMock);
        assertEquals(CatmeUtil.STUDENT_ROLE, courseServiceImpl.findRoleByCourse(user, "5409"));
        assertEquals(CatmeUtil.TA_ROLE, courseServiceImpl.findRoleByCourse(user, "5308"));
        assertEquals(CatmeUtil.GUEST_ROLE, courseServiceImpl.findRoleByCourse(user, "5306"));
        assertNotEquals(CatmeUtil.ADMIN_ROLE, courseServiceImpl.findRoleByCourse(user, "5306"));
    }
}
