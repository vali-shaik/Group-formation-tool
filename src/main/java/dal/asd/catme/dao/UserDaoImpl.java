package dal.asd.catme.dao;

import java.sql.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dal.asd.catme.beans.User;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.util.CatmeUtil;

@Component
public class UserDaoImpl implements IUserDao{

	IRoleDao roleDao;

	PasswordEncoder p;



	@Override
	public int checkExistingUser(String bannerId, Connection con) {
		int rowCount = 0;
		try{
			String query = "SELECT EXISTS(SELECT * FROM User WHERE BannerId = '" + bannerId + "' );";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			rowCount = rs.getInt(1);
		}
		catch(SQLException e){e.printStackTrace();}		
		return rowCount;
	}
	
	@Override
	public int addUser(User user, Connection con) {
		String bannerId = user.getBannerId();
		try {
			if(0 == checkExistingUser(bannerId, con)){
				p=SystemConfig.instance().getPasswordEncoder();
				String query = "INSERT IGNORE INTO User (BannerId, FirstName, LastName, EmailId, Password) VALUES ( '" +
						bannerId + "' , '" + user.getFirstName() + "' , '" + user.getLastName() + "' , '" + user.getEmail() + "' , '" + p.encode(user.getPassword()) + "' );";

				Statement stmt = con.createStatement();
				stmt.executeUpdate(query);
				roleDao=SystemConfig.instance().getRoleDao();
				roleDao.assignRole(bannerId, CatmeUtil.GUEST_ROLE_ID, con);
				return 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public User getUser(String bannerId, Connection con) {
		try {

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

		return null;
	}

	@Override
	public boolean resetPassword(User u, Connection con)
	{
		try {
			String encodedPassword = p.encode(u.getPassword());

			String resetPasswordSQL = "UPDATE `User` SET `Password`=? WHERE `BannerId`=?";

			PreparedStatement resetPasswordStmt = con.prepareStatement(resetPasswordSQL);
			resetPasswordStmt.setString(1,encodedPassword);
			resetPasswordStmt.setString(2,u.getBannerId());

			int status = resetPasswordStmt.executeUpdate();

			if(status==0)
				return false;

			System.out.println("Password Reset Successful");
//			System.out.println("Status of Password Update");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}


}
