package dal.asd.catme.questionmanagertest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.questionmanager.IOption;
import dal.asd.catme.questionmanager.IQuestion;
import dal.asd.catme.questionmanager.IQuestionManagerModelAbstractFactory;
import dal.asd.catme.questionmanager.Option;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    IQuestionManagerModelAbstractFactory modelAbstractFactory = baseAbstractFactory.makeQuestionManagerModelAbstractFactory();

    @Test
    public void getQuestionIdTest()
    {
        IQuestion question = modelAbstractFactory.makeQuestion();

        question.setQuestionId(1);
        assertEquals(question.getQuestionId(), 1);
    }

    @Test
    public void setQuestionIdTest()
    {
        IQuestion question = modelAbstractFactory.makeQuestion();

        question.setQuestionId(1);
        assertEquals(question.getQuestionId(), 1);
    }

    @Test
    public void getQuestionTextTest()
    {
        IQuestion question = modelAbstractFactory.makeQuestion();

        question.setQuestionText("Text");
        assertEquals(question.getQuestionText(), "Text");
    }

    @Test
    public void setQuestionTextTest()
    {
        IQuestion question = modelAbstractFactory.makeQuestion();

        question.setQuestionText("Text");
        assertEquals(question.getQuestionText(), "Text");
    }

    @Test
    public void getQuestionTypeTest()
    {
        IQuestion question = modelAbstractFactory.makeQuestion();

        question.setQuestionType("NUMERIC");
        assertEquals(question.getQuestionType(), "NUMERIC");
    }

    @Test
    public void setQuestionTypeTest()
    {
        IQuestion question = modelAbstractFactory.makeQuestion();

        question.setQuestionType("NUMERIC");
        assertEquals(question.getQuestionType(), "NUMERIC");
    }

    @Test
    public void getQuestionTitleTest()
    {
        IQuestion question = modelAbstractFactory.makeQuestion();

        question.setQuestionTitle("Title");
        assertEquals(question.getQuestionTitle(), "Title");
    }

    @Test
    public void setQuestionTitleTest()
    {
        IQuestion question = modelAbstractFactory.makeQuestion();

        question.setQuestionTitle("Title");
        assertEquals(question.getQuestionTitle(), "Title");
    }

    @Test
    public void getOptionWithOrderTest()
    {
        IQuestion question = modelAbstractFactory.makeQuestion();

        List<IOption> list = listOption();
        question.setOptionWithOrder(list);
        assertEquals(question.getOptionWithOrder(), list);
    }

    @Test
    public void setOptionWithOrderTest()
    {
        IQuestion question = modelAbstractFactory.makeQuestion();

        List<IOption> list = listOption();
        question.setOptionWithOrder(list);
        assertEquals(question.getOptionWithOrder(), list);
    }

    @Test
    public void getCreatedDateTest()
    {
        IQuestion question = modelAbstractFactory.makeQuestion();

        Date date = new Date(2020, 7, 12);
        question.setCreatedDate(date);
        assertEquals(question.getCreatedDate(), date);
    }

    @Test
    public void setCreatedDateTest()
    {
        IQuestion question = modelAbstractFactory.makeQuestion();

        Date date = new Date(2020, 7, 12);
        question.setCreatedDate(date);
        assertEquals(question.getCreatedDate(), date);
    }

    private List<IOption> listOption()
    {
        List<IOption> list = new ArrayList<>();

        Option o = new Option("Option", 1);
        list.add(o);

        return list;
    }
}
