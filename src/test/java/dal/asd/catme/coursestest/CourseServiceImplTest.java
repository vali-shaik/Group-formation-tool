package dal.asd.catme.coursestest;

import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.courses.Course;
import dal.asd.catme.courses.CourseServiceImpl;
import dal.asd.catme.courses.ICourseDao;
import dal.asd.catme.exception.CatmeException;
import dal.asd.catme.util.CatmeUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class CourseServiceImplTest
{
    ICourseDao courseDaoMock = new CourseDaoMock(POJOMock.getCourses());

    @Test
    public void getCoursesTest() throws CatmeException
    {
        List<Course> listOfCourses = POJOMock.getCourses();
        CourseServiceImpl courseServiceImpl = new CourseServiceImpl(courseDaoMock);
        courseServiceImpl.getCourses(CatmeUtil.GUEST_ROLE);
        assertEquals(listOfCourses.get(0).getCourseId(), courseServiceImpl.getCourses(CatmeUtil.GUEST_ROLE).get(0).getCourseId());

    }

    @Test
    public void getCoursesSizeTest() throws CatmeException
    {
        List<Course> listOfCourses = POJOMock.getCourses();
        CourseServiceImpl courseServiceImpl = new CourseServiceImpl(courseDaoMock);
        courseServiceImpl.getCourses(CatmeUtil.GUEST_ROLE);
        assertEquals(listOfCourses.size(), courseServiceImpl.getCourses(CatmeUtil.GUEST_ROLE).size());

    }

    @Test
    public void getCoursesNullCheckTest() throws CatmeException
    {
        CourseServiceImpl courseServiceImpl = new CourseServiceImpl(courseDaoMock);
        assertEquals(null, courseServiceImpl.getCourses(CatmeUtil.ADMIN_ROLE));

    }

    @Test
    public void displayCourseByIdTest() throws CatmeException
    {
        CourseServiceImpl courseServiceImpl = new CourseServiceImpl(courseDaoMock);
        assertEquals("DWDM", courseServiceImpl.displayCourseById("5308").getCourseName());
        assertNotEquals("Adv Cloud", courseServiceImpl.displayCourseById("5308").getCourseName());
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
