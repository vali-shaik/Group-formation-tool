package dal.asd.catme.questionmanagertest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.questionmanager.*;

import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class QuestionServiceImplTest
{
    Connection con;

    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    IQuestionManagerAbstractFactory questionManagerAbstractFactory = baseAbstractFactory.makeQuestionManagerAbstractFactory();
    IQuestionManagerModelAbstractFactory modelAbstractFactory = baseAbstractFactory.makeQuestionManagerModelAbstractFactory();

    @Test
    public void checkQuestionExists()
    {
        IQuestionDao questionDaoObj = questionManagerAbstractFactory.makeQuestionDao();
        try
        {
            assertEquals(1, (questionDaoObj.checkExistingQuestion(1, con)));

        } catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }

        try
        {
            assertEquals(0, (questionDaoObj.checkExistingQuestion(7, con)));

        } catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void deleteQuestion()
    {
        IQuestionDao questionDaoObj = questionManagerAbstractFactory.makeQuestionDao();
        try
        {
            assertEquals(1, (questionDaoObj.deleteQuestion(1)));

        } catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }

        try
        {
            assertEquals(0, (questionDaoObj.deleteQuestion(7)));

        } catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }
    }

}
