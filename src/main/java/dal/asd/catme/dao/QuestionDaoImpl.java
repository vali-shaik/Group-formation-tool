package dal.asd.catme.dao;

import dal.asd.catme.beans.Instructor;
import dal.asd.catme.beans.Question;
import dal.asd.catme.beans.QuestionTitle;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.exception.QuestionDatabaseException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements IQuestionDao
{
    public QuestionDaoImpl()
    {
    }

    @Override
    public List<QuestionTitle> getQuestionTitles(String instructor) throws QuestionDatabaseException
    {
        if(instructor==null)
        {
            throw new QuestionDatabaseException("Error Reading data from database");
        }
        return getQuestions();
    }

    private List<QuestionTitle> getQuestions()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        List<QuestionTitle> ret = new ArrayList<>();
        try
        {
            QuestionTitle title3 = new QuestionTitle("Question Title 3");

            Question q31 = new Question();
            q31.setQuestion("Question 1 of Title 3");
            q31.setCreatedDate(dateFormat.parse("25-10-2020"));

            Question q32 = new Question();
            q32.setQuestion("Question 2 of Title 3");
            q32.setCreatedDate(dateFormat.parse("27-10-2020"));

            Question q33 = new Question();
            q33.setQuestion("Question 3 of Title 3");
            q33.setCreatedDate(dateFormat.parse("29-10-2020"));

            List<Question> list3 = new ArrayList<>();
            list3.add(q31);
            list3.add(q32);
            list3.add(q33);

            title3.setQuestions(list3);

            ret.add(title3);


            QuestionTitle title1 = new QuestionTitle("Question Title 1");

            Question q11 = new Question();
            q11.setQuestion("Question 1 of Title 1");

            q11.setCreatedDate(dateFormat.parse("22-10-2020"));


            Question q12 = new Question();
            q12.setQuestion("Question 2 of Title 1");
            q12.setCreatedDate(dateFormat.parse("23-10-2020"));

            Question q13 = new Question();
            q13.setQuestion("Question 3 of Title 1");
            q13.setCreatedDate(dateFormat.parse("21-10-2020"));

            List<Question> list1 = new ArrayList<>();
            list1.add(q11);
            list1.add(q12);
            list1.add(q13);

            title1.setQuestions(list1);

            ret.add(title1);


            QuestionTitle title2 = new QuestionTitle("Question Title 2");

            Question q21 = new Question();
            q21.setQuestion("Question 1 of Title 2");
            q21.setCreatedDate(dateFormat.parse("18-10-2020"));

            Question q22 = new Question();
            q22.setQuestion("Question 2 of Title 2");
            q22.setCreatedDate(dateFormat.parse("17-10-2020"));

            Question q23 = new Question();
            q23.setQuestion("Question 3 of Title 2");
            q23.setCreatedDate(dateFormat.parse("16-10-2020"));

            List<Question> list2 = new ArrayList<>();
            list2.add(q21);
            list2.add(q22);
            list2.add(q23);

            title2.setQuestions(list2);

            ret.add(title2);



        } catch (ParseException e)
        {
            e.printStackTrace();
        }

        return ret;
    }
}
