package dal.asd.catme.studentlistimport;

import dal.asd.catme.beans.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

import static org.junit.jupiter.api.Assertions.*;

public class CSVReaderTest
{
    @Test
    public void readFileTest()
    {
        CSVReader csvReader = new CSVReader();

        ArrayList<Student> test = createDataList();

        try
        {
            assertThat(test,samePropertyValuesAs(csvReader.readFile("src/test/java/dal/asd/catme/studentlistimport/test.csv")));
        } catch (Exception e)
        {
            fail();
        }

        try
        {
            csvReader.readFile("src/test/java/dal/asd/catme/studentlistimport/test1.csv");
            fail();
        } catch (Exception e)
        {
        }

    }

    @Test
    void validBannerIdTest()
    {
        System.out.println("Validating BannerID");
        CSVReader csvReader = new CSVReader();

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

        CSVReader csvReader = new CSVReader();

        assertTrue(csvReader.validNames("A","A"));
        assertTrue(csvReader.validNames("1","A"));
        assertTrue(csvReader.validNames("A","1"));

        assertFalse(csvReader.validNames("",""));
        assertFalse(csvReader.validNames("name",""));
        assertFalse(csvReader.validNames("","name"));
        assertFalse(csvReader.validNames(null,null));

    }
    @Test
    void validEmailIdTest()
    {
        System.out.println("Validating Email ID");
        CSVReader csvReader = new CSVReader();

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

        list.add(new Student("B00851820","Prajapati","Tapan","tp890953@dal.ca"));
        list.add(new Student("B00121212","Test","Test","test@test.com"));
        list.add(new Student("B00851542","  Test1","Test1   "," test1@test1.com"));

        return list;
    }
}
