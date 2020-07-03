package dal.asd.catme.accesscontrol;

import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.password.IPasswordPolicyCheckerService;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImpl implements IUserService
{
    DatabaseAccess db;

    IUserDao userDao;

    IPasswordPolicyCheckerService passwordPolicyCheckerService;

    @Override
    public String addUser(User user)
    {
        passwordPolicyCheckerService = SystemConfig.instance().getPasswordPolicyCheckerService();
        String isUserAdded = "An account already exists with this BannerId.";
        Connection con = null;
        try
        {
            db = SystemConfig.instance().getDatabaseAccess();
            con = db.getConnection();
        } catch (SQLException e1)
        {
            e1.printStackTrace();
        }
        try
        {
            userDao = SystemConfig.instance().getUserDao();
            if (passwordPolicyCheckerService.enforcePasswordPolicy(user))
            {
                if (1 == userDao.addUser(user, con))
                {
                    isUserAdded = "Successfully Signed Up.";
                }
            } else
            {
                isUserAdded = "weak password!!";
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (con != null)
            {
                try
                {
                    con.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return isUserAdded;
    }
}
