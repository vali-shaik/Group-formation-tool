package dal.asd.catme.questionmanager;

import dal.asd.catme.exception.QuestionDatabaseException;

import java.util.Collections;
import java.util.List;

public class ListQuestionsServiceImpl implements IListQuestionsService
{
    IQuestionDao questionDao;
    List<Question> questions;

    public ListQuestionsServiceImpl(IQuestionDao questionDao)
    {
        this.questionDao = questionDao;
    }

    @Override
    public List<Question> getQuestions(String instructor) throws QuestionDatabaseException
    {
        this.questions = questionDao.getQuestionTitles(instructor);
        return questions;
    }

    @Override
    public List<Question> sortByDate()
    {
        Collections.sort(questions, (question1, question2) ->
        {
            return (question1.getCreatedDate().compareTo(question2.getCreatedDate()));
        });
        return questions;
    }

    @Override
    public List<Question> sortByTitle()
    {
        Collections.sort(questions, (question1, question2) ->
        {
            return question1.getQuestionTitle().compareTo(question2.getQuestionTitle());
        });
        return questions;
    }
}
