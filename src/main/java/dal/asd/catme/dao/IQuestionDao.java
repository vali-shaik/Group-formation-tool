package dal.asd.catme.dao;

import dal.asd.catme.beans.Option;
import dal.asd.catme.beans.Question;
import dal.asd.catme.exception.QuestionDatabaseException;

import java.sql.Connection;
import java.util.List;

public interface IQuestionDao
{
    public List<Question> getQuestionTitles(String instructor) throws QuestionDatabaseException;

    public int deleteQuestion(int questionId);

    public int checkExistingQuestion(int questionId, Connection con);

    public int createQuestion(Question question, String user);

    public int createQuestionTitle(String questionTitle, String user);

    public int createOptions(int questionId, List<Option> options);
}


