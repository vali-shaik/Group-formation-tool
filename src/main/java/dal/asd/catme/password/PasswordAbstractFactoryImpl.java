package dal.asd.catme.password;

import dal.asd.catme.accesscontrol.IUserDao;
import dal.asd.catme.accesscontrol.UserDaoImpl;

public class PasswordAbstractFactoryImpl implements IPasswordAbstractFactory
{
    private static IPasswordAbstractFactory passwordAbstractFactory=null;

    private IPasswordDao passwordDao;
    private IPasswordRulesConfig passwordRulesConfig;
    private IPasswordResetService passwordResetService;
    private IPasswordPolicyCheckerService passwordPolicyCheckerService;
    private IUserDao userDao;

    public PasswordAbstractFactoryImpl()
    {
        passwordDao = new PasswordDaoImpl();
        userDao = new UserDaoImpl();
        passwordPolicyCheckerService = new PasswordPolicyCheckerImpl();
        passwordResetService = new PasswordResetService(userDao,passwordDao);
        passwordRulesConfig = new PasswordRulesConfigImpl();
    }

    public static IPasswordAbstractFactory instance()
    {
        if(passwordAbstractFactory == null)
        {
            passwordAbstractFactory = new PasswordAbstractFactoryImpl();
        }
        return passwordAbstractFactory;
    }

    @Override
    public IPasswordDao makePasswordDao()
    {
        return passwordDao;
    }

    @Override
    public IPasswordPolicyCheckerService makePasswordPolicyCheckerService()
    {
        return passwordPolicyCheckerService;
    }

    @Override
    public IPasswordResetService makePasswordResetService()
    {
        return passwordResetService;
    }

    @Override
    public IPasswordRulesConfig makePasswordRulesConfig()
    {
        return passwordRulesConfig;
    }
}
