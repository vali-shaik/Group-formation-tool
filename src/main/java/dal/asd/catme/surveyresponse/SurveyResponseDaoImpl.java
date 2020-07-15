package dal.asd.catme.surveyresponse;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.questionmanager.IQuestionManagerModelAbstractFactory;
import dal.asd.catme.questionmanager.Option;
import dal.asd.catme.questionmanager.Question;
import dal.asd.catme.util.CatmeUtil;
import dal.asd.catme.util.DBQueriesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SurveyResponseDaoImpl implements ISurveyResponseDao
{
    private static final Logger log = LoggerFactory.getLogger(SurveyResponseDaoImpl.class);

    private static final int ID = 1;
    private static final int TEXT = 2;
    private static final int TYPE = 3;
    private static final int OPTIONTEXT = 4;
    private static final int OPTIONORDER = 5;

    ISurveyResponseModelAbstractFactory modelAbstractFactory = BaseAbstractFactoryImpl.instance().makeSurveyResponseModelAbstractFactory();
    IQuestionManagerModelAbstractFactory questionManagerModelAbstractFactory = BaseAbstractFactoryImpl.instance().makeQuestionManagerModelAbstractFactory();

    public String getPublishedSurveyId(String courseId)
    {
        log.info("Checking database for publish status of Survey of course: "+courseId);
        DatabaseAccess db = SystemConfig.instance().getDatabaseAccess();
        Connection con = null;

        try
        {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(DBQueriesUtil.GET_PUBLISHED_SURVEY);
            stmt.setString(1, courseId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                return rs.getString(1);
            }
        } catch (SQLException throwables)
        {
            log.warn("Error connecting database");
            throwables.printStackTrace();
        } finally
        {
            if (con != null)
            {
                try
                {
                    con.close();
                } catch (SQLException throwables)
                {
                    log.warn("Error closing connection");
                    throwables.printStackTrace();
                }
            }
        }
        return null;
    }

    public List<SurveyResponse> getSurveyQuestions(String surveyId)
    {
        log.info("Getting survey questions from database of surveyId: "+surveyId);
        DatabaseAccess db = SystemConfig.instance().getDatabaseAccess();
        Connection con = null;
        List<SurveyResponse> questions = null;

        try
        {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(DBQueriesUtil.GET_SURVEY_QUESTIONS);
            stmt.setString(1, surveyId);

            ResultSet rs = stmt.executeQuery();

            questions = createQuestionsList(rs);

        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            if (con != null)
            {
                try
                {
                    con.close();
                } catch (SQLException e)
                {
                    log.warn("Error closing connection");
                    e.printStackTrace();
                }
            }
        }

        return questions;

    }

    public boolean saveResponses(SurveyResponseBinder binder, String bannerId)
    {
        log.info("Saving survey responses of student: "+bannerId);
        DatabaseAccess db = SystemConfig.instance().getDatabaseAccess();
        Connection con = null;

        try
        {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(DBQueriesUtil.SAVE_ANSWER);
            stmt.setString(2, bannerId);

            for (SurveyResponse question : binder.getQuestionList())
            {
                stmt.setInt(1, question.getQuestion().getQuestionId());

                if (question.getQuestion().getQuestionType().equals(CatmeUtil.CHECKBOX))
                {
                    for (String ans : question.getAnswer())
                    {
                        stmt.setString(3, ans);
                        stmt.executeUpdate();
                    }
                } else
                {
                    stmt.setString(3, question.getAnswer().get(0));
                    stmt.executeUpdate();
                }
            }
            return true;

        } catch (SQLException throwables)
        {
            log.warn("Error saving responses");
            throwables.printStackTrace();
        } finally
        {
            if (con != null)
            {
                try
                {
                    con.close();
                } catch (SQLException throwables)
                {
                    log.warn("Error closing connection");
                    throwables.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean saveAttempt(String surveyId, String bannerId)
    {
        log.info("Marking survey as attempted: \nSurvey: "+surveyId+"/tStudent: "+bannerId);
        DatabaseAccess db = SystemConfig.instance().getDatabaseAccess();
        Connection con = null;

        try
        {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(DBQueriesUtil.SAVE_ATTEMPT);
            stmt.setInt(1, Integer.parseInt(surveyId));
            stmt.setString(2, bannerId);

            stmt.executeUpdate();

            return true;

        } catch (SQLException throwables)
        {
            log.warn("Error connecting database");
            throwables.printStackTrace();
        } finally
        {
            if (con != null)
            {
                try
                {
                    con.close();
                } catch (SQLException throwables)
                {
                    log.warn("Error closing connection");
                    throwables.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean isSurveyAttempted(String surveyId, String bannerId)
    {
        log.info("Checking if student "+bannerId+" has already attempted survey "+surveyId);
        DatabaseAccess db = SystemConfig.instance().getDatabaseAccess();
        Connection con = null;

        try
        {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(DBQueriesUtil.GET_ATTEMPT);
            stmt.setInt(1, Integer.parseInt(surveyId));
            stmt.setString(2, bannerId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                return true;
            }
        } catch (SQLException throwables)
        {
            log.warn("Error connecting database");
            throwables.printStackTrace();
        } finally
        {
            if (con != null)
            {
                try
                {
                    con.close();
                } catch (SQLException throwables)
                {
                    log.warn("Error closing connection");
                    throwables.printStackTrace();
                }
            }
        }
        return false;
    }

    private List<SurveyResponse> createQuestionsList(ResultSet rs)
    {
        log.info("Converting database response to List of Survey Questions");
        List<SurveyResponse> questions = new ArrayList<>();

        try
        {
            int prevId = -1;
            SurveyResponse q = modelAbstractFactory.makeSurveyResponse();
            while (rs.next())
            {
                if (rs.getInt(ID) != prevId)
                {
                    q = modelAbstractFactory.makeSurveyResponse();
                    q.setQuestion(new Question());
                    q.getQuestion().setQuestionId(rs.getInt(ID));
                    q.getQuestion().setQuestionText(rs.getString(TEXT));
                    q.getQuestion().setQuestionType(rs.getString(TYPE));

                    questions.add(q);
                }
                prevId = rs.getInt(ID);

                if (q.getQuestion().getQuestionType().equals(CatmeUtil.RADIOBUTTON) || q.getQuestion().getQuestionType().equals(CatmeUtil.CHECKBOX))
                {
                    Option option = questionManagerModelAbstractFactory.makeOption();
                    option.setDisplayText(rs.getString(OPTIONTEXT));
                    option.setStoredAs(rs.getInt(OPTIONORDER));
                    q.getQuestion().addOption(option);
                }
            }
        } catch (SQLException throwables)
        {
            log.warn("Error connecting database");
            throwables.printStackTrace();
        }

        return questions;
    }
}
