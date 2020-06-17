package dal.asd.catme.service;

import dal.asd.catme.beans.User;

public interface IPasswordResetService
{

    public boolean userExists(String bannerid);

    public User generateResetLink(String bannerid);

    public String validateToken(String token);

    public boolean resetPassword(String bannerid, String password);
}
