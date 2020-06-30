package dal.asd.catme.service;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.User;

import java.util.List;

public interface IListUserService
{
    List<User> getUsers(Course course);
}
