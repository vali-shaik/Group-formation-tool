
package dal.asd.catme.accesscontrol;

import java.sql.Connection;
import java.util.List;

public interface IUserDao
{
    int checkExistingUser(String bannerId, Connection con);

    int addUser(IUser user, Connection con);

    IUser getUser(String bannerId, Connection con);

    List<IUser> getUsers();

}
