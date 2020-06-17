package dal.asd.catme.service;

import dal.asd.catme.beans.Instructor;
import dal.asd.catme.dao.QuestionDaoMock;
import dal.asd.catme.exception.QuestionDatabaseException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListQuestionsServiceTest
{
    @Test
    void getQuestionsTest()
    {

        IListQuestionsService listQuestionsService = new ListQuestionsServiceImpl(new QuestionDaoMock());

        try
        {
            listQuestionsService.getQuestions("instructor");

        } catch (QuestionDatabaseException e)
        {
            fail();
            e.printStackTrace();
        }

        try
        {
            listQuestionsService.getQuestions(null);
            fail();
        }
        catch (QuestionDatabaseException e)
        {

        }
    }
}
