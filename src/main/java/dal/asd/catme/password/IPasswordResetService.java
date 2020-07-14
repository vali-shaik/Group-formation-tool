
package dal.asd.catme.password;

import dal.asd.catme.accesscontrol.IUser;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.exception.CatmeException;

public interface IPasswordResetService
{
    IUser generateResetLink(String bannerid);

    String validateToken(String token);

    void resetPassword(String bannerid, String password) throws CatmeException;
}
