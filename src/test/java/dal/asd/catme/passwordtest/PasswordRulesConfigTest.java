package dal.asd.catme.passwordtest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordRulesConfigTest
{

    PassordRulesConfigMock passwordRulesConfig = new PassordRulesConfigMock();

    @Test
    public void testvalidateMinimumLength()
    {
        assertEquals(passwordRulesConfig.validateMinimumLength("PASSWORD", 8), true);
        assertEquals(passwordRulesConfig.validateMinimumLength("PASS", 8), false);
    }

    @Test
    public void testvalidateMaximumLength()
    {
        assertEquals(passwordRulesConfig.validateMaximumLength("PASSWORD", 10), true);
        assertEquals(passwordRulesConfig.validateMaximumLength("PASSWORD1234", 10), false);
    }

    @Test
    public void testvalidateMinimumLowerCase()
    {
        assertEquals(passwordRulesConfig.validateMinimumLowerCase("PassWORD", 3), true);
        assertEquals(passwordRulesConfig.validateMinimumLowerCase("PASSWOrd", 3), false);
    }

    @Test
    public void testvalidateMinimumUpperCase()
    {
        assertEquals(passwordRulesConfig.validateMinimumUpperCase("PassWORD", 3), true);
        assertEquals(passwordRulesConfig.validateMinimumUpperCase("PassworD", 3), false);
    }

    @Test
    public void testvalidateMinimumSymbolsSpecialCharacters()
    {
        assertEquals(passwordRulesConfig.validateMinimumSymbolsSpecialCharacters("P&as#sW**ORD%", 3), true);
        assertEquals(passwordRulesConfig.validateMinimumSymbolsSpecialCharacters("#PassworD&", 3), false);
    }

    @Test
    public void testcheckNotAllowedCharacters()
    {
        //Not allowed= [x y z]
        assertEquals(passwordRulesConfig.checkNotAllowedCharacters("P&as#sW**ORD%", "[xyz]"), true);
        assertEquals(passwordRulesConfig.checkNotAllowedCharacters("#PassworD&", "[xyz]"), true);
        assertEquals(passwordRulesConfig.checkNotAllowedCharacters("#PassworD&x", "[xyz]"), false);
    }

    @Test
    public void testregularExpressionMatcher()
    {
        assertEquals(passwordRulesConfig.regularExpressionMatcher("P&as#sW**ORD%", "[xyz]"), false);
        assertEquals(passwordRulesConfig.regularExpressionMatcher("P&as#sWxyz", "[xyz]"), true);
    }

}
