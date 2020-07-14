package dal.asd.catme.coursestest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.POJOMock;
import dal.asd.catme.accesscontrol.*;
import dal.asd.catme.courses.*;
import dal.asd.catme.exception.EnrollmentException;
import dal.asd.catme.util.CatmeUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EnrollStudentServiceImplTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    ICourseAbstractFactory courseAbstractFactory  = baseAbstractFactory.makeCourseAbstractFactory();
    IAccessControlModelAbstractFactory accessControlModelAbstractFactory = baseAbstractFactory.makeAccessControlModelAbstractFactory();
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

            List<IRole> roles = POJOMock.getUsers().get(0).getRole();
            assertEquals(String.valueOf(CatmeUtil.STUDENT_ROLE_ID), roles.get(roles.size() - 1).getRoleId());
        } catch (EnrollmentException e)
        {
            e.printStackTrace();
            fail();
        }

        try
        {
            IUser user = accessControlModelAbstractFactory.makeUser();
            user.setEmail("a@b");
            user.setBannerId("B00454545");
            user.setFirstName("B");
            user.setLastName("A");
            service.assignStudentRole(user);

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