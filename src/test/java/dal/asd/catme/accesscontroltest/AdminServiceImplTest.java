package dal.asd.catme.accesscontroltest;

import dal.asd.catme.accesscontrol.AdminServiceImpl;
import dal.asd.catme.accesscontrol.IAdminDao;
import dal.asd.catme.accesscontrol.IListUserDao;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.courses.Course;
import dal.asd.catme.courses.IListCourseDao;
import dal.asd.catme.coursestest.POJOMock;
import dal.asd.catme.exception.CatmeException;
import dal.asd.catme.util.CatmeUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminServiceImplTest
{

    IAdminDao adminDaoMock = new AdminDaoMock();

    @Test
    public void deleteCourseTest()
    {
        AdminServiceImpl adminService = new AdminServiceImpl(adminDaoMock);

        assertEquals(1,adminService.deleteCourse(POJOMock.getCourses().get(0).getCourseId()));
    }

    @Test
    public void addCourseTest()
    {
        AdminServiceImpl adminService = new AdminServiceImpl(adminDaoMock);

        assertEquals(1,adminService.addCourse(POJOMock.getCourses().get(0)));
    }

    @Test
    public void addInstructorToCourseTest()
    {
        AdminServiceImpl adminService = new AdminServiceImpl(adminDaoMock);

        assertEquals(1,adminService.addInstructorToCourse(POJOMock.getUsers().get(0).getBannerId(),POJOMock.getCourses().get(0).getCourseId()));
    }
}
