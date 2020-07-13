package dal.asd.catme.questionmanagertest;

import dal.asd.catme.questionmanager.IQuestion;
import dal.asd.catme.questionmanager.IQuestionTitle;
import dal.asd.catme.questionmanager.Question;
import dal.asd.catme.questionmanager.QuestionTitle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class QuestionTitleTest
{
    @Test
    public void getQuestionTitleTest()
    {
        IQuestionTitle questionTitle = new QuestionTitle("Title");

        assertEquals(questionTitle.getQuestionTitle(),"Title");
    }

    @Test
    public void setQuestionTitleTest()
    {
        IQuestionTitle questionTitle = new QuestionTitle();

        questionTitle.setQuestionTitle("Title");
        assertEquals(questionTitle.getQuestionTitle(),"Title");
    }

    @Test
    public void getQuestionsTest()
    {
        IQuestionTitle questionTitle = new QuestionTitle();

        List<IQuestion> list = listQuestion();
        questionTitle.setQuestions(list);

        assertEquals(questionTitle.getQuestions(),list);
    }

    @Test
    public void setQuestionsTest()
    {
        IQuestionTitle questionTitle = new QuestionTitle();

        List<IQuestion> list = listQuestion();
        questionTitle.setQuestions(list);

        assertEquals(questionTitle.getQuestions(),list);
    }

    private List<IQuestion> listQuestion()
    {
        List<IQuestion> list = new ArrayList<>();

        Question q = new Question();
        q.setQuestionText("Text");

        list.add(q);
        return list;
    }
}
