package dal.asd.catme.password;

import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.accesscontrol.CatmeException;

public interface IPasswordPolicyCheckerService
{
    boolean enforcePasswordPolicy(User user) throws CatmeException;
}
