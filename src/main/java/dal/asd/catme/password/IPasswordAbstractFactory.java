package dal.asd.catme.password;

public interface IPasswordAbstractFactory
{
    IPasswordDao getPasswordDao();
    IPasswordPolicyCheckerService getPasswordPolicyCheckerService();
    IPasswordResetService getPasswordResetService();
    IPasswordRulesConfig getPasswordRulesConfig();
}
