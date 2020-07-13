
package dal.asd.catme.password;

import dal.asd.catme.accesscontrol.IUser;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.exception.CatmeException;

public interface IPasswordResetService
{
    public IUser generateResetLink(String bannerid);

    public String validateToken(String token);

    public void resetPassword(String bannerid, String password) throws CatmeException;
}
