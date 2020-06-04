package dal.asd.catme.dao;

import java.sql.Connection;
import java.util.ArrayList;

import dal.asd.catme.beans.User;
import dal.asd.catme.dao.IUserDao;

public class UserDaoMock implements IUserDao{
	
	ArrayList<User> users;
	
	public UserDaoMock() {
				
	}

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
	public User getUser(String bannerId, Connection con) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean resetPassword(User u, Connection con) {
		// TODO Auto-generated method stub
		return false;
	}

}
