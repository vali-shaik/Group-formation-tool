package dal.asd.catme.mock;

import dal.asd.catme.beans.*;
import dal.asd.catme.dao.IRoleDao;

import java.sql.Connection;
import java.util.ArrayList;

public class RoleDaoMock implements IRoleDao
{
    ArrayList<User> users;
    Course c;

    public RoleDaoMock(ArrayList<User> users, Course c)
    {
        this.users = users;
        this.c = c;
    }

    @Override
    public int assignRole(String bannerId, int roleId, Connection con)
    {
        for(User u: users)
        {
            if(u.getBannerId().equalsIgnoreCase(bannerId))
            {
                Role l = new Role();
                l.setRoleId(String.valueOf(roleId));
                ArrayList<Role> roles = new ArrayList<>();
                roles.add(l);

                u.setRole(roles);
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int addInstructor(int courseId, int userRoleId, Connection con)
    {
        return 1;
    }

    @Override
    public int assignTa(Enrollment user, Connection con)
    {
        return 1;
    }

    @Override
    public int checkCourseInstructor(String bannerId, int courseId, Connection con)
    {
        for(Instructor i:c.getInstructors())
        {
            if(i.getBannerId().equalsIgnoreCase(bannerId))
                return 1;
        }
        return 0;
    }

    @Override
    public int checkUserRole(String bannerId, int roleId, Connection con)
    {
        for(User u: users)
        {
            if(u.getBannerId().equalsIgnoreCase(bannerId))
            {
                for(Role l:u.getRole())
                {
                    if(l.getRoleId().equals(String.valueOf(roleId)))
                        return 1;
                }
                return 0;
            }
        }
        return 0;
    }

    @Override
    public int getUserRoleId(String bannerId, int roleId, Connection con)
    {
        return 1;
    }
}
