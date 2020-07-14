package dal.asd.catme.coursestest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.POJOMock;
import dal.asd.catme.accesscontrol.IUser;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.courses.*;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class EnrollTaServiceTest
{
    Connection con;

    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    ICourseAbstractFactory courseAbstractFactory  = baseAbstractFactory.makeCourseAbstractFactory();
    ICourseModelAbstractFactory courseModelAbstractFactory = baseAbstractFactory.makeCourseModelAbstractFactory();
    @Test
    void enrollTa()
    {
        IRoleDao roleDaoMock = courseAbstractFactory.makeRoleDao();
        try
        {
            IEnrollment enrollment = courseModelAbstractFactory.makeEnrollment();
            enrollment.setBannerId("B00835717");
            enrollment.setCourseId("5306");
            assertNotNull(roleDaoMock.assignTa(enrollment, con));

        } catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }
    }
}
