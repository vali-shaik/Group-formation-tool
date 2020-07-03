package dal.asd.catme.accesscontroltest;

import dal.asd.catme.accesscontrol.IListUserDao;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.courses.Course;
import dal.asd.catme.courses.IListCourseDao;
import dal.asd.catme.util.CatmeUtil;

import java.util.ArrayList;
import java.util.List;

public class ListDetailsDaoMock implements IListUserDao, IListCourseDao
{

    @Override
    public List<Course> getAllCourses()
    {
        List<Course> courseList = new
                ArrayList<>();
        courseList.add(new Course(CatmeUtil.WEB_ID,
                CatmeUtil.ADVANCED_WEB_SERVICES));
        return courseList;

    }

    @Override
    public List<User> getUsers(Course course)
    {
        List<User> users = new ArrayList<>();
        users.add(new
                User(CatmeUtil.BANNER_ID, CatmeUtil.LAST_NAME, CatmeUtil.FIRST_NAME));
        return users;
    }

}
