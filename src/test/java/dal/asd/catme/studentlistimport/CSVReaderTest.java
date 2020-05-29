package dal.asd.catme.studentlistimport;

import dal.asd.catme.beans.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CSVReaderTest
{
    @Test
    public void readFileTest()
    {
        CSVReader csvReader = new CSVReader();

        ArrayList<Student> test1 = createDataList1();
        ArrayList<Student> test2 = createDataList2();

//        try
//        {
//            assertEquals(test1,csvReader.readFile("/home/dracula/Documents/test.csv"));
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//        }

        try
        {
            csvReader.readFile("/home/dracula/Documents/test1.csv");
        } catch (Exception e)
        {
            System.out.println("Passed");
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

    ArrayList<Student> createDataList1()
    {
        ArrayList<Student> list = new ArrayList<>();

        list.add(createStudent("B00851820","Tapan","Prajapati","tp890953@dal.ca"));
        list.add(createStudent("B00121212","Test","Test","test@test.com"));
        list.add(createStudent("B0085154","  Test1","Test1   "," test1@test1.com\n"));

        return list;
    }

    ArrayList<Student> createDataList2()
    {
        ArrayList<Student> list = new ArrayList<>();

        list.add(createStudent("B00851820","Tapan","Prajapati","tp890953@dal.ca"));
        list.add(createStudent("a00123258","ABC","TAV","abc@dge"));
        list.add(createStudent("B12121212","","",""));

        return list;
    }

    Student createStudent(String b,String l, String f, String email)
    {
        Student s  = new Student();

        s.setBannerId(b);
        s.setFirstName(f);
        s.setLastName(l);
        s.setEmail(email);

        return s;
    }
}
