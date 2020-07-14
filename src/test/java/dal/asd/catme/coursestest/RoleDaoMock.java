package dal.asd.catme.coursestest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.POJOMock;
import dal.asd.catme.accesscontrol.*;
import dal.asd.catme.courses.*;
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

    IAccessControlModelAbstractFactory accessControlModelAbstractFactory = BaseAbstractFactoryMock.instance().makeAccessControlModelAbstractFactory();
    ICourseModelAbstractFactory courseModelAbstractFactory = BaseAbstractFactoryMock.instance().makeCourseModelAbstractFactory();
    IAccessControlAbstractFactory accessControlAbstractFactory = BaseAbstractFactoryMock.instance().makeAccessControlAbstractFactory();
    ICourseAbstractFactory courseAbstractFactory = BaseAbstractFactoryMock.instance().makeCourseAbstractFactory();

    public RoleDaoMock(ArrayList<IUser> users, List<ICourse> courses)
    {
        this.users = users;
        this.courses = courses;
        this.c = courses.get(2);
        userDao = accessControlAbstractFactory.makeUserDao();
        courseDao = courseAbstractFactory.makeCourseDao();
    }


    @Override
    public int assignRole(String bannerId, int roleId, Connection con)
    {
        for (IUser u : users)
        {
            if (u.getBannerId().equalsIgnoreCase(bannerId))
            {
                IRole l = accessControlModelAbstractFactory.makeRole();
                l.setRoleId(String.valueOf(roleId));
                ArrayList<IRole> roles = new ArrayList<>();
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
    public String assignTa(IEnrollment user, Connection con)
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
                                        return "Success";
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return "";
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
                for (IRole l : u.getRole())
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
