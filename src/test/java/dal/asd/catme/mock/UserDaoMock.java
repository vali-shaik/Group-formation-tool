package dal.asd.catme.mock;

import dal.asd.catme.beans.User;
import dal.asd.catme.dao.IUserDao;
import dal.asd.catme.util.CatmeUtil;
import dal.asd.catme.util.RandomPasswordGenerator;

import java.sql.Connection;
import java.util.ArrayList;

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
        for(User u: users)
        {
            if(u.getBannerId().equalsIgnoreCase(bannerId))
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
        for(User u:users)
        {
            if(u.getBannerId().equalsIgnoreCase(bannerId))
                return u;
        }
        return null;
    }

    @Override
    public boolean resetPassword(User u, Connection con)
    {
        u.setPassword(RandomPasswordGenerator.generateRandomPassword(CatmeUtil.RANDOM_PASSWORD_LENGTH));
        return true;
    }
}
