package dal.asd.catme.studentlistimporttest;

import dal.asd.catme.accesscontrol.User;
import org.junit.jupiter.api.Test;

import dal.asd.catme.studentlistimport.CSVReader;
import dal.asd.catme.studentlistimport.ICSVReader;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CSVReaderTest
{
    @Test
    public void readFileTest()
    {
        File file1 = new File("src/test/java/dal/asd/catme/studentlistimporttest/test.csv");
        try
        {
            ICSVReader csvReader = new CSVReader(new FileInputStream(file1));
            assertNotNull(csvReader.readFile());
        } catch (Exception e)
        {
            fail();
        }

    }


}
