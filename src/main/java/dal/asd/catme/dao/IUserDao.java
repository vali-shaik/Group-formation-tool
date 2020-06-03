package dal.asd.catme.dao;

import dal.asd.catme.beans.User;

public interface IUserDao {
	public int checkExistingUser(String bannerId);
	public int addUser(User user);
	public User getUser(String bannerId);

	public boolean resetPassword(User u);

}
