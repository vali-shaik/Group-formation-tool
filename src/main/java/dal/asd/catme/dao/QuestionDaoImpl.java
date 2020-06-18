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
            stmt.setString(1, instructor);

            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                Question q = new Question();
                q.setQuestionId(rs.getInt(QUESTIONID));
                q.setQuestionTitle(rs.getString(QUESTIONTITLE));
                q.setQuestionText(rs.getString(QUESTION));
                q.setQuestionType(rs.getString(QUESTIONTYPE));
                q.setCreatedDate(rs.getDate(CREATEDDATE));

                questionList.add(q);
            }

            return questionList;
        } catch (SQLException throwables)
        {
            throw new QuestionDatabaseException("Error Getting Questions");
        } finally
        {
            try
            {
                con.close();
            } catch (SQLException | NullPointerException throwables)
            {
                throw new QuestionDatabaseException("Error Getting Questions");
            }
        }
    }

    @Override
    public int deleteQuestion(int questionId, Connection con)
    {
        // TODO Auto-generated method stub
        int questionDeleted = 0;
        try
        {
            if (0 != checkExistingQuestion(questionId, con))
            {
                PreparedStatement stmt = con.prepareStatement(DBQueriesUtil.DELETE_QUESTION_QUERY);
                stmt.setInt(1, questionId);
                stmt.executeUpdate();
                questionDeleted = 1;
            }
        } catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return questionDeleted;
    }

    @Override
    public int checkExistingQuestion(int questionId, Connection con)
    {
        // TODO Auto-generated method stub
        int rowCount = 0;
        try
        {
            PreparedStatement stmt = con.prepareStatement(DBQueriesUtil.CHECK_EXISTING_QUESTION_QUERY);
            stmt.setInt(1, questionId);
            ResultSet rs = stmt.executeQuery();

            rs.next();
            rowCount = rs.getInt(1);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return rowCount;
    }
}
