package dal.asd.catme.accesscontroltest;

import dal.asd.catme.accesscontrol.AdminServiceImpl;
import dal.asd.catme.accesscontrol.IAdminDao;
import dal.asd.catme.POJOMock;
import org.junit.jupiter.api.Test;

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
