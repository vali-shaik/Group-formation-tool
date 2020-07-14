package dal.asd.catme.accesscontrol;

import dal.asd.catme.courses.Course;

import java.util.List;

public interface IUserService
{
    String addUser(IUser user);

    List<IUser> getUsers();

}
