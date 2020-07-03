package dal.asd.catme.accesscontrol;

import java.sql.Connection;

public interface IUserDao
{
    public int checkExistingUser(String bannerId, Connection con);

    public int addUser(User user, Connection con);

    public User getUser(String bannerId, Connection con);

}
