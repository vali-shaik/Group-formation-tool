package dal.asd.catme.service;

import dal.asd.catme.beans.User;

public interface IPasswordResetService
{

    public boolean userExists(String bannerid);

    public User resetPassword(String bannerid) ;
}
