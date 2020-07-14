package dal.asd.catme.questionmanagertest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.questionmanager.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionTitleTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    IQuestionManagerModelAbstractFactory modelAbstractFactory = baseAbstractFactory.makeQuestionManagerModelAbstractFactory();

    @Test
    public void getQuestionTitleTest()
    {
        IQuestionTitle questionTitle = modelAbstractFactory.makeQuestionTitle();
        questionTitle.setQuestionTitle("Title");

        assertEquals(questionTitle.getQuestionTitle(), "Title");
    }

    @Test
    public void setQuestionTitleTest()
    {
        IQuestionTitle questionTitle = new QuestionTitle();
        questionTitle.setQuestionTitle("Title");

        questionTitle.setQuestionTitle("Title");
        assertEquals(questionTitle.getQuestionTitle(), "Title");
    }

    @Test
    public void getQuestionsTest()
    {
        IQuestionTitle questionTitle = new QuestionTitle();

        List<IQuestion> list = listQuestion();
        questionTitle.setQuestions(list);

        assertEquals(questionTitle.getQuestions(), list);
    }

    @Test
    public void setQuestionsTest()
    {
        IQuestionTitle questionTitle = new QuestionTitle();

        List<IQuestion> list = listQuestion();
        questionTitle.setQuestions(list);

        assertEquals(questionTitle.getQuestions(), list);
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
