package dal.asd.catme.questionmanagertest;

import dal.asd.catme.exception.QuestionDatabaseException;
import dal.asd.catme.questionmanager.IListQuestionsService;
import dal.asd.catme.questionmanager.ListQuestionsServiceImpl;
import dal.asd.catme.questionmanager.Question;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

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
        } catch (QuestionDatabaseException e)
        {

        }
    }

    @Test
    void sortByDateTest()
    {
        IListQuestionsService listQuestionsService = new ListQuestionsServiceImpl(new QuestionDaoMock());

        try
        {
            listQuestionsService.getQuestions("instructor");

            List<Question> questionList = listQuestionsService.sortByDate();

            Question q1 = questionList.get(0);
            Question q2 = questionList.get(1);

            //date of question1 comes after date of question2
            if (q1.getCreatedDate().compareTo(q2.getCreatedDate()) >= 0)
            {
                fail();
            }
        } catch (QuestionDatabaseException e)
        {
            fail();
        }
    }

    @Test
    void sortByTitleTest()
    {
        IListQuestionsService listQuestionsService = new ListQuestionsServiceImpl(new QuestionDaoMock());

        try
        {
            listQuestionsService.getQuestions("instructor");

            List<Question> questionList = listQuestionsService.sortByTitle();

            Question q1 = questionList.get(0);
            Question q2 = questionList.get(1);

            //question 1 comes after question2
            if (q1.getQuestionTitle().compareTo(q2.getQuestionTitle()) < 0)
            {
                fail();
            }
        } catch (QuestionDatabaseException e)
        {
            fail();
        }
    }
}
