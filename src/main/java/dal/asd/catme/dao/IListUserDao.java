package dal.asd.catme.dao;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.User;

import java.util.List;

public interface IListUserDao
{
    List<User> getUsers(Course course);
}
