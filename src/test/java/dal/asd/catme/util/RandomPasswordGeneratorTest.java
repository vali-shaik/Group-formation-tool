package dal.asd.catme.util;

import org.junit.jupiter.api.Test;

import static dal.asd.catme.util.MailSenderUtil.RANDOM_PASSWORD_LENGTH;
import static org.junit.jupiter.api.Assertions.*;

class RandomPasswordGeneratorTest
{

    @Test
    void generateRandomPassword()
    {
        System.out.println(RandomPasswordGenerator.generateRandomPassword(RANDOM_PASSWORD_LENGTH));
    }
}