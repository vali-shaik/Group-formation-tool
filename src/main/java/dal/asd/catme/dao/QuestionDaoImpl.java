package dal.asd.catme.dao;

import dal.asd.catme.beans.Question;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.exception.CatmeException;
import dal.asd.catme.exception.QuestionDatabaseException;
import dal.asd.catme.util.DBQueriesUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements IQuestionDao
{
    DatabaseAccess db;
    Connection con;
    public QuestionDaoImpl()
    {
    }

    @Override
    public List<Question> getQuestionTitles(String instructor) throws QuestionDatabaseException
    {
        //Column Order
        int QUESTIONID = 1;
        int QUESTIONTITLE = 2;
        int QUESTION = 3;
        int QUESTIONTYPE = 4;
        int CREATEDDATE = 5;


        List<Question> questionList = new ArrayList<>();
        db = SystemConfig.instance().getDatabaseAccess();
        try
        {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(DBQueriesUtil.GET_QUESTIONS);
            stmt.setString(1,instructor);

            ResultSet rs = stmt.executeQuery();

            while(rs.next())
            {
                Question q = new Question();
                q.setQuestionId(rs.getInt(QUESTIONID));
                q.setQuestionTitle(rs.getString(QUESTIONTITLE));
                q.setQuestion(rs.getString(QUESTION));
                q.setQuestionType(rs.getString(QUESTIONTYPE));
                q.setCreatedDate(rs.getDate(CREATEDDATE));

                questionList.add(q);
            }

            return questionList;
        }
        catch (SQLException throwables)
        {
                throw new QuestionDatabaseException("Error Getting Questions");
        }
        finally
        {
            try
            {
                con.close();
            }
            catch (SQLException|NullPointerException throwables)
            {
                throw new QuestionDatabaseException("Error Getting Questions");
            }
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
            q31.setQuestion("Question 1 of Title 3");
            q31.setCreatedDate(dateFormat.parse("25-10-2020"));

            Question q32 = new Question();
            q32.setQuestionTitle("Title 3");
            q32.setQuestion("Question 2 of Title 3");
            q32.setCreatedDate(dateFormat.parse("27-10-2020"));

            Question q33 = new Question();
            q33.setQuestionTitle("Title 3");
            q33.setQuestion("Question 3 of Title 3");
            q33.setCreatedDate(dateFormat.parse("29-10-2020"));

            ret.add(q31);
            ret.add(q32);
            ret.add(q33);




            Question q11 = new Question();
            q11.setQuestionTitle("Title 1");
            q11.setQuestion("Question 1 of Title 1");

            q11.setCreatedDate(dateFormat.parse("22-10-2020"));


            Question q12 = new Question();
            q12.setQuestionTitle("Title 1");
            q12.setQuestion("Question 2 of Title 1");
            q12.setCreatedDate(dateFormat.parse("23-10-2020"));

            Question q13 = new Question();
            q13.setQuestionTitle("Title 1");
            q13.setQuestion("Question 3 of Title 1");
            q13.setCreatedDate(dateFormat.parse("21-10-2020"));

            ret.add(q11);
            ret.add(q12);
            ret.add(q13);




            Question q21 = new Question();

            q21.setQuestionTitle("Title 2");
            q21.setQuestion("Question 1 of Title 2");
            q21.setCreatedDate(dateFormat.parse("18-10-2020"));

            Question q22 = new Question();
            q22.setQuestionTitle("Title 2");
            q22.setQuestion("Question 2 of Title 2");
            q22.setCreatedDate(dateFormat.parse("17-10-2020"));

            Question q23 = new Question();
            q23.setQuestionTitle("Title 2");
            q23.setQuestion("Question 3 of Title 2");
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
