package dal.asd.catme.util;

import org.junit.jupiter.api.Test;

import static dal.asd.catme.util.MailSenderUtil.TOKEN_LENGTH;

class RandomPasswordGeneratorTest
{

    @Test
    void generateRandomPassword()
    {
        System.out.println(RandomTokenGenerator.generateRandomPassword(TOKEN_LENGTH));
    }
}