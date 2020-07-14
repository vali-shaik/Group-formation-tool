package dal.asd.catme.questionmanager;

public class QuestionManagerModelAbstractFactoryImpl implements IQuestionManagerModelAbstractFactory
{
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
