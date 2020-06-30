package dal.asd.catme.dao;

import dal.asd.catme.beans.Option;
import dal.asd.catme.beans.Question;
import dal.asd.catme.beans.QuestionTitle;
import dal.asd.catme.exception.QuestionDatabaseException;
import dal.asd.catme.util.CatmeUtil;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionDaoMock implements IQuestionDao
{
    List<QuestionTitle> questions;

    public QuestionDaoMock()
    {
    }

    public QuestionDaoMock(List<QuestionTitle> questions)
    {
        this.questions = questions;
    }

    @Override
    public List<Question> getQuestionTitles(String instructor) throws QuestionDatabaseException
    {
        if (instructor == null)
            throw new QuestionDatabaseException("Error Getting List of Questions");

        return getQuestions();
    }

    @Override
    public int deleteQuestion(int questionId)
    {
        // TODO Auto-generated method stub
        Connection con = null;
        if (0 != checkExistingQuestion(questionId, con))
        {
            for (QuestionTitle qT : questions)
            {
                for (Question q : qT.getQuestions())
                {
                    if (q.getQuestionId() == questionId)
                        questions.remove(q);
                    return 1;
                }
            }
        }

        return 0;
    }

    @Override
    public int checkExistingQuestion(int questionId, Connection con)
    {
        // TODO Auto-generated method stub
        for (QuestionTitle qT : questions)
        {
            for (Question q : qT.getQuestions())
            {
                if (q.getQuestionId() == questionId)
                    return 1;
            }

        }
        return 0;
    }

    @Override
    public int createQuestion(Question question, String user)
    {
        List<Question> questions = new ArrayList<Question>();
        int result = createQuestionTitle(question.getQuestionTitle(), user);
        if (result > CatmeUtil.ZERO)
        {
            questions.add(question);
            if (questions.get(CatmeUtil.ZERO) != null)
            {
                return CatmeUtil.ONE;
            }
        }
        return CatmeUtil.ZERO;
    }

    @Override
    public int createQuestionTitle(String questionTitle, String user)
    {
        Map<String, String> questionTitleInsertion = new HashMap<String, String>();
        questionTitleInsertion.put(questionTitle, user);
        if (questionTitleInsertion.get(questionTitle) != null)
        {
            return CatmeUtil.ONE;
        } else
        {
            return CatmeUtil.ZERO;
        }
    }

    @Override
    public int createOptions(int questionId, List<Option> options)
    {
        Map<Integer, Map> optionInsertion = new HashMap<Integer, Map>();
        Map<Integer, String> optionMap = new HashMap<Integer, String>();
        for (Option option : options)
        {
            optionMap.put(option.getStoredAs(), option.getDisplayText());
        }
        optionInsertion.put(questionId, optionMap);
        if (optionInsertion.get(questionId) != null)
        {
            return CatmeUtil.ONE;
        } else
        {
            return CatmeUtil.ZERO;
        }
    }

    private List<Question> getQuestions()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        List<Question> ret = new ArrayList<>();
        try
        {

            Question q31 = new Question();
            q31.setQuestionTitle("Title 3");
            q31.setQuestionText("Question 1 of Title 3");
            q31.setCreatedDate(dateFormat.parse("25-10-2020"));

            Question q32 = new Question();
            q32.setQuestionTitle("Title 3");
            q32.setQuestionText("Question 2 of Title 3");
            q32.setCreatedDate(dateFormat.parse("27-10-2020"));

            Question q33 = new Question();
            q33.setQuestionTitle("Title 3");
            q33.setQuestionText("Question 3 of Title 3");
            q33.setCreatedDate(dateFormat.parse("29-10-2020"));

            ret.add(q31);
            ret.add(q32);
            ret.add(q33);


            Question q11 = new Question();
            q11.setQuestionTitle("Title 1");
            q11.setQuestionText("Question 1 of Title 1");

            q11.setCreatedDate(dateFormat.parse("22-10-2020"));


            Question q12 = new Question();
            q12.setQuestionTitle("Title 1");
            q12.setQuestionText("Question 2 of Title 1");
            q12.setCreatedDate(dateFormat.parse("23-10-2020"));

            Question q13 = new Question();
            q13.setQuestionTitle("Title 1");
            q13.setQuestionText("Question 3 of Title 1");
            q13.setCreatedDate(dateFormat.parse("21-10-2020"));

            ret.add(q11);
            ret.add(q12);
            ret.add(q13);


            Question q21 = new Question();

            q21.setQuestionTitle("Title 2");
            q21.setQuestionText("Question 1 of Title 2");
            q21.setCreatedDate(dateFormat.parse("18-10-2020"));

            Question q22 = new Question();
            q22.setQuestionTitle("Title 2");
            q22.setQuestionText("Question 2 of Title 2");
            q22.setCreatedDate(dateFormat.parse("17-10-2020"));

            Question q23 = new Question();
            q23.setQuestionTitle("Title 2");
            q23.setQuestionText("Question 3 of Title 2");
            q23.setCreatedDate(dateFormat.parse("16-10-2020"));

            ret.add(q21);
            ret.add(q22);
            ret.add(q23);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }


        return ret;
    }
}
