package dal.asd.catme.studentlistimporttest;

import dal.asd.catme.studentlistimport.CSVParserAbstractFactoryImpl;
import dal.asd.catme.studentlistimport.CSVReader;
import dal.asd.catme.studentlistimport.ICSVParser;
import dal.asd.catme.studentlistimport.ICSVReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CSVParserTest
{

    @Test
    void validBannerIdTest()
    {
        ICSVParser icsvParser= CSVParserAbstractFactoryImpl.instance().getCSVParser();

        assertTrue(icsvParser.validBannerId("B00851820"));
        assertTrue(icsvParser.validBannerId("B00000000"));
        assertTrue(icsvParser.validBannerId("b00123455"));

        assertFalse(icsvParser.validBannerId("a00000000"));
        assertFalse(icsvParser.validBannerId(""));
        assertFalse(icsvParser.validBannerId("B10000000"));
        assertFalse(icsvParser.validBannerId("B1000000"));
        assertFalse(icsvParser.validBannerId("B00000a0"));
    }

    @Test
    void validNamesTest()
    {
        System.out.println("Validating Names");

        ICSVParser icsvParser= CSVParserAbstractFactoryImpl.instance().getCSVParser();

        assertTrue(icsvParser.validNames("A", "A"));
        assertTrue(icsvParser.validNames("1", "A"));
        assertTrue(icsvParser.validNames("A", "1"));

        assertFalse(icsvParser.validNames("", ""));
        assertFalse(icsvParser.validNames("name", ""));
        assertFalse(icsvParser.validNames("", "name"));
        assertFalse(icsvParser.validNames(null, null));

    }

    @Test
    void validEmailIdTest()
    {
        ICSVParser icsvParser= CSVParserAbstractFactoryImpl.instance().getCSVParser();

        assertTrue(icsvParser.validEmailId("tp890953@dal.ca"));
        assertTrue(icsvParser.validEmailId("Tapan.Prajapati@dal.ca"));
        assertTrue(icsvParser.validEmailId("tapan.prajapati24598@gmail.com"));
        assertTrue(icsvParser.validEmailId("tapan_prajapati24598@gmail.com"));

        assertFalse(icsvParser.validEmailId("_asdf.@gmail.com"));
        assertFalse(icsvParser.validEmailId("@@gmail"));
        assertFalse(icsvParser.validEmailId("abc@234"));
    }
}
