package dal.asd.catme.accesscontroltest;

import dal.asd.catme.accesscontrol.Role;
import dal.asd.catme.accesscontrol.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest
{
    @Test
    public void setBannerIdTest()
    {
        User u = new User();

        u.setBannerId("B00121212");
        assertEquals(u.getBannerId(),"B00121212");

    }

    @Test
    public void getBannerIdTest()
    {
        User u = new User();

        u.setBannerId("B00121212");
        assertEquals(u.getBannerId(),"B00121212");

    }

    @Test
    public void setFirstNameTest()
    {
        User u = new User();

        u.setFirstName("First");
        assertEquals(u.getFirstName(),"First");
    }

    @Test
    public void getFirstNameTest()
    {
        User u = new User();

        u.setFirstName("First");
        assertEquals(u.getFirstName(),"First");
    }

    @Test
    public void setLastNameTest()
    {
        User u = new User();

        u.setLastName("Last");
        assertEquals(u.getLastName(),"Last");
    }

    @Test
    public void getLastNameTest()
    {
        User u = new User();

        u.setLastName("Last");
        assertEquals(u.getLastName(),"Last");
    }

    @Test
    public void setPasswordTest()
    {
        User u = new User();

        u.setPassword("Password");
        assertEquals(u.getPassword(),"Password");
    }

    @Test
    public void getPasswordTest()
    {
        User u = new User();

        u.setPassword("Password");
        assertEquals(u.getPassword(),"Password");
    }

    @Test
    public void setEmailTest()
    {
        User u = new User();

        u.setEmail("abc@xyz.com");
        assertEquals(u.getEmail(),"abc@xyz.com");
    }

    @Test
    public void getEmailTest()
    {
        User u = new User();

        u.setEmail("abc@xyz.com");
        assertEquals(u.getEmail(),"abc@xyz.com");
    }

    @Test
    public void setRoleTest()
    {
        User u = new User();
        List<Role> roles = listRole();

        u.setRole(roles);
        assertEquals(u.getRole(),roles);
    }

    @Test
    public void getRoleTest()
    {
        User u = new User();
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
