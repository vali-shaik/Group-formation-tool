package dal.asd.catme.questionmanagertest;

import dal.asd.catme.questionmanager.Option;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OptionTest
{
    @Test
    public void getDisplayTextTest()
    {
        Option option = new Option("ABCD",1);

        assertEquals(option.getDisplayText(),"ABCD");
    }

    @Test
    public void setDisplayTextTest()
    {
        Option option = new Option();
        option.setDisplayText("ABCD");

        assertEquals(option.getDisplayText(),"ABCD");
    }

    @Test
    public void getStoreAsTest()
    {
        Option option = new Option(1);

        assertEquals(option.getStoredAs(),1);
    }

    @Test
    public void setStoreAsTest()
    {
        Option option = new Option();
        option.setStoredAs(1);

        assertEquals(option.getStoredAs(),1);
    }
}
