package dal.asd.catme.accesscontrol;

import dal.asd.catme.courses.Course;

import java.sql.Connection;
import java.util.List;

public interface IUserDao
{
    int checkExistingUser(String bannerId, Connection con);

    int addUser(User user, Connection con);

    User getUser(String bannerId, Connection con);

    List<User> getUsers();

}
