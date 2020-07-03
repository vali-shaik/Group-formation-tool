package dal.asd.catme.accesscontrol;

import dal.asd.catme.courses.Course;

import java.util.List;

public interface IListUserDao
{
    List<User> getUsers(Course course);
}
