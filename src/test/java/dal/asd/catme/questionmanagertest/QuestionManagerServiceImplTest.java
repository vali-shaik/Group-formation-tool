package dal.asd.catme.questionmanagertest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.questionmanager.*;
import dal.asd.catme.util.CatmeUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class QuestionManagerServiceImplTest
{
    Option option;
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    IQuestionManagerAbstractFactory questionManagerAbstractFactory = baseAbstractFactory.makeQuestionManagerAbstractFactory();
    IQuestionManagerModelAbstractFactory modelAbstractFactory = baseAbstractFactory.makeQuestionManagerModelAbstractFactory();

    @Test
    public void createQuestionTest()
    {
        IQuestionDao questionDaoMock = questionManagerAbstractFactory.makeQuestionDao();

        List<Option> options = formQuestionOptions();
        Question question = modelAbstractFactory.makeQuestion();
        question.setQuestionText("Which Framework?");
        question.setQuestionType("Free Text");
        question.setQuestionTitle("UI");
        question.setOptionWithOrder(options);
        question.setCreatedDate(new Date());
        assertEquals(CatmeUtil.ONE, questionDaoMock.createQuestion(question, CatmeUtil.USER_ROLE_ID));
    }

    @Test
    public void createQuestionTitle()
    {
        IQuestionDao questionDaoMock = questionManagerAbstractFactory.makeQuestionDao();
        assertEquals(CatmeUtil.ONE, questionDaoMock.createQuestionTitle(CatmeUtil.QUESTION_TITLE, CatmeUtil.USER_ROLE_ID));
    }

    @Test
    public void createOptions()
    {
        IQuestionDao questionDaoMock = questionManagerAbstractFactory.makeQuestionDao();
        List<Option> options = formQuestionOptions();
        assertEquals(CatmeUtil.ONE, questionDaoMock.createOptions(CatmeUtil.QUESTION_ID, options));
    }

    private List<Option> formQuestionOptions()
    {
        List<Option> options = new ArrayList<>();
        option = modelAbstractFactory.makeOption();
        option.setDisplayText("Angular");
        option.setStoredAs(1);
        options.add(option);
        return options;

    }

}
