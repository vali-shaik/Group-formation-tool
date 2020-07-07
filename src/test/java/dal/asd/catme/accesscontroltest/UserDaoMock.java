package dal.asd.catme.accesscontroltest;

import java.sql.Connection;
import java.util.ArrayList;

import dal.asd.catme.accesscontrol.IUserDao;
import dal.asd.catme.accesscontrol.User;

public class UserDaoMock implements IUserDao
{

    ArrayList<User> users;


    public UserDaoMock(ArrayList<User> users)
    {
        this.users = users;
    }


    @Override
    public int checkExistingUser(String bannerId, Connection con)
    {
        // TODO Auto-generated method stub
        for (User u : users)
        {
            if (u.getBannerId().equalsIgnoreCase(bannerId))
                return 1;
        }
        return 0;

    }

    @Override
    public int addUser(User user, Connection con)
    {
        users.add(user);
        return 1;

    }

    @Override
    public User getUser(String bannerId, Connection con)
    {
        for (User u : users)
        {
            if (u.getBannerId().equalsIgnoreCase(bannerId))
                return u;
        }
        return null;
    }

}
