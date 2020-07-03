package dal.asd.catme.password;

import dal.asd.catme.accesscontrol.IUserDao;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.exception.CatmeException;
import dal.asd.catme.util.RandomTokenGenerator;

import static dal.asd.catme.accesscontrol.MailSenderUtil.TOKEN_LENGTH;

import java.sql.Connection;
import java.sql.SQLException;


public class PasswordResetService implements IPasswordResetService
{
    IUserDao userDao;
    IPasswordDao passwordDao;
    DatabaseAccess db;
    Connection con;

    public PasswordResetService(IUserDao userDao, IPasswordDao passwordDao)
    {
        this.userDao = userDao;
        this.passwordDao = passwordDao;
    }

    @Override
    public User generateResetLink(String bannerid)
    {
        try
        {
            db = SystemConfig.instance().getDatabaseAccess();
            con = db.getConnection();

            if (userDao.checkExistingUser(bannerid,con)==0)
            {
                return null;

            }
            User u = userDao.getUser(bannerid, con);

            if (u == null)
            {
                return null;
            }

            String token = RandomTokenGenerator.generateRandomPassword(TOKEN_LENGTH);

            u.setPassword(token);

            passwordDao.generatePasswordResetToken(u, token);

            return u;
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        } catch (CatmeException e)
        {
            return null;
        } finally
        {
            try
            {
                con.close();
            } catch (SQLException | NullPointerException e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String validateToken(String token)
    {
        String bannerId = null;
        try
        {
            bannerId = passwordDao.readBannerIdFromToken(token);

        } catch (CatmeException e)
        {
            e.printStackTrace();
        }
        return bannerId;
    }

    @Override
    public void resetPassword(String bannerId, String password) throws CatmeException
    {
        User u = new User();
        u.setBannerId(bannerId);
        u.setPassword(password);

        try
        {
            db = SystemConfig.instance().getDatabaseAccess();
            con = db.getConnection();

            passwordDao.resetPassword(u, con);
            passwordDao.removeToken(bannerId);


        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            throw new CatmeException("Error Reseting Password");
        } finally
        {
            try
            {
                con.close();
                System.out.println("Connection closed");
            } catch (SQLException | NullPointerException e)
            {
                e.printStackTrace();
            }
        }
    }
}
