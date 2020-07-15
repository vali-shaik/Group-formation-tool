package dal.asd.catme.password;

public interface IPasswordRulesConfig
{

    boolean validateMinimumLength(String currentPassword, int minimumLength);

    boolean validateMaximumLength(String currentPassword, int maximumLength);

    boolean validateMinimumUpperCase(String currentPassword, int mininmumUpperLength);

    boolean validateMinimumLowerCase(String currentPassword, int mininmumLowerLength);

    boolean validateMinimumSymbolsSpecialCharacters(String currentPassword, int minimumSymbolSpecialLength);

    boolean checkNotAllowedCharacters(String currentPassword, String regExpression);


}
