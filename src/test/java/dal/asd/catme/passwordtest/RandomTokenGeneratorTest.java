package dal.asd.catme.passwordtest;

import static dal.asd.catme.accesscontrol.MailSenderUtil.TOKEN_LENGTH;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import dal.asd.catme.util.RandomTokenGenerator;

class RandomTokenGeneratorTest
{
    @Test
    void generateRandomPasswordTest()
    {
        assertNotNull(RandomTokenGenerator.generateRandomPassword(TOKEN_LENGTH));
    }
}