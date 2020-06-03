package dal.asd.catme.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dal.asd.catme.beans.User;
import dal.asd.catme.dao.IUserDao;
import dal.asd.catme.database.DatabaseAccess;

@Component
public class UserServiceImpl implements IUserService{
	
	@Autowired
	DatabaseAccess db;
	
	@Autowired
	IUserDao userDao;

	@Override
	public String addUser(User user) {
		// TODO Auto-generated method stub
		Connection con = db.getConnection();
		try {
			return (userDao.addUser(user,con));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(con!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "";
	}

}
