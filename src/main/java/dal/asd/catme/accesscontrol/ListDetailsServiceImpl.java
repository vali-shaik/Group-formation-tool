package dal.asd.catme.accesscontrol;

import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.courses.Course;
import dal.asd.catme.courses.IListCourseDao;
import dal.asd.catme.courses.IListCourseService;

import java.util.List;


public class ListDetailsServiceImpl implements IListCourseService, IListUserService
{
    IListUserDao listUser;
    IListCourseDao listCourse;

    public ListDetailsServiceImpl(IListUserDao listUser, IListCourseDao listCourse)
    {
        this.listUser = listUser;
        this.listCourse = listCourse;
    }

    @Override
    public List<User> getUsers(Course course)
    {
        return listUser.getUsers(course);
    }

    @Override
    public List<Course> getAllCourses()
    {
        return listCourse.getAllCourses();
    }
}
