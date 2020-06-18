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
import dal.asd.catme.util.PasswordRulesUtil;

public class UserServiceImpl implements IUserService{
	
	DatabaseAccess db;
	
	IUserDao userDao;
	
	IPasswordRulesConfig passwordRulesConfig;
	
	
	public boolean enforcePasswordPolicy(User user)
	{
		boolean flag=true;
		passwordRulesConfig=SystemConfig.instance().getPasswordEnforcementConfig();
		List<Boolean> validationResult=new ArrayList<>();
		if(PasswordRulesUtil.MINIMUM_LENGTH_ENABLE)
		{
			validationResult.add(passwordRulesConfig.validateMinimumLength(user.getPassword(), PasswordRulesUtil.MINIMUM_LENGTH));
		}
		if(PasswordRulesUtil.MAXIMUM_LENGTH_ENABLE)
		{
			validationResult.add(passwordRulesConfig.validateMaximumLength(user.getPassword(), PasswordRulesUtil.MAXIMUM_LENGTH));
		}
		if(PasswordRulesUtil.MINIMUM_LOWER_CASE_ENABLE)
		{
			validationResult.add(passwordRulesConfig.validateMinimumLowerCase(user.getPassword(), PasswordRulesUtil.MINIMUM_LOWER_CASE_LENGTH));
		}
		if(PasswordRulesUtil.MINIMUM_UPPER_CASE_ENABLE)
		{
			validationResult.add(passwordRulesConfig.validateMinimumUpperCase(user.getPassword(), PasswordRulesUtil.MINIMUM_UPPER_CASE_LENGTH));
		}
		if(PasswordRulesUtil.MINIMUM_SYMBOL_SPECIAL_ENABLE)
		{
			validationResult.add(passwordRulesConfig.validateMinimumSymbolsSpecialCharacters(user.getPassword(), PasswordRulesUtil.MINIMUM_SYMBOL_SPECIAL_LENGTH));
		}
		if(PasswordRulesUtil.NOT_ALLOWED_CHARACTER_ENABLE)
		{
			validationResult.add(passwordRulesConfig.checkNotAllowedCharacters(user.getPassword(), PasswordRulesUtil.NOT_ALLOWED_CHARACTER_REGEX));
		}
		if(PasswordRulesUtil.PASSWORD_HISTORY_ENABLE)
		{
			//DB logic to check for recent passwords
			validationResult.add(passwordRulesConfig.checkRecentPasswords(user));
		}
		if(validationResult.contains(false))
		{
			flag=false;
		}
		return flag;
	}
	
	
	@Override
	public String addUser(User user) {
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
			if(enforcePasswordPolicy(user))
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
