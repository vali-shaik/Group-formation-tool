package dal.asd.catme.questionmanager;

public interface IQuestionManagerModelAbstractFactory
{
    IQuestion makeQuestion();
    IOption makeOption();
    IQuestionTitle makeQuestionTitle();
}
