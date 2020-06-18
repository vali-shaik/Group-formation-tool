package dal.asd.catme.service;

import dal.asd.catme.beans.User;
import dal.asd.catme.config.IPasswordRulesConfig;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.exception.CatmeException;
import dal.asd.catme.util.PasswordRulesUtil;

import java.util.ArrayList;
import java.util.List;

public class PasswordPolicyCheckerImpl implements IPasswordPolicyCheckerService
{
    IPasswordRulesConfig passwordRulesConfig;
    @Override
    public boolean enforcePasswordPolicy(User user) throws CatmeException
    {
        boolean flag=true;
        passwordRulesConfig= SystemConfig.instance().getPasswordEnforcementConfig();
        List<Boolean> validationResult=new ArrayList<>();
        if(PasswordRulesUtil.MINIMUM_LENGTH_ENABLE)
        {
            validationResult.add(passwordRulesConfig.validateMinimumLength(user.getPassword(), PasswordRulesUtil.MINIMUM_LENGTH));
        }
        if(PasswordRulesUtil.MAXIMUM_LENGTH_ENABLE)
        {
            validationResult.add(passwordRulesConfig.validateMaximumLength(user.getPassword(), PasswordRulesUtil.MAXIMUM_LENGTH));
            System.out.println("min length: "+validationResult.get(validationResult.size()-1));
        }
        if(PasswordRulesUtil.MINIMUM_LOWER_CASE_ENABLE)
        {
            validationResult.add(passwordRulesConfig.validateMinimumLowerCase(user.getPassword(), PasswordRulesUtil.MINIMUM_LOWER_CASE_LENGTH));
            System.out.println("min lower: "+validationResult.get(validationResult.size()-1));
        }
        if(PasswordRulesUtil.MINIMUM_UPPER_CASE_ENABLE)
        {
            validationResult.add(passwordRulesConfig.validateMinimumUpperCase(user.getPassword(), PasswordRulesUtil.MINIMUM_UPPER_CASE_LENGTH));
            System.out.println("min upper: "+validationResult.get(validationResult.size()-1));
        }
        if(PasswordRulesUtil.MINIMUM_SYMBOL_SPECIAL_ENABLE)
        {
            validationResult.add(passwordRulesConfig.validateMinimumSymbolsSpecialCharacters(user.getPassword(), PasswordRulesUtil.MINIMUM_SYMBOL_SPECIAL_LENGTH));
            System.out.println("min symbol: "+validationResult.get(validationResult.size()-1));
        }
        if(PasswordRulesUtil.NOT_ALLOWED_CHARACTER_ENABLE)
        {
            validationResult.add(passwordRulesConfig.checkNotAllowedCharacters(user.getPassword(), PasswordRulesUtil.NOT_ALLOWED_CHARACTER_REGEX));
            System.out.println("not allowed: "+validationResult.get(validationResult.size()-1));
        }
        if(validationResult.contains(false))
        {
            flag=false;
        }
        return flag;
    }
}
