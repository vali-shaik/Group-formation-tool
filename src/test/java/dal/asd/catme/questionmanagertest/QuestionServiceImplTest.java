package dal.asd.catme.questionmanagertest;

import dal.asd.catme.questionmanager.*;

import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//import org.junit.jupiter.api.Test;


public class QuestionServiceImplTest
{
    Connection con;

    @Test
    public void checkQuestionExists()
    {
        IQuestionDao questionDaoObj = new QuestionDaoMock(formQuestions());
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
        IQuestionDao questionDaoObj = new QuestionDaoMock(formQuestions());
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


    public ArrayList<IQuestionTitle> formQuestions()
    {
        List<IQuestionTitle> listOfQuestions = new ArrayList<>();

        IQuestionTitle questionTitle1 = new QuestionTitle();
        questionTitle1.setQuestionTitle("JAVA");

        IQuestion q1 = new Question();
        IQuestion q2 = new Question();

        q1.setQuestionId(1);
        q1.setQuestionText("Does JAVA rule?");
        q2.setQuestionId(2);
        q2.setQuestionText("Do Jedi love JAVA?");

        ArrayList<IQuestion> set1 = new ArrayList<>();
        set1.add(q1);
        set1.add(q2);

        questionTitle1.setQuestions(set1);


        IQuestionTitle questionTitle2 = new QuestionTitle();
        questionTitle2.setQuestionTitle("C++");

        IQuestion q3 = new Question();
        IQuestion q4 = new Question();

        q3.setQuestionId(3);
        q3.setQuestionText("Does C++ rule?");
        q4.setQuestionId(4);
        q4.setQuestionText("Do Jedi love C++?");
        ArrayList<IQuestion> set2 = new ArrayList<>();
        set2.add(q1);
        set2.add(q2);

        questionTitle2.setQuestions(set2);


        listOfQuestions.add(questionTitle1);
        listOfQuestions.add(questionTitle2);

        return (ArrayList<IQuestionTitle>) listOfQuestions;

    }

}
