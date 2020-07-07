package dal.asd.catme.accesscontroltest;

import dal.asd.catme.accesscontrol.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoleTest
{
    @Test
    public void getRoleIdTest()
    {
        Role role = new Role();

        role.setRoleId("001");
        assertEquals(role.getRoleId(),"001");
    }

    @Test
    public void setRoleIdTest()
    {
        Role role = new Role();

        role.setRoleId("001");
        assertEquals(role.getRoleId(),"001");
    }

    @Test
    public void getRoleNameTest()
    {
        Role role = new Role();

        role.setRoleName("Admin");
        assertEquals(role.getRoleName(),"Admin");
    }

    @Test
    public void setRoleNameTest()
    {
        Role role = new Role();

        role.setRoleName("Admin");
        assertEquals(role.getRoleName(),"Admin");
    }
}
