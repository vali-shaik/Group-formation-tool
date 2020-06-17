package dal.asd.catme.dao;

import dal.asd.catme.beans.Instructor;
import dal.asd.catme.beans.Question;
import dal.asd.catme.beans.QuestionTitle;
import dal.asd.catme.exception.QuestionDatabaseException;

import java.util.ArrayList;
import java.util.List;

public class QuestionDaoMock implements IQuestionDao
{
    @Override
    public List<QuestionTitle> getQuestionTitles(String instructor) throws QuestionDatabaseException
    {
        if(instructor==null)
            throw new QuestionDatabaseException("Error Getting List of Questions");

        return getQuestions();
    }

    private List<QuestionTitle> getQuestions()
    {
        List<QuestionTitle> ret = new ArrayList<>();
        QuestionTitle title1 = new QuestionTitle("Question Title 1");

        Question q11 = new Question();
        q11.setQuestion("Question 1 of Title 1");

        Question q12 = new Question();
        q12.setQuestion("Question 1 of Title 1");

        Question q13 = new Question();
        q13.setQuestion("Question 1 of Title 1");

        List<Question> list1 = new ArrayList<>();
        list1.add(q11);
        list1.add(q12);
        list1.add(q13);

        title1.setQuestions(list1);

        ret.add(title1);


        QuestionTitle title2 = new QuestionTitle("Question Title 2");

        Question q21 = new Question();
        q21.setQuestion("Question 1 of Title 2");

        Question q22 = new Question();
        q22.setQuestion("Question 1 of Title 2");

        Question q23 = new Question();
        q23.setQuestion("Question 1 of Title 2");

        List<Question> list2 = new ArrayList<>();
        list2.add(q21);
        list2.add(q22);
        list2.add(q23);

        title2.setQuestions(list2);

        ret.add(title2);


        QuestionTitle title3 = new QuestionTitle("Question Title 3");

        Question q31 = new Question();
        q31.setQuestion("Question 1 of Title 3");

        Question q32 = new Question();
        q32.setQuestion("Question 1 of Title 3");

        Question q33 = new Question();
        q33.setQuestion("Question 1 of Title 3");

        List<Question> list3 = new ArrayList<>();
        list3.add(q31);
        list3.add(q32);
        list3.add(q33);

        title3.setQuestions(list3);

        ret.add(title3);

        return ret;
    }
}
