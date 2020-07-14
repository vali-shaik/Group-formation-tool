package dal.asd.catme.questionmanager;

public class QuestionManagerAbstractFactoryImpl implements IQuestionManagerAbstractFactory
{
    private IQuestionDao questionDao;
    private IQuestionManagerService questionManagerService;
    private IListQuestionsService listQuestionsService;

    public QuestionManagerAbstractFactoryImpl()
    {
        questionDao = new QuestionDaoImpl();
        questionManagerService = new QuestionManagerServiceImpl(questionDao);
        listQuestionsService = new ListQuestionsServiceImpl(questionDao);
    }

    @Override
    public IQuestionDao makeQuestionDao()
    {
        return questionDao;
    }

    @Override
    public IQuestionManagerService makeQuestionManagerService()
    {
        return questionManagerService;
    }

    @Override
    public IListQuestionsService makeListQuestionsService()
    {
        return listQuestionsService;
    }
}
