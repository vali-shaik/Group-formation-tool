package dal.asd.catme.dao;

import dal.asd.catme.beans.Question;
import dal.asd.catme.exception.QuestionDatabaseException;

import java.util.List;

public interface IQuestionDao
{
    public List<Question> getQuestionTitles(String instructor) throws QuestionDatabaseException;
}
