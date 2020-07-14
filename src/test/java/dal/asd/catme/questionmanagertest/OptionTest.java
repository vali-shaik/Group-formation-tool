package dal.asd.catme.questionmanagertest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.questionmanager.IOption;
import dal.asd.catme.questionmanager.IQuestionManagerModelAbstractFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptionTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    IQuestionManagerModelAbstractFactory modelAbstractFactory = baseAbstractFactory.makeQuestionManagerModelAbstractFactory();

    @Test
    public void getDisplayTextTest()
    {
        IOption option = modelAbstractFactory.makeOption();
        option.setDisplayText("ABCD");

        assertEquals(option.getDisplayText(), "ABCD");
    }

    @Test
    public void setDisplayTextTest()
    {
        IOption option = modelAbstractFactory.makeOption();
        option.setDisplayText("ABCD");

        assertEquals(option.getDisplayText(), "ABCD");
    }

    @Test
    public void getStoreAsTest()
    {
        IOption option = modelAbstractFactory.makeOption();
        option.setStoredAs(1);

        assertEquals(option.getStoredAs(), 1);
    }

    @Test
    public void setStoreAsTest()
    {
        IOption option = modelAbstractFactory.makeOption();
        option.setStoredAs(1);

        assertEquals(option.getStoredAs(), 1);
    }
}
