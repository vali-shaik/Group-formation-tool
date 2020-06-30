package dal.asd.catme.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.exception.CatmeException;
import dal.asd.catme.util.PasswordRulesUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

import dal.asd.catme.beans.User;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.util.CatmeUtil;

import static dal.asd.catme.util.DBQueriesUtil.*;

public class UserDaoImpl implements IUserDao
{

    IRoleDao roleDao;

    PasswordEncoder p;


    @Override
    public int checkExistingUser(String bannerId, Connection con)
    {
        int rowCount = 0;
        try
        {
            PreparedStatement stmt = con.prepareStatement(CHECK_EXISTING_USER_QUERY);
            stmt.setString(1, bannerId);

            ResultSet rs = stmt.executeQuery();
            rs.next();
            rowCount = rs.getInt(1);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return rowCount;
    }

    @Override
    public int addUser(User user, Connection con)
    {
        String bannerId = user.getBannerId();
        try
        {
            if (0 == checkExistingUser(bannerId, con))
            {
                p = SystemConfig.instance().getPasswordEncoder();
                PreparedStatement stmt = con.prepareStatement(ADD_USER_QUERY);
                stmt.setString(1, user.getBannerId());
                stmt.setString(2, user.getFirstName());
                stmt.setString(3, user.getLastName());
                stmt.setString(4, user.getEmail());
                stmt.setString(5, p.encode(user.getPassword()));

                stmt.executeUpdate();
                roleDao = SystemConfig.instance().getRoleDao();
                roleDao.assignRole(bannerId, CatmeUtil.GUEST_ROLE_ID, con);
                return 1;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public User getUser(String bannerId, Connection con)
    {
        try
        {
            PreparedStatement getUser = con.prepareStatement(GET_USER_QUERY);
            getUser.setString(1, bannerId);

            ResultSet rs = getUser.executeQuery();

            rs.next();
            String firstname = rs.getString(2);
            String lastname = rs.getString(3);
            String emailid = rs.getString(4);

            User u = new User(bannerId, lastname, firstname, emailid);

            return u;
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }


}
