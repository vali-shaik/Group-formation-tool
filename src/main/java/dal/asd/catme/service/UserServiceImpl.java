package dal.asd.catme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dal.asd.catme.beans.User;
import dal.asd.catme.dao.IUserDao;

@Component
public class UserServiceImpl implements IUserService{
	
	@Autowired
	IUserDao userDao;

	@Override
	public String addUser(User user) {
		// TODO Auto-generated method stub
		return (userDao.addUser(user));
	}

}
