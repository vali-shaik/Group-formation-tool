package dal.asd.catme.coursestest;

import dal.asd.catme.courses.IRoleDao;
import dal.asd.catme.courses.IStudentDao;
import dal.asd.catme.accesscontrol.Role;
import dal.asd.catme.accesscontrol.Student;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.accesscontroltest.StudentDaoMock;
import dal.asd.catme.courses.Course;
import dal.asd.catme.courses.EnrollStudentService;
import dal.asd.catme.exception.EnrollmentException;
import dal.asd.catme.util.CatmeUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EnrollStudentServiceTest
{

    ArrayList<User> users = POJOMock.getUsers();
    Course c = POJOMock.getCourses().get(0);
    Student s = POJOMock.getStudents().get(0);

    IRoleDao roleDao = new RoleDaoMock(users, c);
    IStudentDao studentDao = new StudentDaoMock(s);

    @Test
    public void enrollStudentsIntoCourseTest()
    {
        EnrollStudentService service = new EnrollStudentService(roleDao, studentDao);

        assertTrue(service.enrollStudentsIntoCourse(POJOMock.getStudents(),c));
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
            assertEquals(s.enrolledCourses.get(0), c);
        } catch (EnrollmentException e)
        {
            e.printStackTrace();
            fail();
        }
    }
}