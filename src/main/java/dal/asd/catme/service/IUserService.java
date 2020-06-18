package dal.asd.catme.service;

import dal.asd.catme.beans.User;
import dal.asd.catme.exception.CatmeException;

public interface IUserService {
	public String addUser(User user);
	public boolean enforcePasswordPolicy(User user) throws CatmeException;

}
