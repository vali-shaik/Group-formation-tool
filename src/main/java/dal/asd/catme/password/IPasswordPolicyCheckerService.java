package dal.asd.catme.password;

import dal.asd.catme.accesscontrol.IUser;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.exception.CatmeException;

public interface IPasswordPolicyCheckerService
{
    public boolean enforcePasswordPolicy(IUser user) throws CatmeException;
}