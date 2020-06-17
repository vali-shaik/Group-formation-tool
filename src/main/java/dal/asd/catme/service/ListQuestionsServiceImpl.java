package dal.asd.catme.service;

import dal.asd.catme.beans.Instructor;
import dal.asd.catme.beans.Question;
import dal.asd.catme.beans.QuestionTitle;
import dal.asd.catme.dao.IQuestionDao;
import dal.asd.catme.exception.QuestionDatabaseException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListQuestionsServiceImpl implements IListQuestionsService
{
    IQuestionDao questionDao;
    List<QuestionTitle> questionTitles;

    public ListQuestionsServiceImpl(IQuestionDao questionDao)
    {
        this.questionDao = questionDao;
    }

    @Override
    public List<QuestionTitle> getQuestions(String instructor) throws QuestionDatabaseException
    {
        this.questionTitles = questionDao.getQuestionTitles(instructor);
        return questionTitles;
    }

    @Override
    public List<QuestionTitle> sortByDate()
    {
        for(QuestionTitle questionTitle: questionTitles)
        {
            Collections.sort(questionTitle.getQuestions(),(question1, question2) ->{
                return (question1.getCreatedDate().compareTo(question2.getCreatedDate()));
            } );
        }
        return questionTitles;
    }

    @Override
    public List<QuestionTitle> sortByTitle()
    {
        Collections.sort(questionTitles,(questionTitle1, questionTitle2) -> {
            return questionTitle1.getQuestionTitle().compareTo(questionTitle2.getQuestionTitle());
        } );

        return questionTitles;
    }
}
