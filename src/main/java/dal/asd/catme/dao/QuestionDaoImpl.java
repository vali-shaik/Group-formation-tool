package dal.asd.catme.dao;

import dal.asd.catme.beans.Option;
import dal.asd.catme.beans.Question;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.exception.CatmeException;
import dal.asd.catme.exception.QuestionDatabaseException;
import dal.asd.catme.util.CatmeUtil;
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
        Connection con=null;
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
    public int deleteQuestion(int questionId)
    {
        // TODO Auto-generated method stub
        int questionDeleted = 0;
        db = SystemConfig.instance().getDatabaseAccess();
        Connection con = null;
        try
        {
        	con = db.getConnection();
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
        } finally
        {
        	try {
        		if (con != null){
				con.close();
        		}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

    @Override
    public int createQuestion(Question question,String user) {
        int result=0;
        db = SystemConfig.instance().getDatabaseAccess();
        Connection con = null;
        try {
            con = db.getConnection();
            int questionTitleId = createQuestionTitle(question.getQuestionTitle(), user);

            PreparedStatement preparedStatement = con.prepareStatement(DBQueriesUtil.CHECK_QUESTION);
            preparedStatement.setInt(1, questionTitleId);
            preparedStatement.setString(2,question.getQuestionText());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()) {
                preparedStatement=con.prepareStatement(DBQueriesUtil.INSERT_QUESTION);
                preparedStatement.setInt(1, questionTitleId);
                preparedStatement.setString(2, question.getQuestionText());
                preparedStatement.setString(3,getQuestionType(question.getQuestionType()));
                //preparedStatement.setDate(4, new Date(questionTitleId));
                result=preparedStatement.executeUpdate();
                if(result>0) {
                    preparedStatement = con.prepareStatement(DBQueriesUtil.CHECK_QUESTION);
                    preparedStatement.setInt(1, questionTitleId);
                    preparedStatement.setString(2,question.getQuestionText());
                    resultSet = preparedStatement.executeQuery();
                    resultSet.next();
                }
            }
            result = Integer.parseInt(resultSet.getString("QuestionId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(con!=null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public int createQuestionTitle(String questionTitle,String user) {
        int result=0;

        db = SystemConfig.instance().getDatabaseAccess();
        Connection con = null;
        try {
            con = db.getConnection();
            int userRoleId = getUserRoleId(con,user);
            PreparedStatement preparedStatement = con.prepareStatement(DBQueriesUtil.CHECK_QUESTION_TITLE);
            preparedStatement.setString(1, questionTitle);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()) {
                preparedStatement=con.prepareStatement(DBQueriesUtil.INSERT_QUESTION_TITLE);
                preparedStatement.setString(1, questionTitle);
                preparedStatement.setInt(2, userRoleId);
                result=preparedStatement.executeUpdate();

                if(result>0) {
                    preparedStatement = con.prepareStatement(DBQueriesUtil.CHECK_QUESTION_TITLE);
                    preparedStatement.setString(1, questionTitle);
                    resultSet = preparedStatement.executeQuery();
                    resultSet.next();
                }

            }
            result = Integer.parseInt(resultSet.getString("QuestionTitleId"));

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            try
            {
                con.close();
            } catch (SQLException|NullPointerException throwables)
            {
                throwables.printStackTrace();
            }
        }

        return result;

    }

    private int getUserRoleId(Connection con,String user) {
        int userRoleId = 0;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(DBQueriesUtil.SELECT_ROLE_BY_ROLENAME);
            preparedStatement.setString(CatmeUtil.ONE, CatmeUtil.INSTRUCTOR_ROLE);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                int roleId =Integer.parseInt(resultSet.getString("roleId"));
                preparedStatement = con.prepareStatement(DBQueriesUtil.SELECT_USER_ROLE_BY_BANNERID);
                preparedStatement.setString(1, user);
                preparedStatement.setInt(2,roleId);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
            }

            userRoleId= Integer.parseInt(resultSet.getString("UserRoleId"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userRoleId;
    }

    private String getQuestionType(String questionType) {
        if(questionType.equalsIgnoreCase(CatmeUtil.FREE_TEXT))
            return CatmeUtil.FREETEXT_DB;
        else if(questionType.equalsIgnoreCase(CatmeUtil.NUMERIC))
            return CatmeUtil.NUMERIC_DB;
        else if(questionType.equalsIgnoreCase(CatmeUtil.MCQ_CHOOSE_MULTIPLE))
            return CatmeUtil.CHECKBOX;
        else
            return CatmeUtil.RADIOBUTTON;
    }

    @Override
    public int createOptions(int questionId, List<Option> options) {
        int result =0;
        db = SystemConfig.instance().getDatabaseAccess();
        Connection con = null;
        for(int i=0;i<options.size();i++) {
            Option option = options.get(i);
            if(option.getDisplayText().trim().length() > 0 && option.getDisplayText()!="") {
                try {
                    con = db.getConnection();
                    PreparedStatement preparedStatement = con.prepareStatement(DBQueriesUtil.CHECK_QUESTION_OPTION);
                    preparedStatement.setInt(1, questionId);
                    preparedStatement.setString(2, option.getDisplayText());
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(!resultSet.next()) {
                        preparedStatement = con.prepareStatement(DBQueriesUtil.INSERT_QUESTION_OPTION);
                        preparedStatement.setInt(1, questionId);
                        preparedStatement.setString(2, option.getDisplayText());
                        preparedStatement.setInt(3, option.getStoredAs());
                        result=preparedStatement.executeUpdate();
                    }
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                finally
                {
                    try
                    {
                        con.close();
                    } catch (SQLException|NullPointerException throwables)
                    {
                        throwables.printStackTrace();
                    }
                }
            }

        }
        return result;
    }
}
