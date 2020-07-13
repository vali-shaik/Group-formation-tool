package dal.asd.catme.passwordtest;

import dal.asd.catme.accesscontrol.IUser;
import dal.asd.catme.accesscontrol.IUserDao;
import dal.asd.catme.accesscontrol.Role;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.accesscontroltest.UserDaoMock;
import dal.asd.catme.exception.CatmeException;
import dal.asd.catme.password.IPasswordDao;
import dal.asd.catme.password.IPasswordResetService;
import dal.asd.catme.password.PasswordResetService;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PasswordResetServiceTest
{

    ArrayList<IUser> users = getUsers();
    IUserDao userDao = new UserDaoMock(users);
    IPasswordDao passwordDao = new PasswordDaoMock();

    @Test
    void generateResetLinkTest()
    {
        IPasswordResetService service = new PasswordResetService(userDao, passwordDao);

        assertNotNull(service.generateResetLink(users.get(0).getBannerId()));

        assertNull(service.generateResetLink("ASDV"));
    }

    @Test
    void validateTokenTest()
    {
        IPasswordResetService service = new PasswordResetService(userDao, passwordDao);

        assertNotNull(service.validateToken("@@@@"));

        assertNull(service.validateToken("!!!!"));
    }

    @Test
    void resetPasswordTest()
    {
        IPasswordResetService service = new PasswordResetService(userDao, passwordDao);

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

    ArrayList<IUser> getUsers()
    {
        ArrayList<IUser> users = new ArrayList<>();

        User u = new User("B00121212", "Last", "First", "abc@123.com");
        ArrayList<Role> roles = new ArrayList<>();
        u.setRole(roles);

        users.add(u);
        return users;
    }
}