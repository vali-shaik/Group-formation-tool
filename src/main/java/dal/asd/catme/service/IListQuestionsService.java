package dal.asd.catme.service;

import dal.asd.catme.beans.Instructor;
import dal.asd.catme.beans.Question;
import dal.asd.catme.beans.QuestionTitle;
import dal.asd.catme.exception.QuestionDatabaseException;

import java.util.List;

public interface IListQuestionsService
{
    public List<QuestionTitle> getQuestions(String instructor) throws QuestionDatabaseException;

    public List<QuestionTitle> sortByDate();

    public List<QuestionTitle> sortByTitle();
}
