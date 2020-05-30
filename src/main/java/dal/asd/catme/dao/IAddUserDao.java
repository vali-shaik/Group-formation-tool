package dal.asd.catme.dao;

import dal.asd.catme.beans.User;

public interface IAddUserDao {
	public int addUser(User user);
	public int assignGuestRole(String bannerId);
	int checkExistingUser(String bannerId);

}
