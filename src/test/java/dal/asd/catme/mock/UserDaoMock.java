package dal.asd.catme.mock;

import dal.asd.catme.beans.User;
import dal.asd.catme.dao.IUserDao;
import dal.asd.catme.exception.CatmeException;
import dal.asd.catme.util.RandomTokenGenerator;

import java.sql.Connection;
import java.util.ArrayList;

import static dal.asd.catme.util.MailSenderUtil.TOKEN_LENGTH;

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
    public void resetPassword(User u, Connection con)
    {
        u.setPassword(RandomTokenGenerator.generateRandomPassword(TOKEN_LENGTH));
    }

    @Override
    public String readBannerIdFromToken(String token) throws CatmeException
    {
        return "B00121212";
    }

    @Override
    public void generatePasswordResetToken(User u, String token) throws CatmeException
    {
        RandomTokenGenerator.generateRandomPassword(TOKEN_LENGTH);
    }

    @Override
    public void removeToken(String bannerId)
    {


    }

    @Override
    public boolean matchWithPasswordHistory(String bannerId, String password) throws CatmeException
    {
        return false;
    }

    @Override
    public void deleteOverLimitPasswords(String bannerId) throws CatmeException
    {

    }
}
