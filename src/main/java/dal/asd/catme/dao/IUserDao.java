package dal.asd.catme.dao;

import dal.asd.catme.beans.User;

public interface IUserDao {
	public int checkExistingUser(String bannerId);
	public String addUser(User user);

}
