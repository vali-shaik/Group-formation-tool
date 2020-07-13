package dal.asd.catme.questionmanager;

import dal.asd.catme.exception.QuestionDatabaseException;

import java.util.List;

public interface IListQuestionsService
{
    public List<IQuestion> getQuestions(String instructor) throws QuestionDatabaseException;

    public List<IQuestion> sortByDate();

    public List<IQuestion> sortByTitle();
}
