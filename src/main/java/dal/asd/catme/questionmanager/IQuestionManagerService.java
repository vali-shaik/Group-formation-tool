package dal.asd.catme.questionmanager;

import java.util.List;

public interface IQuestionManagerService
{
    public int createQuestion(Question question, String user);

    public String findQuestionType(Question question, String user);

    public int createOptions(int questionId, List<Option> options);

    public int deleteQuestion(int questionId);

}
