package dal.asd.catme.questionmanager;

import java.sql.Connection;
import java.util.List;

public interface IQuestionDao
{
    List<Question> getQuestionTitles(String instructor) throws QuestionDatabaseException;

    int deleteQuestion(int questionId);

    int checkExistingQuestion(int questionId, Connection con);

    int createQuestion(Question question, String user);

    int createQuestionTitle(String questionTitle, String user);

    int createOptions(int questionId, List<Option> options);
}
