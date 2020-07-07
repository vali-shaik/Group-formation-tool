package dal.asd.catme.accesscontroltest;

import dal.asd.catme.accesscontrol.IListUserDao;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.courses.Course;
import dal.asd.catme.courses.IListCourseDao;
import dal.asd.catme.coursestest.POJOMock;
import dal.asd.catme.util.CatmeUtil;

import java.util.ArrayList;
import java.util.List;

public class ListDetailsDaoMock implements IListUserDao, IListCourseDao
{

    @Override
    public List<Course> getAllCourses()
    {
        return POJOMock.getCourses();

    }

    @Override
    public List<User> getUsers(Course course)
    {
        return POJOMock.getUsers();
    }

}
