package dal.asd.catme.service;

import java.util.List;
import dal.asd.catme.beans.Question;
import dal.asd.catme.exception.QuestionDatabaseException;

public interface IListQuestionsService
{
    public List<Question> getQuestions(String instructor) throws QuestionDatabaseException;

    public List<Question> sortByDate();

    public List<Question> sortByTitle();
}
