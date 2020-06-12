package dal.asd.catme.service;

import java.sql.Connection;
import java.sql.SQLException;

import dal.asd.catme.beans.User;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.dao.IUserDao;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.util.CatmeUtil;
import dal.asd.catme.util.RandomPasswordGenerator;

import static dal.asd.catme.util.MailSenderUtil.RANDOM_PASSWORD_LENGTH;


public class PasswordResetService implements IPasswordResetService
{
    IUserDao userDao;

    DatabaseAccess db;

    Connection con;

    public PasswordResetService(IUserDao userDao)
    {
        this.userDao = userDao;
    }
    public PasswordResetService()
    {
    }

    @Override
    public boolean userExists(String bannerid)
    {
    	userDao=SystemConfig.instance().getUserDao();
        if(userDao.checkExistingUser(bannerid,con)==0)
            return false;
        return true;
    }

    @Override
    public User resetPassword(String bannerid)
    {
        try
        {
        	db=SystemConfig.instance().getDatabaseAccess();
            con = db.getConnection();
            System.out.println("Database Connected");

            if (!userExists(bannerid))
            {
                System.out.println("User does not exist");
                return null;

            }
            userDao=SystemConfig.instance().getUserDao();
            User u = userDao.getUser(bannerid,con);

            if (u == null)
            {
                System.out.println("Error Reading user from database");
                return null;
            }

            String newPassword = RandomPasswordGenerator.generateRandomPassword(RANDOM_PASSWORD_LENGTH);

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
