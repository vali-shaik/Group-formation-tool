package dal.asd.catme.accesscontroltest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.accesscontrol.IAccessControlAbstractFactory;
import dal.asd.catme.accesscontrol.IAccessControlModelAbstractFactory;
import dal.asd.catme.accesscontrol.IRole;
import dal.asd.catme.accesscontrol.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoleTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    IAccessControlModelAbstractFactory accessControlModelAbstractFactory = baseAbstractFactory.makeAccessControlModelAbstractFactory();
    @Test
    public void getRoleIdTest()
    {
        IRole role = accessControlModelAbstractFactory.makeRole();

        role.setRoleId("001");
        assertEquals(role.getRoleId(),"001");
    }

    @Test
    public void setRoleIdTest()
    {
        IRole role = accessControlModelAbstractFactory.makeRole();

        role.setRoleId("001");
        assertEquals(role.getRoleId(),"001");
    }

    @Test
    public void getRoleNameTest()
    {
        IRole role = accessControlModelAbstractFactory.makeRole();

        role.setRoleName("Admin");
        assertEquals(role.getRoleName(),"Admin");
    }

    @Test
    public void setRoleNameTest()
    {
        IRole role = accessControlModelAbstractFactory.makeRole();

        role.setRoleName("Admin");
        assertEquals(role.getRoleName(),"Admin");
    }
}
