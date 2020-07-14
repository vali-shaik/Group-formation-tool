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
    IOption option;
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    IQuestionManagerAbstractFactory questionManagerAbstractFactory = baseAbstractFactory.makeQuestionManagerAbstractFactory();
    IQuestionManagerModelAbstractFactory modelAbstractFactory = baseAbstractFactory.makeQuestionManagerModelAbstractFactory();

    @Test
    public void createQuestionTest()
    {
        IQuestionDao questionDaoMock = questionManagerAbstractFactory.makeQuestionDao();

        List<IOption> options = formQuestionOptions();
        Question question = new Question(CatmeUtil.ONE, "Which Framework?", "Free Text", "UI", options, new Date());
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
        List<IOption> options = formQuestionOptions();
        assertEquals(CatmeUtil.ONE, questionDaoMock.createOptions(CatmeUtil.QUESTION_ID, options));
    }

    private List<IOption> formQuestionOptions()
    {
        List<IOption> options = new ArrayList<>();
        option = modelAbstractFactory.makeOption();
        option.setDisplayText("Angular");
        option.setStoredAs(1);
        options.add(option);
        return options;

    }

}
