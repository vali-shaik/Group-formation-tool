package dal.asd.catme.accesscontrol;

import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.courses.CourseAbstractFactoryImpl;
import dal.asd.catme.courses.IRoleDao;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.util.CatmeUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dal.asd.catme.util.DBQueriesUtil.*;

public class UserDaoImpl implements IUserDao
{
    IRoleDao roleDao;

    PasswordEncoder p;

    IAccessControlModelAbstractFactory modelAbstractFactory = AccessControlModelAbstractFactoryImpl.instance();

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
    public int addUser(IUser user, Connection con)
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
                roleDao = CourseAbstractFactoryImpl.instance().getRoleDao();
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
    public IUser getUser(String bannerId, Connection con)
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

            IUser u = modelAbstractFactory.createUser();
            u.setBannerId(bannerId);
            u.setFirstName(firstname);
            u.setLastName(lastname);
            u.setEmail(emailid);

            return u;
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<IUser> getUsers()
    {
        DatabaseAccess db;
        Connection connection = null;
        List<IUser> users = new ArrayList<>();
        try
        {
            db = SystemConfig.instance().getDatabaseAccess();
            connection = db.getConnection();
            ResultSet resultSet = listUsers(connection, LIST_USER_QUERY);

            while (resultSet.next())
            {
                IUser user = modelAbstractFactory.createUser();
                user.setBannerId(resultSet.getString(CatmeUtil.BANNER_ID));
                user.setFirstName(resultSet.getString(CatmeUtil.FIRST_NAME));
                user.setLastName(resultSet.getString(CatmeUtil.LAST_NAME));
                users.add(user);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                connection.close();
            } catch (SQLException|NullPointerException e)
            {
                e.printStackTrace();
            }
        }
        return users;
    }

    private ResultSet listUsers(Connection connection, String query)
    {
        ResultSet rs = null;
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, CatmeUtil.STUDENT_ROLE);
            rs = preparedStatement.executeQuery();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return rs;
    }
}
