package dal.asd.catme.questionmanager;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.util.CatmeUtil;

import java.util.ArrayList;
import java.util.List;

public class QuestionManagerServiceImpl implements IQuestionManagerService
{
    IQuestionDao questionDao;
    IQuestionManagerModelAbstractFactory modelAbstractFactory = BaseAbstractFactoryImpl.instance().makeQuestionManagerModelAbstractFactory();

    public QuestionManagerServiceImpl(IQuestionDao questionDao)
    {
        this.questionDao = questionDao;
    }

    @Override
    public String findQuestionType(IQuestion question, String user)
    {
        if (question.getQuestionType().equalsIgnoreCase(CatmeUtil.FREE_TEXT) || question.getQuestionType().equalsIgnoreCase(CatmeUtil.NUMERIC))
        {
            int result = createQuestion(question, user);
            if (result > 0)
            {
                return CatmeUtil.QUESTION_CREATION_SUCCESS;
            } else
            {
                return CatmeUtil.QUESTION_FAILURE_PAGE;
            }
        } else
        {
            List<IOption> options = new ArrayList<>();
            for (int i = 1; i <= 5; i++)
            {
                IOption option = modelAbstractFactory.makeOption();
                option.setStoredAs(i);
                options.add(option);
            }
            question.setOptionWithOrder(options);
            return CatmeUtil.OPTION_EDITOR;
        }
    }

    @Override
    public int createQuestion(IQuestion question, String user)
    {
        return questionDao.createQuestion(question, user);
    }

    @Override
    public int createOptions(int questionId, List<IOption> options)
    {
        return questionDao.createOptions(questionId, options);
    }

    @Override
    public int deleteQuestion(int questionId)
    {
        return questionDao.deleteQuestion(questionId);
    }
}