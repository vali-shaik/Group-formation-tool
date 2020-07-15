package dal.asd.catme.password;

import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.exception.CatmeException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dal.asd.catme.util.DBQueriesUtil.*;

public class PasswordDaoImpl implements IPasswordDao
{

    PasswordEncoder p;

    @Override
    public void resetPassword(User u, Connection con) throws CatmeException
    {
        p = SystemConfig.instance().getPasswordEncoder();
        try
        {
            String encodedPassword = p.encode(u.getPassword());

            if (PasswordRulesUtil.PASSWORD_HISTORY_ENABLE && matchWithPasswordHistory(u.getBannerId(), u.getPassword()))
            {
                throw new CatmeException("Password should not match with last 10 passwords");
            }

            PreparedStatement resetPasswordStmt = con.prepareStatement(RESET_PASSWORD_QUERY);
            resetPasswordStmt.setString(1, encodedPassword);
            resetPasswordStmt.setString(2, u.getBannerId());

            int status = resetPasswordStmt.executeUpdate();

            if (status == 0)
                throw new CatmeException("Error Updating Password");

            PreparedStatement passwordHistoryStmt = con.prepareStatement(PasswordRulesUtil.PASSWORD_ADD_HISTORY);
            passwordHistoryStmt.setString(1, encodedPassword);
            passwordHistoryStmt.setString(2, u.getBannerId());

            status = passwordHistoryStmt.executeUpdate();
            System.out.println("Status: " + status);
            if (status == 0)
                throw new CatmeException("Error Creating Password History");

            deleteOverLimitPasswords(u.getBannerId());

            System.out.println("Password Reset Successful");
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void generatePasswordResetToken(User u, String token) throws CatmeException
    {
        DatabaseAccess db = SystemConfig.instance().getDatabaseAccess();
        Connection con = null;
        try
        {
            con = db.getConnection();

            PreparedStatement stmt = con.prepareStatement(GENERATE_PASSWORD_RESET_TOKEN);
            stmt.setString(1, u.getBannerId());
            stmt.setString(2, token);

            stmt.executeUpdate();
            System.out.println("Token: " + token);
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            throw new CatmeException("Error Generating Token.. Try Again");
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
    }

    public String readBannerIdFromToken(String token) throws CatmeException
    {
        DatabaseAccess db = SystemConfig.instance().getDatabaseAccess();
        Connection con = null;
        try
        {
            con = db.getConnection();

            PreparedStatement stmt = con.prepareStatement(READ_BANNERID_FROM_TOKEN);
            stmt.setString(1, token);

            ResultSet rs = stmt.executeQuery();

            if (rs.next() == false)
            {
                throw new CatmeException("Invalid Link");
            }

            return rs.getString(1);

        } catch (SQLException throwables)
        {
            throw new CatmeException("Error Generating Token.. Try Again");
        } finally
        {
            try
            {
                con.close();
            } catch (SQLException | NullPointerException e)
            {
                return null;
            }
        }
    }

    public void removeToken(String bannerId)
    {
        DatabaseAccess db = SystemConfig.instance().getDatabaseAccess();
        Connection con = null;
        try
        {
            con = db.getConnection();

            PreparedStatement stmt = con.prepareStatement(REMOVE_TOKEN);
            stmt.setString(1, bannerId);

            stmt.executeUpdate();

        } catch (SQLException throwables)
        {
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
    }

    @Override
    public boolean matchWithPasswordHistory(String bannerId, String password) throws CatmeException
    {
        DatabaseAccess db = SystemConfig.instance().getDatabaseAccess();
        Connection con = null;
        PasswordEncoder p = SystemConfig.instance().getPasswordEncoder();

        try
        {
            con = db.getConnection();

            PreparedStatement stmt = con.prepareStatement(PasswordRulesUtil.PASSWORD_HISTORY_ORDER_QUERY);
            stmt.setString(1, bannerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                String oldPass = rs.getString(1);
                if (p.matches(password, oldPass))
                    return true;
            }
            return false;
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
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
        return false;
    }

    @Override
    public void deleteOverLimitPasswords(String bannerId) throws CatmeException
    {
        DatabaseAccess db = SystemConfig.instance().getDatabaseAccess();
        Connection con = null;

        try
        {
            con = db.getConnection();

            PreparedStatement stmt = con.prepareStatement(PasswordRulesUtil.PASSWORD_ALL_HISTORY_ORDER_QUERY);
            stmt.setString(1, bannerId);

            ResultSet rs = stmt.executeQuery();

            int resultSize = 0;
            int passwordId = 0;
            while (rs.next())
            {
                if (passwordId == 0)
                    passwordId = rs.getInt(1);
                resultSize++;
            }

            if (resultSize > PasswordRulesUtil.PASSWORD_HISTORY_LIMIT)
            {
                PreparedStatement deletePasswordStmt = con.prepareStatement(PasswordRulesUtil.PASSWORD_DELETE);
                deletePasswordStmt.setInt(1, passwordId);

                deletePasswordStmt.execute();
            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
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
    }
}
