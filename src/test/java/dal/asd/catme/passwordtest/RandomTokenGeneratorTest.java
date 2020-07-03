package dal.asd.catme.passwordtest;

import org.junit.jupiter.api.Test;

import dal.asd.catme.util.RandomTokenGenerator;

import static dal.asd.catme.util.MailSenderUtil.TOKEN_LENGTH;

class RandomPasswordGeneratorTest
{

    @Test
    void generateRandomPassword()
    {
        System.out.println(RandomTokenGenerator.generateRandomPassword(TOKEN_LENGTH));
    }
}