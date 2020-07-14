package dal.asd.catme.questionmanager;

import java.util.List;

public interface IQuestionManagerService
{
    int createQuestion(IQuestion question, String user);

    String findQuestionType(IQuestion question, String user);

    int createOptions(int questionId, List<IOption> options);

    int deleteQuestion(int questionId);

}
