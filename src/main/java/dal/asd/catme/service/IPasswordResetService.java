
package dal.asd.catme.service;

import dal.asd.catme.beans.User;
import dal.asd.catme.exception.CatmeException;

public interface IPasswordResetService
{
    public User generateResetLink(String bannerid);

    public String validateToken(String token);

    public void resetPassword(String bannerid, String password) throws CatmeException;
}
