package dal.asd.catme.questionmanagertest;

import dal.asd.catme.questionmanager.Option;
import dal.asd.catme.questionmanager.Question;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionTest
{
    @Test
    public void getQuestionIdTest()
    {
        Question question = new Question();

        question.setQuestionId(1);
        assertEquals(question.getQuestionId(),1);
    }

    @Test
    public void setQuestionIdTest()
    {
        Question question = new Question();

        question.setQuestionId(1);
        assertEquals(question.getQuestionId(),1);
    }

    @Test
    public void getQuestionTextTest()
    {
        Question question = new Question();

        question.setQuestionText("Text");
        assertEquals(question.getQuestionText(),"Text");
    }

    @Test
    public void setQuestionTextTest()
    {
        Question question = new Question();

        question.setQuestionText("Text");
        assertEquals(question.getQuestionText(),"Text");
    }

    @Test
    public void getQuestionTypeTest()
    {
        Question question = new Question();

        question.setQuestionType("NUMERIC");
        assertEquals(question.getQuestionType(),"NUMERIC");
    }

    @Test
    public void setQuestionTypeTest()
    {
        Question question = new Question();

        question.setQuestionType("NUMERIC");
        assertEquals(question.getQuestionType(),"NUMERIC");
    }

    @Test
    public void getQuestionTitleTest()
    {
        Question question = new Question();

        question.setQuestionTitle("Title");
        assertEquals(question.getQuestionTitle(),"Title");
    }

    @Test
    public void setQuestionTitleTest()
    {
        Question question = new Question();

        question.setQuestionTitle("Title");
        assertEquals(question.getQuestionTitle(),"Title");
    }

    @Test
    public void getOptionWithOrderTest()
    {
        Question question = new Question();

        List<Option> list = listOption();
        question.setOptionWithOrder(list);
        assertEquals(question.getOptionWithOrder(),list);
    }

    @Test
    public void setOptionWithOrderTest()
    {
        Question question = new Question();

        List<Option> list = listOption();
        question.setOptionWithOrder(list);
        assertEquals(question.getOptionWithOrder(),list);
    }

    @Test
    public void getCreatedDateTest()
    {
        Question question = new Question();

        Date date = new Date(2020,7,12);
        question.setCreatedDate(date);
        assertEquals(question.getCreatedDate(),date);
    }

    @Test
    public void setCreatedDateTest()
    {
        Question question = new Question();

        Date date = new Date(2020,7,12);
        question.setCreatedDate(date);
        assertEquals(question.getCreatedDate(),date);
    }

    private List<Option> listOption()
    {
        List<Option> list = new ArrayList<>();

        Option o = new Option("Option",1);
        list.add(o);

        return list;
    }
}
