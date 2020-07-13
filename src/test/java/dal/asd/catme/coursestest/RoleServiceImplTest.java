package dal.asd.catme.coursestest;

import dal.asd.catme.POJOMock;
import dal.asd.catme.courses.IRoleDao;
import dal.asd.catme.courses.Enrollment;
import dal.asd.catme.courses.RoleServiceImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoleServiceImplTest
{
    IRoleDao roleDao = new RoleDaoMock(POJOMock.getUsers(),POJOMock.getCourses().get(0));

    @Test
    public void assignTATest()
    {
        RoleServiceImpl roleService = new RoleServiceImpl(roleDao);

        assertEquals("",roleService.assignTa(new Enrollment("B00835717","5308")));
    }
}
