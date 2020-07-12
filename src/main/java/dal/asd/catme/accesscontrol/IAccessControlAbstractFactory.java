package dal.asd.catme.accesscontrol;

public interface IAccessControlAbstractFactory
{
    IAdminDao getAdminDao();
    IAdminService getAdminService();

    IMailSenderService getMailSenderService();

    IUserDao getUserDao();
    IUserService getUserService();
}
