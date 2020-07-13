
package dal.asd.catme.courses;

import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.database.DatabaseAccess;

import java.sql.Connection;
import java.sql.SQLException;

public class RoleServiceImpl implements IRoleService
{
    IRoleDao roleDao;
    DatabaseAccess db;

    public RoleServiceImpl(IRoleDao roleDao)
    {
        this.roleDao = roleDao;
    }

    @Override
    public String assignTa(Enrollment user)
    {
        Connection con = null;
        try
        {
            db = SystemConfig.instance().getDatabaseAccess();
            con = db.getConnection();

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
