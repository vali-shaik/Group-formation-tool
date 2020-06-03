package dal.asd.catme.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dal.asd.catme.beans.User;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.util.CatmeUtil;

@Component
public class UserDaoImpl implements IUserDao{
	
	@Autowired
	DatabaseAccess db;	

	@Autowired
	IRoleDao roleDao;

	Connection con = null;


	@Override
	public int checkExistingUser(String bannerId) {
		int rowCount = 0;
		try{

			con = db.getConnection();
			String query = "SELECT EXISTS(SELECT * FROM User WHERE BannerId = '" + bannerId + "' );";
			ResultSet rs = db.executeQuery(query);
			rs.next();
			rowCount = rs.getInt(1);
		}
		catch(SQLException e){e.printStackTrace();}		
		return rowCount;
	}
	
	@Override
	public int addUser(User user) {
		String bannerId = user.getBannerId();
		try {
			con = db.getConnection();
			if(0 == checkExistingUser(bannerId)){
				String query = "INSERT IGNORE INTO User (BannerId, FirstName, LastName, EmailId, Password) VALUES ( '" +
						bannerId + "' , '" + user.getFirstName() + "' , '" + user.getLastName() + "' , '" + user.getEmail() + "' , '" + user.getPassword() + "' );";

				db.executeUpdate(query);
				roleDao.assignRole(bannerId, CatmeUtil.GUEST_ROLE_ID);
				return 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			if (con != null) {
		        try {
		            con.close();
		        } catch (SQLException e) { e.printStackTrace(); }
		    }
		}
		
		return 0;
	}

	@Override
	public User getUser(String bannerId) {
		try {
			con = db.getConnection();

			String getUserSQL = "SELECT * FROM User WHERE BannerId=?";

			PreparedStatement getUser = con.prepareStatement(getUserSQL);
			getUser.setString(1,bannerId);

			ResultSet rs = getUser.executeQuery();

			rs.next();
			String firstname = rs.getString(2);
			String lastname = rs.getString(3);
			String emailid = rs.getString(4);

			User u = new User(bannerId,lastname,firstname,emailid);

			return u;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) { e.printStackTrace(); }
			}
		}

		return null;
	}

	@Override
	public boolean resetPassword(User u)
	{
		try {
			con = db.getConnection();

			String resetPasswordSQL = "UPDATE `User` SET `Password`=? WHERE `BannerId`=?";

			PreparedStatement resetPasswordStmt = con.prepareStatement(resetPasswordSQL);
			resetPasswordStmt.setString(1,u.getPassword());
			resetPasswordStmt.setString(2,u.getBannerId());

//			int status = resetPasswordStmt.executeUpdate();

			System.out.println("Status of Password Update");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) { e.printStackTrace(); }
			}
		}

		return false;
	}


}
