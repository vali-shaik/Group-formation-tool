package dal.asd.catme.studentlistimporttest;

import org.junit.jupiter.api.Test;

import dal.asd.catme.accesscontrol.Student;
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
        ICSVReader csvReader = new CSVReader();
        File file1 = new File("src/test/java/dal/asd/catme/studentlistimporttest/test.csv");
        File file2 = new File("src/test/java/dal/asd/catme/studentlistimporttest/test1.csv");
        try
        {
            assertNotNull(csvReader.readFile(new FileInputStream(file1)));
        } catch (Exception e)
        {
            fail();
        }

        try
        {
            csvReader.readFile(new FileInputStream(file2));
            fail();
        } catch (Exception e)
        {
        }

    }

    @Test
    void validBannerIdTest()
    {
        ICSVReader csvReader = new CSVReader();

        assertTrue(csvReader.validBannerId("B00851820"));
        assertTrue(csvReader.validBannerId("B00000000"));
        assertTrue(csvReader.validBannerId("b00123455"));

        assertFalse(csvReader.validBannerId("a00000000"));
        assertFalse(csvReader.validBannerId(""));
        assertFalse(csvReader.validBannerId("B10000000"));
        assertFalse(csvReader.validBannerId("B1000000"));
        assertFalse(csvReader.validBannerId("B00000a0"));
    }

    @Test
    void validNamesTest()
    {
        System.out.println("Validating Names");

        ICSVReader csvReader = new CSVReader();

        assertTrue(csvReader.validNames("A", "A"));
        assertTrue(csvReader.validNames("1", "A"));
        assertTrue(csvReader.validNames("A", "1"));

        assertFalse(csvReader.validNames("", ""));
        assertFalse(csvReader.validNames("name", ""));
        assertFalse(csvReader.validNames("", "name"));
        assertFalse(csvReader.validNames(null, null));

    }

    @Test
    void validEmailIdTest()
    {
        ICSVReader csvReader = new CSVReader();

        assertTrue(csvReader.validEmailId("tp890953@dal.ca"));
        assertTrue(csvReader.validEmailId("Tapan.Prajapati@dal.ca"));
        assertTrue(csvReader.validEmailId("tapan.prajapati24598@gmail.com"));
        assertTrue(csvReader.validEmailId("tapan_prajapati24598@gmail.com"));

        assertFalse(csvReader.validEmailId("_asdf.@gmail.com"));
        assertFalse(csvReader.validEmailId("@@gmail"));
        assertFalse(csvReader.validEmailId("abc@234"));
    }

    ArrayList<Student> createDataList()
    {
        ArrayList<Student> list = new ArrayList<>();

        list.add(new Student("B00851820", "Prajapati", "Tapan", "tp890953@dal.ca"));
        list.add(new Student("B00121212", "Test", "Test", "test@test.com"));
        list.add(new Student("B00851542", "  Test1", "Test1   ", " test1@test1.com"));

        return list;
    }
}
