package dal.asd.catme.service;

import dal.asd.catme.beans.Instructor;
import dal.asd.catme.beans.QuestionTitle;
import dal.asd.catme.dao.IQuestionDao;
import dal.asd.catme.exception.QuestionDatabaseException;

import java.util.List;

public class ListQuestionsServiceImpl implements IListQuestionsService
{
    IQuestionDao questionDao;

    public ListQuestionsServiceImpl(IQuestionDao questionDao)
    {
        this.questionDao = questionDao;
    }

    @Override
    public List<QuestionTitle> getQuestions(String instructor) throws QuestionDatabaseException
    {
        return questionDao.getQuestionTitles(instructor);
    }
}
