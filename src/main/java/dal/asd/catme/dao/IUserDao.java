package dal.asd.catme.dao;

import dal.asd.catme.beans.User;
import dal.asd.catme.exception.CatmeException;

import java.sql.Connection;

public interface IUserDao
{
    public int checkExistingUser(String bannerId, Connection con);

    public int addUser(User user, Connection con);

    public User getUser(String bannerId, Connection con);

}
