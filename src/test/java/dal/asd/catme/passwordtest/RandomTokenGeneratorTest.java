package dal.asd.catme.passwordtest;

import static dal.asd.catme.accesscontrol.MailSenderUtil.TOKEN_LENGTH;

import org.junit.jupiter.api.Test;

import dal.asd.catme.util.RandomTokenGenerator;

class RandomPasswordGeneratorTest
{

    @Test
    void generateRandomPassword()
    {
        System.out.println(RandomTokenGenerator.generateRandomPassword(TOKEN_LENGTH));
    }
}