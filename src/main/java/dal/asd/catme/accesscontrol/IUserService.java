package dal.asd.catme.accesscontrol;

import dal.asd.catme.courses.Course;

import java.util.List;

public interface IUserService
{
    public String addUser(User user);

    List<User> getUsers();

}
