package dal.asd.catme.coursestest;

import dal.asd.catme.POJOMock;
import dal.asd.catme.accesscontrol.IUser;
import dal.asd.catme.courses.*;
import dal.asd.catme.accesscontrol.IUserDao;
import dal.asd.catme.accesscontrol.Role;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.accesscontroltest.UserDaoMock;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoMock implements IRoleDao
{

    IUserDao userDao;
    ICourseDao courseDao;
    List<IUser> users;
    List<ICourse> courses;
    ICourse c;

    public RoleDaoMock(List<IUser> users, ICourse c)
    {
        this.users = users;
        this.c = c;
        userDao = new UserDaoMock(users);

    }

    public RoleDaoMock(ArrayList<IUser> users, List<ICourse> courses)
    {
        this.users = users;
        this.courses = courses;
        this.c = courses.get(2);
        userDao = new UserDaoMock(users);
        courseDao = new CourseDaoMock(courses);
    }


    @Override
    public int assignRole(String bannerId, int roleId, Connection con)
    {
        for (IUser u : users)
        {
            if (u.getBannerId().equalsIgnoreCase(bannerId))
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
    public int addInstructor(String courseId, int userRoleId, Connection con)
    {
        return 1;
    }

    @Override
    public int assignTa(IEnrollment user, Connection con)
    {
        if (0 != userDao.checkExistingUser(user.getBannerId(), con))
        {

            if (0 != courseDao.checkCourseExists(user.getCourseId(), con))
            {

                if (0 == courseDao.checkCourseRegistration(user.getBannerId(), user.getCourseId(), con))
                {

                    if (0 == checkCourseInstructor(user.getBannerId(), user.getCourseId(), con))
                    {
                        for (ICourse c : courses)
                        {
                            if (c.getCourseId().equalsIgnoreCase(user.toString()))
                            {

                                for (IUser u : users)
                                {
                                    if (u.getBannerId().equalsIgnoreCase(user.getBannerId()))
                                    {
                                        return 1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public int checkCourseInstructor(String bannerId, String courseId, Connection con)
    {
        for (IUser i : POJOMock.getUsers())
        {
            if (i.getBannerId().equalsIgnoreCase(bannerId))
                return 1;
        }
        return 0;

    }

    @Override
    public int checkUserRole(String bannerId, int roleId, Connection con)
    {
        for (IUser u : users)
        {
            if (u.getBannerId().equalsIgnoreCase(bannerId))
            {
                for (Role l : u.getRole())
                {
                    if (l.getRoleId().equals(String.valueOf(roleId)))
                        return 1;
                }
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