package dal.asd.catme.accesscontrol;

import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.courses.Course;
import dal.asd.catme.courses.IListCourseDao;
import dal.asd.catme.courses.IListCourseService;

import java.util.List;


public class ListDetailsServiceImpl implements IListCourseService, IListUserService
{
    IListCourseDao listCourse;
    IListUserDao listUser;

    @Override
    public List<User> getUsers(Course course)
    {
        listUser = SystemConfig.instance().getListUserDao();
        return listUser.getUsers(course);
    }

    @Override
    public List<Course> getAllCourses()
    {
        listCourse = SystemConfig.instance().getListCourseDao();
        return listCourse.getAllCourses();
    }
}
