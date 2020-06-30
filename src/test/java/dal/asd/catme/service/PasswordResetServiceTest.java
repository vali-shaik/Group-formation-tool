package dal.asd.catme.service;

import dal.asd.catme.beans.Role;
import dal.asd.catme.beans.User;
import dal.asd.catme.dao.IPasswordDao;
import dal.asd.catme.dao.IUserDao;
import dal.asd.catme.dao.PasswordDaoMock;
import dal.asd.catme.dao.UserDaoMock;
import dal.asd.catme.exception.CatmeException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PasswordResetServiceTest
{

    ArrayList<User> users = getUsers();
    IUserDao userDao = new UserDaoMock(users);
    IPasswordDao passwordDao = new PasswordDaoMock();

    @Test
    void userExists()
    {
        IPasswordResetService service = new PasswordResetService(userDao,passwordDao);

        assertTrue(service.userExists(users.get(0).getBannerId()));

        assertFalse(service.userExists("B00123456"));
    }

    @Test
    void generateResetLinkTest()
    {
        IPasswordResetService service = new PasswordResetService(userDao,passwordDao);

        assertNotNull(service.generateResetLink(users.get(0).getBannerId()));

        assertNull(service.generateResetLink("ASDV"));
    }

    @Test
    void validateTokenTest()
    {
        IPasswordResetService service = new PasswordResetService(userDao,passwordDao);

        assertNotNull(service.validateToken("@@@@"));

        assertNull(service.validateToken("!!!!"));
    }

    @Test
    void resetPasswordTest()
    {
        IPasswordResetService service = new PasswordResetService(userDao,passwordDao);

        try
        {
            service.resetPassword("B00121212", "ABCD");
        } catch (CatmeException e)
        {
            e.printStackTrace();
            fail();
        }
        try
        {

            service.resetPassword("B00121212", null);
            fail();
        } catch (CatmeException e)
        {

        }
    }

    ArrayList<User> getUsers()
    {
        ArrayList<User> users = new ArrayList<>();

        User u = new User("B00121212", "Last", "First", "abc@123.com");
        ArrayList<Role> roles = new ArrayList<>();
        u.setRole(roles);

        users.add(u);
        return users;
    }
}