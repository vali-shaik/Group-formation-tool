package dal.asd.catme.service;

import dal.asd.catme.beans.User;
import dal.asd.catme.dao.IUserDao;
import dal.asd.catme.util.CatmeUtil;
import dal.asd.catme.util.RandomPasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class PasswordResetService implements IPasswordResetService
{
    @Autowired
    IUserDao userDao;

    public PasswordResetService(IUserDao userDao)
    {
        this.userDao = userDao;
    }

    @Override
    public boolean userExists(String bannerid)
    {
        if(userDao.checkExistingUser(bannerid)==0)
            return false;
        return true;
    }

    @Override
    public User resetPassword(String bannerid)
    {
        if(!userExists(bannerid))
        {
            System.out.println("User does not exist");
            return null;

        }

        User u = userDao.getUser(bannerid);

        if(u==null)
        {
            System.out.println("Error Reading user from database");
            return null;
        }

        String newPassword = RandomPasswordGenerator.generateRandomPassword(CatmeUtil.RANDOM_PASSWORD_LENGTH);

        u.setPassword(newPassword);

        if(!userDao.resetPassword(u))
        {
            System.out.println("Error updating password to database");
            return null;
        }

        return u;
    }
}
