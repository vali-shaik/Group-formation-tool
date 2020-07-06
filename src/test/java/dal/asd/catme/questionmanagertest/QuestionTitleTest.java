package dal.asd.catme.questionmanagertest;

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
        QuestionTitle questionTitle = new QuestionTitle("Title");

        assertEquals(questionTitle.getQuestionTitle(),"Title");
    }

    @Test
    public void setQuestionTitleTest()
    {
        QuestionTitle questionTitle = new QuestionTitle();

        questionTitle.setQuestionTitle("Title");
        assertEquals(questionTitle.getQuestionTitle(),"Title");
    }

    @Test
    public void getQuestionsTest()
    {
        QuestionTitle questionTitle = new QuestionTitle();

        List<Question> list = listQuestion();
        questionTitle.setQuestions(list);

        assertEquals(questionTitle.getQuestions(),list);
    }

    @Test
    public void setQuestionsTest()
    {
        QuestionTitle questionTitle = new QuestionTitle();

        List<Question> list = listQuestion();
        questionTitle.setQuestions(list);

        assertEquals(questionTitle.getQuestions(),list);
    }

    private List<Question> listQuestion()
    {
        List<Question> list = new ArrayList<>();

        Question q = new Question();
        q.setQuestionText("Text");

        list.add(q);
        return list;
    }
}
