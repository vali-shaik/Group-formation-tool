package dal.asd.catme.coursestest;

import dal.asd.catme.POJOMock;
import dal.asd.catme.accesscontrol.IUser;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.courses.Enrollment;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class EnrollTaServiceTest
{
    Connection con;


    @Test
    void enrollTa()
    {
        RoleDaoMock roleDaoMock = new RoleDaoMock(formUsers(), POJOMock.getCourses());
        try
        {
            assertNull(roleDaoMock.assignTa(new Enrollment("B00835717", "5306"), con));

        } catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }
    }


    public ArrayList<IUser> formUsers()
    {
        List<IUser> listOfUsers = new ArrayList<>();
        IUser user = new User();
        user.setBannerId("B00835717");
        listOfUsers.add(user);
        IUser user1 = new User();
        user1.setBannerId("B00835718");
        listOfUsers.add(user1);

        return (ArrayList<IUser>) listOfUsers;
    }
}