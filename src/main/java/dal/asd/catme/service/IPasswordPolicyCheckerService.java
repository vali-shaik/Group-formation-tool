package dal.asd.catme.service;

import dal.asd.catme.beans.User;
import dal.asd.catme.exception.CatmeException;

public interface IPasswordPolicyCheckerService
{
    public boolean enforcePasswordPolicy(User user) throws CatmeException;
}
