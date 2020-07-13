package dal.asd.catme.questionmanager;

import java.util.List;

public interface IQuestionManagerService
{
    public int createQuestion(IQuestion question, String user);

    public String findQuestionType(IQuestion question, String user);

    public int createOptions(int questionId, List<IOption> options);

    public int deleteQuestion(int questionId);

}