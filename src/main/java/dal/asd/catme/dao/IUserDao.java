package dal.asd.catme.dao;

import java.sql.Connection;

import dal.asd.catme.beans.User;

public interface IUserDao {
	public int checkExistingUser(String bannerId, Connection con);
	public String addUser(User user, Connection con);

}
