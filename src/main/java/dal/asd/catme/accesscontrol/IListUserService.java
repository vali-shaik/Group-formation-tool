package dal.asd.catme.accesscontrol;

import dal.asd.catme.courses.Course;

import java.util.List;

public interface IListUserService
{
    List<User> getUsers(Course course);
}
