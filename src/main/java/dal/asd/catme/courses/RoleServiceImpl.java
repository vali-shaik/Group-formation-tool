package dal.asd.catme.courses;

import dal.asd.catme.accesscontrol.IRoleDao;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.database.DatabaseAccess;

import java.sql.Connection;
import java.sql.SQLException;

public class RoleServiceImpl implements IRoleService
{

    IRoleDao roleDao;

    DatabaseAccess db;

    @Override
    public String assignTa(Enrollment user)
    {
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
            roleDao = SystemConfig.instance().getRoleDao();
            return (roleDao.assignTa(user, con));
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
        return "";
    }
}
