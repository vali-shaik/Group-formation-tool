package dal.asd.catme.accesscontrol;

import dal.asd.catme.password.IPasswordPolicyCheckerService;
import dal.asd.catme.password.PasswordPolicyCheckerImpl;

public class AccessControlAbstractFactoryImpl implements IAccessControlAbstractFactory
{
    private static IAccessControlAbstractFactory abstractFactory = null;

    private IAdminDao adminDao;
    private IUserDao userDao;

    private IAdminService adminService;
    private IMailSenderService mailSenderService;
    private IUserService userService;
    private IPasswordPolicyCheckerService passwordPolicyCheckerService;

    AccessControlAbstractFactoryImpl()
    {
        adminDao = new AdminDaoImpl();
        userDao = new UserDaoImpl();

        adminService = new AdminServiceImpl(adminDao);
        mailSenderService = new MailSenderServiceImpl();
        passwordPolicyCheckerService = new PasswordPolicyCheckerImpl();
        userService = new UserServiceImpl(userDao,passwordPolicyCheckerService);
    }

    public static IAccessControlAbstractFactory instance()
    {
        if(abstractFactory==null)
        {
            abstractFactory = new AccessControlAbstractFactoryImpl();
        }
        return abstractFactory;
    }

    @Override
    public IAdminDao getAdminDao()
    {
        return adminDao;
    }

    @Override
    public IAdminService getAdminService()
    {
        return adminService;
    }

    @Override
    public IMailSenderService getMailSenderService()
    {
        return mailSenderService;
    }

    @Override
    public IUserDao getUserDao()
    {
        return userDao;
    }

    @Override
    public IUserService getUserService()
    {
        return userService;
    }
}
