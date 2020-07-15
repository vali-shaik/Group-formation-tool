package dal.asd.catme.surveytest;

import java.util.List;

import dal.asd.catme.courses.Course;
import dal.asd.catme.exception.QuestionDatabaseException;
import dal.asd.catme.questionmanager.Question;
import dal.asd.catme.survey.ISurveyDao;
import dal.asd.catme.survey.Rule;
import dal.asd.catme.survey.Survey;
import dal.asd.catme.survey.SurveyQuestion;

public class SurveyDaoMock implements ISurveyDao 
{
	List<Question> questionsList=FormSurveyMock.formQuestionsList();
	
	@Override
	public Survey getSurvey(String courseId) throws QuestionDatabaseException 
	{
		Survey survey=new Survey();
		if(courseId!=null)
		{
			if(courseId.equalsIgnoreCase("CSCI5100"))
			{
				survey.setSurveyId(7);
			}
			else if(courseId.equalsIgnoreCase("CSCI5409"))
			{
				survey.setSurveyId(9);
			}
		}
		return survey;
	}

	@Override
	public List<Question> fetchCourseSurveyQuestions(String courseId) throws QuestionDatabaseException 
	{
		if(courseId!=null)
		{
			if(courseId.equalsIgnoreCase("CSCI5100"))
			{
				return questionsList;			
			}
		}
		return null;
	}

	@Override
	public Rule getSurveyQuestionRule(int questionId) throws QuestionDatabaseException 
	{
		Rule rule=new Rule();
		if(questionId==22)
		{
			rule.setRuleId(78);
	    	rule.setRuleType("Group Similar");
	    	rule.setRuleValue("9 rating");
		}
		return rule;
	}

	@Override
	public int addToSurvey(Survey survey, Question question) throws QuestionDatabaseException 
	{
		if(survey!=null && question!=null)
		{
			SurveyQuestion surveyQuestion=new SurveyQuestion();
			surveyQuestion.setQuestion(question);
			if(survey.getSurveyQuestions().add(surveyQuestion))
			{
				return 1;
			}
		}
		return 0;
	}

	@Override
	public int saveSurvey(Survey survey) throws QuestionDatabaseException 
	{
		if(survey!=null)
		{
			if(survey.getSurveyQuestions().size()>0 && survey.getIsPublished()==false)
			{
				return 1;
			}
		}
		return 0;
	}

	@Override
	public int deleteSurveyQuestion(Survey survey, Question question) throws QuestionDatabaseException 
	{
		if(survey!=null && question!=null)
		{
			SurveyQuestion surveyQuestion=new SurveyQuestion();
			surveyQuestion.setQuestion(question);
			if(survey.getSurveyQuestions().get(0).getQuestion().getQuestionId()==question.getQuestionId())
			{
				survey.getSurveyQuestions().remove(0);
				return 1;
			}
		}
		return 0;
	}

	@Override
	public boolean isSurveyPublished(Course course) throws QuestionDatabaseException 
	{
		if(course!=null)
		{
			if(course.getCourseId().equalsIgnoreCase("CSCI5100"))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int publishSurvey(Survey survey) throws QuestionDatabaseException 
	{
		if(survey!=null)
		{
			if(survey.getSurveyQuestions().size()>0 && survey.getIsPublished()==false)
			{
				return 1;
			}
		}
		return 0;
	}

	@Override
	public int getSurveyQuestionPriority(Survey survey, Question question) throws QuestionDatabaseException 
	{
		if(survey!=null && question!=null)
		{
			if(survey.getSurveyQuestions().size()>0)
			{
				return survey.getSurveyQuestions().get(0).getPriority();
			}
		}
		return 0;
	}

}
