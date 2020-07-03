package dal.asd.catme.coursestest;

import dal.asd.catme.accesscontrol.IRoleDao;
import dal.asd.catme.accesscontrol.IUserDao;
import dal.asd.catme.accesscontrol.Instructor;
import dal.asd.catme.accesscontrol.Role;
import dal.asd.catme.accesscontrol.TInstructor;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.accesscontroltest.UserDaoMock;
import dal.asd.catme.courses.Course;
import dal.asd.catme.courses.Enrollment;
import dal.asd.catme.courses.ICourseDao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoMock implements IRoleDao
{

    IUserDao userDao;
    ICourseDao courseDao;
    ArrayList<User> users;
    ArrayList<Course> courses;
    Course c;

    public RoleDaoMock(ArrayList<User> users, Course c)
    {
        this.users = users;
        this.c = c;
        userDao = new UserDaoMock(users);

    }

    public RoleDaoMock(ArrayList<User> users, ArrayList<Course> courses)
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
        for (User u : users)
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
    public String assignTa(Enrollment user, Connection con)
    {
        if (0 != userDao.checkExistingUser(user.bannerId, con))
        {

            if (0 != courseDao.checkCourseExists(user.courseId, con))
            {

                if (0 == courseDao.checkCourseRegistration(user.bannerId, user.courseId, con))
                {

                    if (0 == checkCourseInstructor(user.bannerId, user.courseId, con))
                    {
                        for (Course c : courses)
                        {
                            if (c.getCourseId().equalsIgnoreCase(user.courseId))
                            {
                                List<TInstructor> tInstructors = c.gettInstructors();
                                if (tInstructors == null)
                                {
                                    tInstructors = new ArrayList<TInstructor>();
                                }

                                for (User u : users)
                                {
                                    if (u.getBannerId().equalsIgnoreCase(user.bannerId))
                                    {
                                        TInstructor t = new TInstructor();
                                        t.setBannerId(user.bannerId);
                                        t.setEmail(u.getEmail());
                                        t.setFirstName(u.getFirstName());
                                        t.setLastName(u.getLastName());

                                        return "Success";


                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public int checkCourseInstructor(String bannerId, String courseId, Connection con)
    {
        for (Instructor i : c.getInstructors())
        {
            if (i.getBannerId().equalsIgnoreCase(bannerId))
                return 1;
        }
        return 0;

    }

    @Override
    public int checkUserRole(String bannerId, int roleId, Connection con)
    {
        for (User u : users)
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
