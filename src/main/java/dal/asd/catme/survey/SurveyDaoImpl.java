package dal.asd.catme.survey;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.courses.Course;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.exception.QuestionDatabaseException;
import dal.asd.catme.questionmanager.Question;
import dal.asd.catme.util.DBQueriesUtil;

public class SurveyDaoImpl implements ISurveyDao
{
	DatabaseAccess database;
	@Override
	public List<Question> fetchCourseSurveyQuestions(String courseId) throws QuestionDatabaseException {
		List<Question> questionList = new ArrayList<>();
		database = SystemConfig.instance().getDatabaseAccess();
		Connection connection = null;
		try
		{
			connection = database.getConnection();
			PreparedStatement statement = connection.prepareStatement(DBQueriesUtil.FETCH_SURVEY_QUESTIONS);
			statement.setString(1, courseId);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
			{
				Question question = new Question();
				question.setQuestionId(rs.getInt("QuestionId"));
				question.setQuestionText(rs.getString("QuestionText"));
				question.setQuestionType(rs.getString("QuestionType"));
				question.setCreatedDate(rs.getDate("CreatedDate"));
				questionList.add(question);
			}
			return questionList;
		} catch (SQLException throwables)
		{
			throw new QuestionDatabaseException("Error Getting Questions for this Course");
		} finally
		{
			try
			{
				connection.close();
			} catch (SQLException | NullPointerException throwables)
			{
				throw new QuestionDatabaseException("Error Getting Questions");
			}
		}
	}

	@Override
	public Rule getSurveyQuestionRule(int questionId) throws QuestionDatabaseException {
		Rule rule=null;
		database = SystemConfig.instance().getDatabaseAccess();
		Connection connection = null;

		try 
		{
			connection = database.getConnection();
			PreparedStatement statement = connection.prepareStatement(DBQueriesUtil.GET_SURVEY_QUESTION_RULE);
			statement.setInt(1, questionId);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
			{
				rule=new Rule();
				rule.setRuleId(rs.getInt("SurveyQuestionRuleId"));
				rule.setRuleType(rs.getString("RuleType"));
				rule.setRuleValue(rs.getString("RuleCriteriaValue"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {

			try
			{
				connection.close();
			} catch (SQLException | NullPointerException throwables)
			{
				throw new QuestionDatabaseException("Error Getting Questions");
			}
		
		}
		return rule;
	}

	@Override
	public Survey getSurvey(String courseId) throws QuestionDatabaseException {
		Survey survey=null;
		database = SystemConfig.instance().getDatabaseAccess();
		Connection connection = null;
		try 
		{
			connection = database.getConnection();
			PreparedStatement statement = connection.prepareStatement(DBQueriesUtil.GET_SURVEY_BY_COURSE);
			System.out.println("#### LOAD SURBEY Course ID : "+courseId);
			statement.setString(1,courseId );
			ResultSet rs = statement.executeQuery();
			while (rs.next())
			{
				survey=new Survey();
				survey.setSurveyId(rs.getInt("SurveyId"));
				survey.setGroupSize(rs.getInt("GroupSize"));
				survey.setSurveyName(rs.getString("SurveyName"));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally {

			try
			{
				connection.close();
			} catch (SQLException | NullPointerException throwables)
			{
				throw new QuestionDatabaseException("Error Getting Questions");
			}
		
		}
		return survey;
	}

	@Override
	public int addToSurvey(Survey survey, Question question) throws QuestionDatabaseException
	{

		database = SystemConfig.instance().getDatabaseAccess();
		Connection connection = null;
		int rowsAdded=0;
		try {
			connection = database.getConnection();
			PreparedStatement statement = connection.prepareStatement(DBQueriesUtil.ADD_QUESTION_TO_SURVEY);
			statement.setInt(1,survey.getSurveyId());
			statement.setInt(2,question.getQuestionId());
			statement.setInt(3, DBQueriesUtil.DEFAULT_SURVEY_QUESTION_RULE);
			statement.setInt(4,DBQueriesUtil.DEFAULT_PRIORITY);
			rowsAdded = statement.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally 
		{
			try
			{
				connection.close();
			} catch (SQLException | NullPointerException throwables)
			{
				throw new QuestionDatabaseException("Error Getting Questions");
			}
		}
		return rowsAdded;
	}
	
	@Override
	public int deleteSurveyQuestion(Survey survey, Question question) throws QuestionDatabaseException 
	{

		database = SystemConfig.instance().getDatabaseAccess();
		Connection connection = null;
		int rowsDeleted=0;
		try {
			connection = database.getConnection();
			PreparedStatement statement = connection.prepareStatement(DBQueriesUtil.DELETE_SURVEY_QUESTION);
			statement.setInt(1,survey.getSurveyId());
			statement.setInt(2,question.getQuestionId());
			rowsDeleted = statement.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally 
		{
			try
			{
				connection.close();
			} catch (SQLException | NullPointerException throwables)
			{
				throw new QuestionDatabaseException("Error Getting Questions");
			}
		}
		return rowsDeleted;
	}

	@Override
	public int saveSurvey(Survey survey) throws QuestionDatabaseException 
	{
		database = SystemConfig.instance().getDatabaseAccess();
		Connection connection = null;
		int rowsAdded=0;
			try 
			{
				connection = database.getConnection();
				PreparedStatement statement = connection.prepareStatement(DBQueriesUtil.DELETE_ALL_SURVEY_QUESTIONS);
				statement.setInt(1, survey.getSurveyId());
				statement.executeUpdate();
				if(survey.getSurveyQuestions()!=null && survey.getSurveyQuestions().size()>0)
				{
					
					for(SurveyQuestion surveyQuestion:survey.getSurveyQuestions())
					{
						
						
						PreparedStatement statement3 = connection.prepareStatement(DBQueriesUtil.ADD_RULE_SURVEY_QUESTION);
						statement3.setString(1, surveyQuestion.getRule().getRuleType().trim());
						statement3.setString(2, surveyQuestion.getRule().getRuleValue());
						ResultSet rs=statement3.executeQuery();
						while(rs.next())
						{
							int ruleId=rs.getInt(1);
							surveyQuestion.getRule().setRuleId(ruleId);
						}
						
					PreparedStatement statement2 = connection.prepareStatement(DBQueriesUtil.ADD_QUESTION_TO_SURVEY);
					statement2.setInt(1,survey.getSurveyId());
					statement2.setInt(2,surveyQuestion.getQuestion().getQuestionId());
					statement2.setInt(3, surveyQuestion.getRule().getRuleId());
					statement2.setInt(4, surveyQuestion.getPriority());
					rowsAdded = rowsAdded+statement2.executeUpdate();
					}
					PreparedStatement statement3 = connection.prepareStatement(DBQueriesUtil.UPDATE_SURVEY_GROUPSIZE);
					statement3.setInt(1,survey.getSurveyId());
					statement3.setInt(2,survey.getGroupSize());
					statement3.executeUpdate();
					
					
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			finally 
			{
				try
				{
					connection.close();
				} catch (SQLException | NullPointerException throwables)
				{
					throw new QuestionDatabaseException("Error Saving Survey Questions");
				}
			}
			
		return rowsAdded;
	}

	@Override
	public boolean isSurveyPublished(Course course) throws QuestionDatabaseException 
	{
		database = SystemConfig.instance().getDatabaseAccess();
		Connection connection = null;
		try 
		{
			connection = database.getConnection();
			PreparedStatement statement = connection.prepareStatement(DBQueriesUtil.IS_PUBLISHED);
			System.out.println("#### LOAD SURBEY Course ID : "+course.getCourseId());
			statement.setString(1,course.getCourseId());
			ResultSet rs = statement.executeQuery();
			while (rs.next())
			{
				return true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally {

			try
			{
				connection.close();
			} catch (SQLException | NullPointerException throwables)
			{
				throw new QuestionDatabaseException("Error Getting Questions");
			}
		
		}
		return false;
	}

	@Override
	public int publishSurvey(Survey survey) throws QuestionDatabaseException 
	{
		database = SystemConfig.instance().getDatabaseAccess();
		Connection connection = null;
		int rowsAdded=0;
			try 
			{
				connection = database.getConnection();
				PreparedStatement statement = connection.prepareStatement(DBQueriesUtil.PUBLISH_SURVEY);
				statement.setInt(1, survey.getSurveyId());
				rowsAdded=statement.executeUpdate();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			finally 
			{
				try
				{
					connection.close();
				} catch (SQLException | NullPointerException throwables)
				{
					throw new QuestionDatabaseException("Error publishing the survey");
				}
			}
			
		return rowsAdded;
	}

	@Override
	public int getSurveyQuestionPriority(Survey survey, Question question) throws QuestionDatabaseException 
	{
		
		database = SystemConfig.instance().getDatabaseAccess();
		Connection connection = null;
		try {
			connection = database.getConnection();
			PreparedStatement statement = connection.prepareStatement(DBQueriesUtil.GET_PRIORITY);
			statement.setInt(1, survey.getSurveyId());
			statement.setInt(2,question.getQuestionId());
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
				return rs.getInt("Priority");
			}
			
		} 
		catch (SQLException e) 
		{
			throw new QuestionDatabaseException("Error in fetching Priority");
		}
		finally 
		{
			try
			{
				connection.close();
			} catch (SQLException | NullPointerException throwables)
			{
				throw new QuestionDatabaseException("Error publishing the survey");
			}
		}
		return 0;
	}
}
