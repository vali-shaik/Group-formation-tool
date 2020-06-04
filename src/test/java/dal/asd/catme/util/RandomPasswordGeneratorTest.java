package dal.asd.catme.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomPasswordGeneratorTest
{

    @Test
    void generateRandomPassword()
    {
        System.out.println(RandomPasswordGenerator.generateRandomPassword(CatmeUtil.RANDOM_PASSWORD_LENGTH));
    }
}