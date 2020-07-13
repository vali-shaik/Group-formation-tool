package dal.asd.catme.questionmanager;

import dal.asd.catme.exception.QuestionDatabaseException;

import java.util.Collections;
import java.util.List;

public class ListQuestionsServiceImpl implements IListQuestionsService
{
    IQuestionDao questionDao;
    List<IQuestion> questions;

    public ListQuestionsServiceImpl(IQuestionDao questionDao)
    {
        this.questionDao = questionDao;
    }

    @Override
    public List<IQuestion> getQuestions(String instructor) throws QuestionDatabaseException
    {
        this.questions = questionDao.getQuestionTitles(instructor);
        return questions;
    }

    @Override
    public List<IQuestion> sortByDate()
    {
        Collections.sort(questions, (question1, question2) ->
        {
            return (question1.getCreatedDate().compareTo(question2.getCreatedDate()));
        });
        return questions;
    }

    @Override
    public List<IQuestion> sortByTitle()
    {
        Collections.sort(questions, (question1, question2) ->
        {
            return question1.getQuestionTitle().compareTo(question2.getQuestionTitle());
        });
        return questions;
    }
}
