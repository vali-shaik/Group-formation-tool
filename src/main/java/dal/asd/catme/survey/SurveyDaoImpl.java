package dal.asd.catme.survey;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.courses.Course;
import dal.asd.catme.database.IDatabaseAbstractFactory;
import dal.asd.catme.database.IDatabaseAccess;
import dal.asd.catme.questionmanager.IQuestionManagerModelAbstractFactory;
import dal.asd.catme.questionmanager.Question;
import dal.asd.catme.util.DBQueriesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SurveyDaoImpl implements ISurveyDao
{
    private static final Logger log = LoggerFactory.getLogger(SurveyDaoImpl.class);
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryImpl.instance();
    IDatabaseAbstractFactory databaseAbstractFactory = baseAbstractFactory.makeDatabaseAbstractFactory();
    ISurveyModelAbstractFactory surveyModelAbstractFactory = baseAbstractFactory.makeSurveyModelAbstractFactory();
    IQuestionManagerModelAbstractFactory questionManagerModelAbstractFactory = baseAbstractFactory.makeQuestionManagerModelAbstractFactory();

    IDatabaseAccess database;

    @Override
    public List<Question> fetchCourseSurveyQuestions(String courseId) throws SurveyException
    {
        List<Question> questionList = new ArrayList<>();
        database = databaseAbstractFactory.makeDatabaseAccess();
        Connection connection = null;
        if (courseId == null || courseId.length() == 0)
        {
            log.error("Course Id is null ");
            throw new SurveyException("Unable to find the course");
        } else
        {
            PreparedStatement statement = null;
            ResultSet rs = null;
            try
            {
                log.info("Opening connection to DB");
                connection = database.getConnection();
                log.info("Executing the Query");
                statement = connection.prepareStatement(DBQueriesUtil.FETCH_SURVEY_QUESTIONS);
                statement.setString(1, courseId);
                rs = statement.executeQuery();
                while (rs.next())
                {
                    log.info("Fetched Survey quesitons from DB ");
                    Question question = questionManagerModelAbstractFactory.makeQuestion();
                    question.setQuestionId(rs.getInt("QuestionId"));
                    question.setQuestionText(rs.getString("QuestionText"));
                    question.setQuestionType(rs.getString("QuestionType"));
                    question.setCreatedDate(rs.getDate("CreatedDate"));
                    questionList.add(question);
                }
                return questionList;
            } catch (SQLException throwables)
            {
                log.error("Error Getting Questions for this Course");
                throw new SurveyException("Error Getting Questions for this Course");
            } finally
            {
                try
                {
                    if (connection == null)
                    {
                        log.info("No connetionsfound");
                    } else
                    {
                        log.warn("Closing DB connections");
                        connection.close();
                    }
                    if (statement == null)
                    {
                        log.info("No open Statements found");
                    } else
                    {
                        log.warn("Closing DB Statements");
                        statement.close();
                    }
                    if (rs == null)
                    {
                        log.info("No open Resultset found");
                    } else
                    {
                        log.warn("Closing DB Result set");
                        rs.close();
                    }

                } catch (SQLException | NullPointerException throwables)
                {
                    log.error("Error closing DB connections");
                    throw new SurveyException("Error while closing DB connection");
                }
            }
        }
    }

    @Override
    public Rule getSurveyQuestionRule(int questionId) throws SurveyException
    {
        Rule rule = null;
        database = databaseAbstractFactory.makeDatabaseAccess();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try
        {
            log.info("Opening connection to DB");
            connection = database.getConnection();
            statement = connection.prepareStatement(DBQueriesUtil.GET_SURVEY_QUESTION_RULE);
            statement.setInt(1, questionId);
            rs = statement.executeQuery();
            while (rs.next())
            {
                log.info("Fetched Rule for the question");
                rule = surveyModelAbstractFactory.makeRule();
                rule.setRuleId(rs.getInt("SurveyQuestionRuleId"));
                rule.setRuleType(rs.getString("RuleType"));
                rule.setRuleValue(rs.getString("RuleCriteriaValue"));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if (connection == null)
                {
                    log.info("No connetionsfound");
                } else
                {
                    log.warn("Closing DB connections");
                    connection.close();
                }
                if (statement == null)
                {
                    log.info("No open Statements found");
                } else
                {
                    log.warn("Closing DB Statements");
                    statement.close();
                }
                if (rs == null)
                {
                    log.info("No open Resultset found");
                } else
                {
                    log.warn("Closing DB Result set");
                    rs.close();
                }

            } catch (SQLException | NullPointerException throwables)
            {
                log.error("Error closing DB connections");
                throw new SurveyException("Error while closing DB connection");
            }
        }
        return rule;
    }

    @Override
    public Survey getSurvey(String courseId) throws SurveyException
    {
        Survey survey = new Survey();
        database = databaseAbstractFactory.makeDatabaseAccess();
        Connection connection = null;
        PreparedStatement statement = null;
        PreparedStatement statement2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        if (courseId == null)
        {
            log.error("Unable to find course Id");
            throw new SurveyException("Course Id is unavailable");
        } else
        {
            log.info("Fetching Survey details");
            try
            {
                log.info("Opening connection to DB");
                connection = database.getConnection();
                log.info("Executing the query to fetch survey details");
                statement = connection.prepareStatement(DBQueriesUtil.GET_SURVEY_BY_COURSE);
                statement.setString(1, courseId);
                rs = statement.executeQuery();
                if (rs.next())
                {
                    log.info("Fetched the survey details");
                    survey = surveyModelAbstractFactory.makeSurvey();
                    survey.setSurveyId(rs.getInt("SurveyId"));
                    survey.setGroupSize(rs.getInt("GroupSize"));
                    survey.setSurveyName(rs.getString("SurveyName"));
                } else
                {
                    statement2 = connection.prepareStatement(DBQueriesUtil.CREATE_SURVEY);
                    statement2.setString(1, courseId);
                    rs2 = statement2.executeQuery();
                    while (rs2.next())
                    {
                        survey.setSurveyId(rs.getInt(1));
                    }
                }
            } catch (SQLException e)
            {
                e.printStackTrace();
            } finally
            {
                try
                {
                    if (connection == null)
                    {
                        log.info("No connetionsfound");
                    } else
                    {
                        log.warn("Closing DB connections");
                        connection.close();
                    }
                    if (statement == null)
                    {
                        log.info("No open Statements found");
                    } else
                    {
                        log.warn("Closing DB Statements");
                        statement.close();
                    }
                    if (rs == null)
                    {
                        log.info("No open Resultset found");
                    } else
                    {
                        log.warn("Closing DB Result set");
                        rs.close();
                    }
                    if (statement2 == null)
                    {
                        log.info("No open Statements found");
                    } else
                    {
                        log.warn("Closing DB Statements");
                        statement2.close();
                    }
                    if (rs2 == null)
                    {
                        log.info("No open Resultset found");
                    } else
                    {
                        log.warn("Closing DB Result set");
                        rs2.close();
                    }

                } catch (SQLException | NullPointerException throwables)
                {
                    log.error("Error closing DB connections");
                    throw new SurveyException("Error while closing DB connection");
                }
            }
        }
        return survey;
    }

    @Override
    public int addToSurvey(Survey survey, Question question) throws SurveyException
    {

        database = databaseAbstractFactory.makeDatabaseAccess();
        Connection connection = null;
        int rowsAdded = 0;
        PreparedStatement statement = null;
        if (survey == null || question == null)
        {
            log.error("Unable to find survey or question details");
            throw new SurveyException("Survey/Question Id is unavailable");
        } else
        {
            try
            {
                log.info("Opening connection to DB");
                connection = database.getConnection();
                log.info("Executing the sql query");
                statement = connection.prepareStatement(DBQueriesUtil.ADD_QUESTION_TO_SURVEY);
                statement.setInt(1, survey.getSurveyId());
                statement.setInt(2, question.getQuestionId());
                statement.setInt(3, DBQueriesUtil.DEFAULT_SURVEY_QUESTION_RULE);
                statement.setInt(4, DBQueriesUtil.DEFAULT_PRIORITY);
                rowsAdded = statement.executeUpdate();
            } catch (SQLException e)
            {
                e.printStackTrace();
            } finally
            {
                try
                {
                    if (connection == null)
                    {
                        log.info("No connetionsfound");
                    } else
                    {
                        log.warn("Closing DB connections");
                        connection.close();
                    }
                    if (statement == null)
                    {
                        log.info("No open Statements found");
                    } else
                    {
                        log.warn("Closing DB Statements");
                        statement.close();
                    }
                } catch (SQLException | NullPointerException throwables)
                {
                    log.error("Error closing DB connections");
                    throw new SurveyException("Error while closing DB connection");
                }
            }
        }
        return rowsAdded;
    }

    @Override
    public int deleteSurveyQuestion(Survey survey, Question question) throws SurveyException
    {

        database = databaseAbstractFactory.makeDatabaseAccess();
        Connection connection = null;
        int rowsDeleted = 0;
        PreparedStatement statement = null;
        if (survey == null || question == null)
        {
            log.error("Unable to find survey or question details for deleting question from survey");
            throw new SurveyException("Survey/Question Id is unavailable for deleting question");
        } else
        {
            try
            {
                log.info("Opening connection to DB");
                connection = database.getConnection();
                log.info("Executing SQL query for deleting survey question");
                statement = connection.prepareStatement(DBQueriesUtil.DELETE_SURVEY_QUESTION);
                statement.setInt(1, survey.getSurveyId());
                statement.setInt(2, question.getQuestionId());
                rowsDeleted = statement.executeUpdate();
            } catch (SQLException e)
            {
                e.printStackTrace();
            } finally
            {
                try
                {
                    if (connection == null)
                    {
                        log.info("No connetionsfound");
                    } else
                    {
                        log.warn("Closing DB connections");
                        connection.close();
                    }
                    if (statement == null)
                    {
                        log.info("No open Statements found");
                    } else
                    {
                        log.warn("Closing DB Statements");
                        statement.close();
                    }
                } catch (SQLException | NullPointerException throwables)
                {
                    log.error("Error closing DB connections");
                    throw new SurveyException("Error while closing DB connection");
                }
            }
        }
        return rowsDeleted;
    }

    @Override
    public int saveSurvey(Survey survey) throws SurveyException
    {
        database = databaseAbstractFactory.makeDatabaseAccess();
        Connection connection = null;
        int rowsAdded = 0;
        PreparedStatement statement = null;
        PreparedStatement statement2 = null;
        PreparedStatement statement3 = null;
        PreparedStatement statement4 = null;
        ResultSet rs = null;
        if (survey == null)
        {
            log.error("Survey details are unavalable");
            throw new SurveyException("Error saving survey questions");
        } else
        {
            try
            {
                log.info("Saving Survey details");
                log.info("Opening connection to DB");
                connection = database.getConnection();
                log.info("Executing sql query");
                statement = connection.prepareStatement(DBQueriesUtil.DELETE_ALL_SURVEY_QUESTIONS);
                statement.setInt(1, survey.getSurveyId());
                statement.executeUpdate();
                if (survey.getSurveyQuestions() == null && survey.getSurveyQuestions().size() == 0)
                {
                    log.warn("No survey Survey Question to save");
                } else
                {
                    for (SurveyQuestion surveyQuestion : survey.getSurveyQuestions())
                    {
                        log.info("Adding rule to each question");
                        statement3 = connection.prepareStatement(DBQueriesUtil.ADD_RULE_SURVEY_QUESTION);
                        statement3.setString(1, surveyQuestion.getRule().getRuleType().trim());
                        statement3.setString(2, surveyQuestion.getRule().getRuleValue());
                        rs = statement3.executeQuery();

                        while (rs.next())
                        {
                            int ruleId = rs.getInt(1);
                            surveyQuestion.getRule().setRuleId(ruleId);
                        }
                        statement3.close();
                        log.info("Adding question to surveys");
                        statement2 = connection.prepareStatement(DBQueriesUtil.ADD_QUESTION_TO_SURVEY);
                        statement2.setInt(1, survey.getSurveyId());
                        statement2.setInt(2, surveyQuestion.getQuestion().getQuestionId());
                        statement2.setInt(3, surveyQuestion.getRule().getRuleId());
                        statement2.setInt(4, surveyQuestion.getPriority());
                        rowsAdded = rowsAdded + statement2.executeUpdate();
                        statement2.close();
                    }
                    log.info("Updating group size of a survey");
                    statement4 = connection.prepareStatement(DBQueriesUtil.UPDATE_SURVEY_GROUPSIZE);
                    statement4.setInt(1, survey.getSurveyId());
                    statement4.setInt(2, survey.getGroupSize());
                    statement4.executeUpdate();
                    statement4.close();
                }
            } catch (SQLException | NullPointerException e)
            {
                e.printStackTrace();
            } finally
            {
                try
                {
                    if (connection == null)
                    {
                        log.info("No connetionsfound");
                    } else
                    {
                        log.warn("Closing DB connections");
                        connection.close();
                    }
                    if (statement == null)
                    {
                        log.info("No open Statements found");
                    } else
                    {
                        log.warn("Closing DB Statements");
                        statement.close();
                    }
                    if (rs == null)
                    {
                        log.info("No open Resultset found");
                    } else
                    {
                        log.warn("Closing DB Result set");
                        rs.close();
                    }

                } catch (SQLException | NullPointerException throwables)
                {
                    log.error("Error closing DB connections");
                    throw new SurveyException("Error while closing DB connection");
                }
            }
        }
        return rowsAdded;
    }

    @Override
    public boolean isSurveyPublished(Course course) throws SurveyException
    {
        database = databaseAbstractFactory.makeDatabaseAccess();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        if (course == null)
        {
            log.error("Course details are unavailable");
            throw new SurveyException("Unable to find Course");
        } else
        {
            try
            {
                log.info("Opening connection to DB");
                connection = database.getConnection();
                statement = connection.prepareStatement(DBQueriesUtil.IS_PUBLISHED);
                statement.setString(1, course.getCourseId());
                rs = statement.executeQuery();
                while (rs.next())
                {
                    log.info("Survey is published");
                    return true;
                }
            } catch (SQLException e)
            {
                e.printStackTrace();
            } finally
            {
                try
                {
                    if (connection == null)
                    {
                        log.info("No connetionsfound");
                    } else
                    {
                        log.warn("Closing DB connections");
                        connection.close();
                    }
                    if (statement == null)
                    {
                        log.info("No open Statements found");
                    } else
                    {
                        log.warn("Closing DB Statements");
                        statement.close();
                    }
                    if (rs == null)
                    {
                        log.info("No open Resultset found");
                    } else
                    {
                        log.warn("Closing DB Result set");
                        rs.close();
                    }

                } catch (SQLException | NullPointerException throwables)
                {
                    log.error("Error closing DB connections");
                    throw new SurveyException("Error while closing DB connection");
                }
            }
        }
        return false;
    }

    @Override
    public int publishSurvey(Survey survey) throws SurveyException
    {
        database = databaseAbstractFactory.makeDatabaseAccess();
        Connection connection = null;
        int rowsAdded = 0;
        PreparedStatement statement = null;
        if (survey == null)
        {
            log.info("Survey details are unavailable");
            throw new SurveyException("Survey details not found");
        } else
        {
            try
            {
                log.info("Opening connection to DB");
                connection = database.getConnection();
                statement = connection.prepareStatement(DBQueriesUtil.PUBLISH_SURVEY);
                statement.setInt(1, survey.getSurveyId());
                rowsAdded = statement.executeUpdate();
            } catch (SQLException e)
            {
                e.printStackTrace();
            } finally
            {
                try
                {
                    if (connection == null)
                    {
                        log.info("No connetionsfound");
                    } else
                    {
                        log.warn("Closing DB connections");
                        connection.close();
                    }
                    if (statement == null)
                    {
                        log.info("No open Statements found");
                    } else
                    {
                        log.warn("Closing DB Statements");
                        statement.close();
                    }
                } catch (SQLException | NullPointerException throwables)
                {
                    log.error("Error closing DB connections");
                    throw new SurveyException("Error while closing DB connection");
                }
            }
        }
        return rowsAdded;
    }

    @Override
    public int getSurveyQuestionPriority(Survey survey, Question question) throws SurveyException
    {

        database = databaseAbstractFactory.makeDatabaseAccess();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        if (survey == null || question == null)
        {
            log.error("Unable to get survey or question details for finding priority");
            throw new SurveyException("Error while fetching priority");
        } else
        {
            try
            {
                log.info("Opening connection to DB");
                connection = database.getConnection();
                statement = connection.prepareStatement(DBQueriesUtil.GET_PRIORITY);
                statement.setInt(1, survey.getSurveyId());
                statement.setInt(2, question.getQuestionId());
                rs = statement.executeQuery();
                while (rs.next())
                {
                    log.info("Priority fetched from DB");
                    return rs.getInt("Priority");
                }

            } catch (SQLException e)
            {
                throw new SurveyException("Error in fetching Priority");
            } finally
            {
                try
                {
                    if (connection == null)
                    {
                        log.info("No connetionsfound");
                    } else
                    {
                        log.warn("Closing DB connections");
                        connection.close();
                    }
                    if (statement == null)
                    {
                        log.info("No open Statements found");
                    } else
                    {
                        log.warn("Closing DB Statements");
                        statement.close();
                    }
                    if (rs == null)
                    {
                        log.info("No open Resultset found");
                    } else
                    {
                        log.warn("Closing DB Result set");
                        rs.close();
                    }

                } catch (SQLException | NullPointerException throwables)
                {
                    log.error("Error closing DB connections");
                    throw new SurveyException("Error while closing DB connection");
                }
            }
        }
        return 0;
    }
}
