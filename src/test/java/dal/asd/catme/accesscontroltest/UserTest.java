package dal.asd.catme.accesscontroltest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.accesscontrol.IAccessControlModelAbstractFactory;
import dal.asd.catme.accesscontrol.IRole;
import dal.asd.catme.accesscontrol.IUser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    IAccessControlModelAbstractFactory accessControlModelAbstractFactory = baseAbstractFactory.makeAccessControlModelAbstractFactory();

    @Test
    public void setBannerIdTest()
    {
        IUser u = accessControlModelAbstractFactory.makeUser();

        u.setBannerId("B00121212");
        assertEquals(u.getBannerId(), "B00121212");

    }

    @Test
    public void getBannerIdTest()
    {
        IUser u = accessControlModelAbstractFactory.makeUser();

        u.setBannerId("B00121212");
        assertEquals(u.getBannerId(), "B00121212");

    }

    @Test
    public void setFirstNameTest()
    {
        IUser u = accessControlModelAbstractFactory.makeUser();

        u.setFirstName("First");
        assertEquals(u.getFirstName(), "First");
    }

    @Test
    public void getFirstNameTest()
    {
        IUser u = accessControlModelAbstractFactory.makeUser();

        u.setFirstName("First");
        assertEquals(u.getFirstName(), "First");
    }

    @Test
    public void setLastNameTest()
    {
        IUser u = accessControlModelAbstractFactory.makeUser();

        u.setLastName("Last");
        assertEquals(u.getLastName(), "Last");
    }

    @Test
    public void getLastNameTest()
    {
        IUser u = accessControlModelAbstractFactory.makeUser();

        u.setLastName("Last");
        assertEquals(u.getLastName(), "Last");
    }

    @Test
    public void setPasswordTest()
    {
        IUser u = accessControlModelAbstractFactory.makeUser();

        u.setPassword("Password");
        assertEquals(u.getPassword(), "Password");
    }

    @Test
    public void getPasswordTest()
    {
        IUser u = accessControlModelAbstractFactory.makeUser();

        u.setPassword("Password");
        assertEquals(u.getPassword(), "Password");
    }

    @Test
    public void setEmailTest()
    {
        IUser u = accessControlModelAbstractFactory.makeUser();

        u.setEmail("abc@xyz.com");
        assertEquals(u.getEmail(), "abc@xyz.com");
    }

    @Test
    public void getEmailTest()
    {
        IUser u = accessControlModelAbstractFactory.makeUser();

        u.setEmail("abc@xyz.com");
        assertEquals(u.getEmail(), "abc@xyz.com");
    }

    @Test
    public void setRoleTest()
    {
        IUser u = accessControlModelAbstractFactory.makeUser();
        List<IRole> roles = listRole();

        u.setRole(roles);
        assertEquals(u.getRole(), roles);
    }

    @Test
    public void getRoleTest()
    {
        IUser u = accessControlModelAbstractFactory.makeUser();
        List<IRole> roles = listRole();

        u.setRole(roles);
        assertEquals(u.getRole(), roles);
    }


    private List<IRole> listRole()
    {
        List<IRole> list = new ArrayList<>();

        IRole r = accessControlModelAbstractFactory.makeRole();
        r.setRoleName("Admin");
        r.setRoleName("1");

        list.add(r);
        return list;
    }
}
