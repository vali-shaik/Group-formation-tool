package dal.asd.catme.dao;

import java.sql.Connection;
import java.util.ArrayList;

import dal.asd.catme.beans.User;
import dal.asd.catme.exception.CatmeException;

public class UserDaoMock implements IUserDao{
	
	ArrayList<User> users;


    public UserDaoMock(ArrayList<User> users)
    {
        this.users = users;
    }


	@Override
	public int checkExistingUser(String bannerId, Connection con) {
		// TODO Auto-generated method stub
		for(User u: users)
        {
            if(u.getBannerId().equalsIgnoreCase(bannerId))
                return 1;
        }
        return 0;

	}

	@Override
	public int addUser(User user, Connection con) {
		// TODO Auto-generated method stub
		users.add(user);
        return 1;

	}

	@Override
	public User getUser(String bannerId, Connection con)
	{
		for(User u:users)
		{
			if(u.getBannerId().equalsIgnoreCase(bannerId))
				return u;
		}
		return null;
	}

	@Override
	public void resetPassword(User u, Connection con) throws CatmeException
	{
		if(u.getPassword()==null)
			throw new CatmeException();

	}

	@Override
	public String readBannerIdFromToken(String token) throws CatmeException
	{
		if(token.equals("@@@@"))
		{
			return "B00000000";
		}
		return null;
	}

	@Override
	public void generatePasswordResetToken(User u, String token) throws CatmeException
	{

	}

	@Override
	public void removeToken(String bannerId)
	{

	}

	@Override
	public boolean matchWithPasswordHistory(String bannerId, String password) throws CatmeException
	{
		return false;
	}

	@Override
	public void deleteOverLimitPasswords(String bannerId) throws CatmeException
	{

	}
}
