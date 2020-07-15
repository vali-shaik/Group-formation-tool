package dal.asd.catme.password;

import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.exception.CatmeException;

public interface IPasswordResetService
{
    User generateResetLink(String bannerid);

    String validateToken(String token);

    void resetPassword(String bannerid, String password) throws CatmeException;
}
