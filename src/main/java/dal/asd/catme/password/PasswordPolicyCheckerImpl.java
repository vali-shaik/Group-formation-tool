package dal.asd.catme.password;

import dal.asd.catme.accesscontrol.IUser;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.exception.CatmeException;

import java.util.ArrayList;
import java.util.List;

public class PasswordPolicyCheckerImpl implements IPasswordPolicyCheckerService
{
    IPasswordRulesConfig passwordRulesConfig;

    @Override
    public boolean enforcePasswordPolicy(IUser user) throws CatmeException
    {
        boolean flag = true;
        passwordRulesConfig = PasswordAbstractFactoryImpl.instance().getPasswordRulesConfig();
        List<Boolean> validationResult = new ArrayList<>();
        if (PasswordRulesUtil.MINIMUM_LENGTH_ENABLE)
        {
            validationResult.add(passwordRulesConfig.validateMinimumLength(user.getPassword(), PasswordRulesUtil.MINIMUM_LENGTH));
        }
        if (PasswordRulesUtil.MAXIMUM_LENGTH_ENABLE)
        {
            validationResult.add(passwordRulesConfig.validateMaximumLength(user.getPassword(), PasswordRulesUtil.MAXIMUM_LENGTH));
        }
        if (PasswordRulesUtil.MINIMUM_LOWER_CASE_ENABLE)
        {
            validationResult.add(passwordRulesConfig.validateMinimumLowerCase(user.getPassword(), PasswordRulesUtil.MINIMUM_LOWER_CASE_LENGTH));
        }
        if (PasswordRulesUtil.MINIMUM_UPPER_CASE_ENABLE)
        {
            validationResult.add(passwordRulesConfig.validateMinimumUpperCase(user.getPassword(), PasswordRulesUtil.MINIMUM_UPPER_CASE_LENGTH));
        }
        if (PasswordRulesUtil.MINIMUM_SYMBOL_SPECIAL_ENABLE)
        {
            validationResult.add(passwordRulesConfig.validateMinimumSymbolsSpecialCharacters(user.getPassword(), PasswordRulesUtil.MINIMUM_SYMBOL_SPECIAL_LENGTH));
        }
        if (PasswordRulesUtil.NOT_ALLOWED_CHARACTER_ENABLE)
        {
            validationResult.add(passwordRulesConfig.checkNotAllowedCharacters(user.getPassword(), PasswordRulesUtil.NOT_ALLOWED_CHARACTER_REGEX));
        }
        if (validationResult.contains(false))
        {
            flag = false;
        }
        return flag;
    }
}
