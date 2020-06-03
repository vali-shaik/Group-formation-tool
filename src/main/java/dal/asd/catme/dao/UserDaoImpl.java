package dal.asd.catme.dao;

import java.sql.*;

import org.springframework.beans.factory.annotation.Autowired;
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
				String query = "INSERT IGNORE INTO User (BannerId, FirstName, LastName, EmailId, Password) VALUES ( '" +
						bannerId + "' , '" + user.getFirstName() + "' , '" + user.getLastName() + "' , '" + user.getEmail() + "' , '" + user.getPassword() + "' );";

				Statement stmt = con.createStatement();
				stmt.executeUpdate(query);
				roleDao.assignRole(bannerId, CatmeUtil.GUEST_ROLE_ID, con);
				return 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}


}
