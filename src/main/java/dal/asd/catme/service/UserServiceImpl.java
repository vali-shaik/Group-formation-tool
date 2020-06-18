
package dal.asd.catme.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.asd.catme.beans.User;
import dal.asd.catme.config.IPasswordRulesConfig;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.dao.IUserDao;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.exception.CatmeException;
import dal.asd.catme.util.PasswordRulesUtil;

public class UserServiceImpl implements IUserService{
	
	DatabaseAccess db;
	
	IUserDao userDao;

	IPasswordPolicyCheckerService passwordPolicyCheckerService;
	
	
	@Override
	public String addUser(User user) {
		passwordPolicyCheckerService = SystemConfig.instance().getPasswordPolicyCheckerService();
		String isUserAdded = "An account already exists with this BannerId.";
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			db=SystemConfig.instance().getDatabaseAccess();
			con = db.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			userDao=SystemConfig.instance().getUserDao();
			//Password Policy Enforcement
			System.out.println("#USER details"+user.toString());
			if(passwordPolicyCheckerService.enforcePasswordPolicy(user))
			{
				if (1 == userDao.addUser(user,con)){
					isUserAdded = "Successfully Signed Up.";
				}
			}
			else
			{
				isUserAdded="weak password!!";
			}
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
		return isUserAdded;
	}

}
