package dal.asd.catme.accesscontroltest;

import dal.asd.catme.POJOMock;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.accesscontrol.IUserDao;

import java.sql.Connection;
import java.util.List;

public class UserDaoMock implements IUserDao
{

    List<User> users;


    public UserDaoMock(List<User> users)
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
    public List<User> getUsers()
    {
        return POJOMock.getUsers();
    }

    @Override
    public int addUser(User user, Connection con)
    {
        // TODO Auto-generated method stub
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
