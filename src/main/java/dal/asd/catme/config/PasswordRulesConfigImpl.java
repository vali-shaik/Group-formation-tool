package dal.asd.catme.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.crypto.password.PasswordEncoder;

import dal.asd.catme.beans.User;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.exception.CatmeException;
import dal.asd.catme.util.PasswordRulesUtil;

public class PasswordRulesConfigImpl implements IPasswordRulesConfig {

	DatabaseAccess databaseAccess;

	PasswordEncoder passwordEncoder;


	boolean regularExpressionMatcher(String currentPassword,String regularExpression)
	{
		Pattern pattern = Pattern.compile(regularExpression);
		Matcher matcher = pattern.matcher(currentPassword);
		return matcher.find();
	}

	@Override
	public boolean validateMinimumLength(String currentPassword, int minimumLength) {

		Boolean flag=true;
		if(currentPassword!=null) 
		{
			if(currentPassword.trim().length()<minimumLength)
			{
				flag=false;
			}
		}
		return flag;
	}

	@Override
	public boolean validateMaximumLength(String currentPassword, int maximumLength) {
		boolean flag=true;
		if(currentPassword!=null) 
		{
			if(currentPassword.trim().length()>maximumLength)
			{
				flag=false;
			}
		}
		return flag;
	}

	@Override
	public boolean validateMinimumUpperCase(String currentPassword, int mininmumUpperLength) {

		Boolean flag=true;
		String minimumUpperCaseRegEx=PasswordRulesUtil.MINIMUM_UPPER_CASE_REGEX+"{"+mininmumUpperLength+"}";
		if(currentPassword!=null) 
		{
			if(!regularExpressionMatcher(currentPassword,minimumUpperCaseRegEx))
			{
				flag=false;
			}
		}
		return flag;
	}

	@Override
	public boolean validateMinimumLowerCase(String currentPassword, int mininmumLowerLength) {
		boolean flag=true;
		String minimumLowerCaseRegEx=PasswordRulesUtil.MINIMUM_LOWER_CASE_REGEX+"{"+mininmumLowerLength+"}";
		if(currentPassword!=null) 
		{
			if(!regularExpressionMatcher(currentPassword,minimumLowerCaseRegEx))
			{
				flag=false;
			}
		}
		return flag;
	}

	@Override
	public boolean validateMinimumSymbolsSpecialCharacters(String currentPassword, int minimumSymbolSpecialLength) {
		boolean flag=true;
		String minimumSymbolSpecialRegEx=PasswordRulesUtil.MINIMUM_SYMBOL_SPECIAL_REGEX+"{"+minimumSymbolSpecialLength+"}";
		if(currentPassword!=null) 
		{
			if(!regularExpressionMatcher(currentPassword,minimumSymbolSpecialRegEx))
			{
				flag=false;
			}
		}
		return flag;
	}

	@Override
	public boolean checkNotAllowedCharacters(String currentPassword, String regExpression) {
		boolean flag=true;
		String notAllowedCharactersRegEx=PasswordRulesUtil.NOT_ALLOWED_CHARACTER_REGEX;
		if(currentPassword!=null) 
		{
			if(regularExpressionMatcher(currentPassword,notAllowedCharactersRegEx))
			{
				flag=false;
			}
		}
		return flag;
	}

}
