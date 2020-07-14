package dal.asd.catme.coursestest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.POJOMock;
import dal.asd.catme.courses.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoleServiceImplTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    ICourseAbstractFactory courseAbstractFactory  = baseAbstractFactory.makeCourseAbstractFactory();

    @Test
    public void assignTATest()
    {
        IRoleService roleService = courseAbstractFactory.makeRoleService();

        assertEquals("",roleService.assignTa(new Enrollment("B00835717","5308")));
    }
}
