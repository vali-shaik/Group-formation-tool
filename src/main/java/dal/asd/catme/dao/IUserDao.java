package dal.asd.catme.dao;

import dal.asd.catme.beans.User;
import dal.asd.catme.exception.CatmeException;

import java.sql.Connection;

public interface IUserDao {
	public int checkExistingUser(String bannerId, Connection con);
	public int addUser(User user, Connection con);
	public User getUser(String bannerId, Connection con);

	public void resetPassword(User u, Connection con) throws CatmeException;

	public String readBannerIdFromToken(String token) throws CatmeException;

	public void generatePasswordResetToken(User u, String token) throws CatmeException;

	public void removeToken(String bannerId);

	public boolean matchWithPasswordHistory(String bannerId, String password) throws CatmeException;

	public void deleteOverLimitPasswords(String bannerId) throws CatmeException;

}
