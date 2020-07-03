package dal.asd.catme.questionmanager;

import dal.asd.catme.exception.QuestionDatabaseException;

import java.util.List;

public interface IListQuestionsService
{
    public List<Question> getQuestions(String instructor) throws QuestionDatabaseException;

    public List<Question> sortByDate();

    public List<Question> sortByTitle();
}
