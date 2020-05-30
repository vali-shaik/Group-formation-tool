package dal.asd.catme.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dal.asd.catme.beans.User;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.util.CatmeUtil;

@Component
public class AddUserDaoImpl implements IAddUserDao {
	
	@Autowired
	DatabaseAccess db;
	
	@Override
	public int checkExistingUser(String bannerId) {
		int rowCount = 0;
		try{
			db.getConnection();
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
		if(0 == checkExistingUser(bannerId)){
			String query = "INSERT IGNORE INTO User (BannerId, FirstName, LastName, EmailId, Password) VALUES ( '" +
					bannerId + "' , '" + user.getFirstName() + "' , '" + user.getLastName() + "' , '" + user.getEmail() + "' , '" + user.getPassword() + "' );";

			db.executeUpdate(query);
			assignGuestRole(bannerId);
			return 1;
		}
		
		return 0;
	}

	@Override
	public int assignGuestRole(String bannerId) {
		String query = "INSERT IGNORE INTO UserRole (UserRoleId, BannerId, RoleId) VALUES ( NULL, '" +
				bannerId + "' , '" + CatmeUtil.GUEST_ROLE_ID + "' );";

		int rs = db.executeUpdate(query);
		return rs;
	}


}
