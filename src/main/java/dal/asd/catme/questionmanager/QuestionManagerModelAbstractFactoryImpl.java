package dal.asd.catme.questionmanager;

public class QuestionManagerModelAbstractFactoryImpl implements IQuestionManagerModelAbstractFactory
{
    private static IQuestionManagerModelAbstractFactory modelAbstractFactory = null;

    public static IQuestionManagerModelAbstractFactory instance()
    {
        if(modelAbstractFactory==null)
        {
            modelAbstractFactory = new QuestionManagerModelAbstractFactoryImpl();
        }
        return modelAbstractFactory;
    }

    @Override
    public IQuestion createQuestion()
    {
        return new Question();
    }

    @Override
    public IOption createOption()
    {
        return new Option();
    }

    @Override
    public IQuestionTitle createQuestionTitle()
    {
        return new QuestionTitle();
    }
}
