package dal.asd.catme.coursestest;

import dal.asd.catme.POJOMock;
import dal.asd.catme.accesscontrol.IUser;
import dal.asd.catme.courses.*;
import dal.asd.catme.accesscontrol.Role;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.exception.EnrollmentException;
import dal.asd.catme.util.CatmeUtil;
import org.junit.Rule;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EnrollStudentServiceTest
{

    ArrayList<IUser> users = POJOMock.getUsers();
    ICourse c = POJOMock.getCourses().get(0);
    IUser s = POJOMock.getUsers().get(0);

    IRoleDao roleDao = new RoleDaoMock(users, c);
    IStudentDao studentDao = new StudentDaoMock(s);

    @Test
    public void enrollStudentsIntoCourseTest()
    {
        EnrollStudentService service = new EnrollStudentService(roleDao, studentDao);

        assertTrue(service.enrollStudentsIntoCourse(POJOMock.getUsers(),c));
    }

    @Test
    void assignStudentRoleTest()
    {
        EnrollStudentService service = new EnrollStudentService(roleDao, studentDao);

        try
        {
            service.assignStudentRole(users.get(0));

            List<Role> roles = users.get(0).getRole();
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
        EnrollStudentService service = new EnrollStudentService(roleDao, studentDao);
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