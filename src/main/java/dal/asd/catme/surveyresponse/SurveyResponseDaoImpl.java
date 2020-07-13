package dal.asd.catme.surveyresponse;

import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.questionmanager.Option;
import dal.asd.catme.questionmanager.Question;
import dal.asd.catme.util.CatmeUtil;
import dal.asd.catme.util.DBQueriesUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SurveyResponseDaoImpl implements ISurveyResponseDao
{
    private static int ID=1;
    private static int TEXT=2;
    private static int TYPE=3;
    private static int OPTIONTEXT=4;
    private static int OPTIONORDER=5;

    ISurveyResponseModelAbstractFactory modelAbstractFactory = SurveyResponseModelAbstractFactoryImpl.instance();

    public String isSurveyPublished(String courseId)
    {
        DatabaseAccess db = SystemConfig.instance().getDatabaseAccess();
        Connection con = null;

        try
        {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(DBQueriesUtil.GET_PUBLISHED_SURVEY);
            stmt.setString(1,courseId);

            ResultSet rs = stmt.executeQuery();

            if(rs.next())
            {
                return rs.getString(1);
            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        finally
        {
            if(con!=null)
            {
                try
                {
                    con.close();
                } catch (SQLException throwables)
                {
                    throwables.printStackTrace();
                }
            }
        }
        return null;
    }

    public List<ISurveyResponse> getSurveyQuestions(String surveyId)
    {
        DatabaseAccess db = SystemConfig.instance().getDatabaseAccess();
        Connection con=null;
        List<ISurveyResponse> questions = null;

        try
        {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(DBQueriesUtil.GET_SURVEY_QUESTIONS);
            stmt.setString(1,surveyId);

            ResultSet rs = stmt.executeQuery();

            questions = createQuestionsList(rs);

        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            if(con!=null)
            {
                try
                {
                    con.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }

        return questions;

    }

    public boolean saveResponses(ISurveyResponseBinder binder, String bannerId)
    {
        DatabaseAccess db = SystemConfig.instance().getDatabaseAccess();
        Connection con = null;

        try
        {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(DBQueriesUtil.SAVE_ANSWER);
            stmt.setString(2,bannerId);

            for(ISurveyResponse question: binder.getQuestionList())
            {
                stmt.setInt(1,question.getQuestion().getQuestionId());

                if(question.getQuestion().getQuestionType().equals(CatmeUtil.CHECKBOX))
                {
                    for(String ans: question.getAnswer())
                    {
                        stmt.setString(3,ans);
                        stmt.executeUpdate();
                    }
                }
                else
                {
                    stmt.setString(3,question.getAnswer().get(0));
                    stmt.executeUpdate();
                }
            }
            return true;

        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        finally
        {
            if(con!=null)
            {
                try
                {
                    con.close();
                } catch (SQLException throwables)
                {
                    throwables.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean saveAttempt(String surveyId, String bannerId)
    {
        DatabaseAccess db = SystemConfig.instance().getDatabaseAccess();
        Connection con = null;

        try
        {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(DBQueriesUtil.SAVE_ATTEMPT);
            stmt.setInt(1, Integer.parseInt(surveyId));
            stmt.setString(2,bannerId);

            stmt.executeUpdate();

            return true;

        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        finally
        {
            if(con!=null)
            {
                try
                {
                    con.close();
                } catch (SQLException throwables)
                {
                    throwables.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean isSurveyAttempted(String surveyId, String bannerId)
    {
        DatabaseAccess db = SystemConfig.instance().getDatabaseAccess();
        Connection con = null;

        try
        {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(DBQueriesUtil.GET_ATTEMPT);
            stmt.setInt(1,Integer.parseInt(surveyId));
            stmt.setString(2, bannerId);

            ResultSet rs = stmt.executeQuery();

            if(rs.next())
            {
                return true;
            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        finally
        {
            if(con!=null)
            {
                try
                {
                    con.close();
                } catch (SQLException throwables)
                {
                    throwables.printStackTrace();
                }
            }
        }
        return false;
    }

    private List<ISurveyResponse> createQuestionsList(ResultSet rs)
    {
        List<ISurveyResponse> questions = new ArrayList<>();

        try
        {
            int prevId=-1;
            ISurveyResponse q=modelAbstractFactory.createSurveyResponse();
            while(rs.next())
            {
                if(rs.getInt(ID)!=prevId)
                {
                    q=modelAbstractFactory.createSurveyResponse();
                    q.setQuestion(new Question());
                    q.getQuestion().setQuestionId(rs.getInt(ID));
                    q.getQuestion().setQuestionText(rs.getString(TEXT));
                    q.getQuestion().setQuestionType(rs.getString(TYPE));

                    questions.add(q);
                }
                prevId = rs.getInt(ID);

                if(q.getQuestion().getQuestionType().equals(CatmeUtil.RADIOBUTTON) || q.getQuestion().getQuestionType().equals(CatmeUtil.CHECKBOX))
                {
                    q.getQuestion().addOption(new Option(rs.getString(OPTIONTEXT),rs.getInt(OPTIONORDER)));
                }

            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        return questions;
    }
}
