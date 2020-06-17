package dal.asd.catme.dao;

import dal.asd.catme.beans.Instructor;
import dal.asd.catme.beans.QuestionTitle;
import dal.asd.catme.exception.QuestionDatabaseException;

import java.util.List;

public interface IQuestionDao
{
    public List<QuestionTitle> getQuestionTitles(String instructor) throws QuestionDatabaseException;
}
