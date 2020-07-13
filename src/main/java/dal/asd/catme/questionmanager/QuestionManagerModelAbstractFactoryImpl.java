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
    public IQuestion makeQuestion()
    {
        return new Question();
    }

    @Override
    public IOption makeOption()
    {
        return new Option();
    }

    @Override
    public IQuestionTitle makeQuestionTitle()
    {
        return new QuestionTitle();
    }
}
