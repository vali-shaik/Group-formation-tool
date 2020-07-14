package dal.asd.catme.coursestest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.POJOMock;
import dal.asd.catme.accesscontrol.IUser;
import dal.asd.catme.courses.*;
import dal.asd.catme.accesscontrol.Role;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.exception.EnrollmentException;
import dal.asd.catme.util.CatmeUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EnrollStudentServiceImplTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    ICourseAbstractFactory courseAbstractFactory  = baseAbstractFactory.makeCourseAbstractFactory();
    ICourse c = POJOMock.getCourses().get(0);
    IUser s = POJOMock.getUsers().get(0);

    @Test
    public void enrollStudentsIntoCourseTest()
    {
        IEnrollStudentService service = courseAbstractFactory.makeEnrollmentService();

        assertTrue(service.enrollStudentsIntoCourse(POJOMock.getUsers(),c));
    }

    @Test
    void assignStudentRoleTest()
    {
        IEnrollStudentService service = courseAbstractFactory.makeEnrollmentService();

        try
        {
            service.assignStudentRole(POJOMock.getUsers().get(0));

            List<Role> roles = POJOMock.getUsers().get(0).getRole();
            assertEquals(String.valueOf(CatmeUtil.STUDENT_ROLE_ID), roles.get(roles.size() - 1).getRoleId());
        } catch (EnrollmentException e)
        {
            e.printStackTrace();
            fail();
        }

        try
        {
            service.assignStudentRole(new User("B00454545", "A", "B", "a@b"));

            fail();
        } catch (EnrollmentException e)
        {
        }
    }

    @Test
    void enrollStudentTest()
    {
        IEnrollStudentService service = courseAbstractFactory.makeEnrollmentService();
        try
        {
            service.enrollStudent(s, c);
        } catch (EnrollmentException e)
        {
            e.printStackTrace();
            fail();
        }
    }
}