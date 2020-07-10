package dal.asd.catme.accesscontroltest;

import dal.asd.catme.accesscontrol.IListUserDao;
import dal.asd.catme.accesscontrol.ListDetailsServiceImpl;
import dal.asd.catme.courses.IListCourseDao;
import dal.asd.catme.coursestest.POJOMock;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListDetailsServiceImplTest
{
    IListUserDao listUserDao = new ListDetailsDaoMock();
    IListCourseDao listCourseDao = new ListDetailsDaoMock();
    @Test
    public void getUsersTest()
    {
        ListDetailsServiceImpl listDetailsService = new ListDetailsServiceImpl(listUserDao,listCourseDao);

        assertNotNull(listDetailsService.getUsers(POJOMock.getCourses().get(0)));
    }

    @Test
    public void getAllCoursesTest()
    {
        ListDetailsServiceImpl listDetailsService = new ListDetailsServiceImpl(listUserDao,listCourseDao);

        assertNotNull(listDetailsService.getAllCourses());
    }
}
