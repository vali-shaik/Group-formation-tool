package dal.asd.catme.service;

import dal.asd.catme.beans.Role;
import dal.asd.catme.beans.User;
import dal.asd.catme.dao.IUserDao;
import dal.asd.catme.mock.UserDaoMock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PasswordResetServiceTest
{

    ArrayList<User> users = getUsers();
    IUserDao userDao = new UserDaoMock(users);
    @Test
    void userExists()
	{
		/*
		 * IPasswordResetService service = new PasswordResetService(userDao);
		 * 
		 * assertTrue(service.userExists(users.get(0).getBannerId()));
		 * 
		 * assertFalse(service.userExists("B00123456"));
		 */}

    ArrayList<User> getUsers()
    {
        ArrayList<User> users = new ArrayList<>();

        User u = new User("B00121212","Last","First","abc@123.com");
        ArrayList<Role> roles = new ArrayList<>();
        u.setRole(roles);

        users.add(u);
        return users;
    }
}