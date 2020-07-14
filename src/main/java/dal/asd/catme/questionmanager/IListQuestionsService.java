package dal.asd.catme.questionmanager;

import dal.asd.catme.exception.QuestionDatabaseException;

import java.util.List;

public interface IListQuestionsService
{
    List<IQuestion> getQuestions(String instructor) throws QuestionDatabaseException;

    List<IQuestion> sortByDate();

    List<IQuestion> sortByTitle();
}
