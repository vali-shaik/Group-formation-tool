package dal.asd.catme.service;

import dal.asd.catme.beans.Instructor;
import dal.asd.catme.beans.Question;
import dal.asd.catme.beans.QuestionTitle;
import dal.asd.catme.exception.QuestionDatabaseException;

import java.util.List;

public interface IListQuestionsService
{
    public List<Question> getQuestions(String instructor) throws QuestionDatabaseException;

    public List<Question> sortByDate();

    public List<Question> sortByTitle();
}
