package dal.asd.catme.questionmanager;

public interface IQuestionManagerModelAbstractFactory
{
    IQuestion createQuestion();
    IOption createOption();
    IQuestionTitle createQuestionTitle();
}
