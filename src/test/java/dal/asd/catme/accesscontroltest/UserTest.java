package dal.asd.catme.accesscontroltest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.accesscontrol.IAccessControlModelAbstractFactory;
import dal.asd.catme.accesscontrol.IUser;
import dal.asd.catme.accesscontrol.Role;
import dal.asd.catme.accesscontrol.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    IAccessControlModelAbstractFactory accessControlModelAbstractFactory = baseAbstractFactory.makeAccessControlModelAbstractFactory();

    @Test
    public void setBannerIdTest()
    {
        IUser u = accessControlModelAbstractFactory.makeUser();

        u.setBannerId("B00121212");
        assertEquals(u.getBannerId(),"B00121212");

    }

    @Test
    public void getBannerIdTest()
    {
        IUser u = accessControlModelAbstractFactory.makeUser();

        u.setBannerId("B00121212");
        assertEquals(u.getBannerId(),"B00121212");

    }

    @Test
    public void setFirstNameTest()
    {
        IUser u = accessControlModelAbstractFactory.makeUser();

        u.setFirstName("First");
        assertEquals(u.getFirstName(),"First");
    }

    @Test
    public void getFirstNameTest()
    {
        IUser u = accessControlModelAbstractFactory.makeUser();

        u.setFirstName("First");
        assertEquals(u.getFirstName(),"First");
    }

    @Test
    public void setLastNameTest()
    {
        IUser u = accessControlModelAbstractFactory.makeUser();

        u.setLastName("Last");
        assertEquals(u.getLastName(),"Last");
    }

    @Test
    public void getLastNameTest()
    {
        IUser u = accessControlModelAbstractFactory.makeUser();

        u.setLastName("Last");
        assertEquals(u.getLastName(),"Last");
    }

    @Test
    public void setPasswordTest()
    {
        IUser u = accessControlModelAbstractFactory.makeUser();

        u.setPassword("Password");
        assertEquals(u.getPassword(),"Password");
    }

    @Test
    public void getPasswordTest()
    {
        IUser u = accessControlModelAbstractFactory.makeUser();

        u.setPassword("Password");
        assertEquals(u.getPassword(),"Password");
    }

    @Test
    public void setEmailTest()
    {
        IUser u = accessControlModelAbstractFactory.makeUser();

        u.setEmail("abc@xyz.com");
        assertEquals(u.getEmail(),"abc@xyz.com");
    }

    @Test
    public void getEmailTest()
    {
        IUser u = accessControlModelAbstractFactory.makeUser();

        u.setEmail("abc@xyz.com");
        assertEquals(u.getEmail(),"abc@xyz.com");
    }

    @Test
    public void setRoleTest()
    {
        IUser u = accessControlModelAbstractFactory.makeUser();
        List<Role> roles = listRole();

        u.setRole(roles);
        assertEquals(u.getRole(),roles);
    }

    @Test
    public void getRoleTest()
    {
        IUser u = accessControlModelAbstractFactory.makeUser();
        List<Role> roles = listRole();

        u.setRole(roles);
        assertEquals(u.getRole(),roles);
    }


    private List<Role> listRole()
    {
        List<Role> list = new ArrayList<>();

        Role r = new Role();
        r.setRoleName("Admin");
        r.setRoleName("1");

        list.add(r);
        return list;
    }
}
