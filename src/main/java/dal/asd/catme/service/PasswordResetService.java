package dal.asd.catme.service;

import java.sql.Connection;
import java.sql.SQLException;

import dal.asd.catme.beans.User;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.dao.IUserDao;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.exception.CatmeException;
import dal.asd.catme.util.RandomTokenGenerator;

import static dal.asd.catme.util.MailSenderUtil.TOKEN_LENGTH;


public class PasswordResetService implements IPasswordResetService
{
    IUserDao userDao;

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
    public User generateResetLink(String bannerid)
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
            User u = userDao.getUser(bannerid,con);

            if (u == null)
            {
                System.out.println("Error Reading user from database");
                return null;
            }

            String token = RandomTokenGenerator.generateRandomPassword(TOKEN_LENGTH);

            u.setPassword(token);

            userDao.generatePasswordResetToken(u,token);

            return u;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        catch (CatmeException e)
        {
            return null;
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

    @Override
    public String validateToken(String token)
    {
        try
        {
            String bannerid = userDao.readBannerIdFromToken(token);
            return bannerid;
        }
        catch (CatmeException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean resetPassword(String bannerid, String password)
    {
        User u = new User();
        u.setBannerId(bannerid);
        u.setPassword(password);

        try
        {
            db=SystemConfig.instance().getDatabaseAccess();
            con = db.getConnection();

            if(userDao.resetPassword(u,con))
            {
                userDao.removeToken(bannerid);
                return true;
            }
            return false;

        } catch (SQLException throwables)
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
        return false;
    }
}
