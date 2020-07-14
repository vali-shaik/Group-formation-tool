package dal.asd.catme.password;

import dal.asd.catme.accesscontrol.IUser;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.exception.CatmeException;

import java.sql.Connection;

public interface IPasswordDao
{

    void resetPassword(IUser u, Connection con) throws CatmeException;

    String readBannerIdFromToken(String token) throws CatmeException;

    void generatePasswordResetToken(IUser u, String token) throws CatmeException;

    void removeToken(String bannerId);

    boolean matchWithPasswordHistory(String bannerId, String password) throws CatmeException;

    void deleteOverLimitPasswords(String bannerId) throws CatmeException;
}
