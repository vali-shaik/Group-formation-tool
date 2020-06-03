package dal.asd.catme.service;

import dal.asd.catme.beans.User;
import dal.asd.catme.dao.IUserDao;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.util.CatmeUtil;
import dal.asd.catme.util.RandomPasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;


@Service
public class PasswordResetService implements IPasswordResetService
{
    @Autowired
    IUserDao userDao;

    @Autowired
    DatabaseAccess db;

    Connection con;

    public PasswordResetService(IUserDao userDao)
    {
        this.userDao = userDao;
    }

    @Override
    public boolean userExists(String bannerid)
    {
        if(userDao.checkExistingUser(bannerid,con)==0)
            return false;
        return true;
    }

    @Override
    public User resetPassword(String bannerid)
    {
        try
        {
            con = db.getConnection();
            System.out.println("Database Connected");

            if (!userExists(bannerid))
            {
                System.out.println("User does not exist");
                return null;

            }

            User u = userDao.getUser(bannerid,con);

            if (u == null)
            {
                System.out.println("Error Reading user from database");
                return null;
            }

            String newPassword = RandomPasswordGenerator.generateRandomPassword(CatmeUtil.RANDOM_PASSWORD_LENGTH);

            u.setPassword(newPassword);

            if (!userDao.resetPassword(u,con))
            {
                System.out.println("Error updating password to database");
                return null;
            }

            return u;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        finally
        {
            try
            {
                con.close();
                System.out.println("Connection closed");
            } catch (SQLException|NullPointerException e)
            {
//                e.printStackTrace();
            }
        }
        return null;
    }
}
