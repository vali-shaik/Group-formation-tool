package dal.asd.catme.survey;

import java.util.ArrayList;
import java.util.List;

import dal.asd.catme.courses.Course;
import dal.asd.catme.exception.QuestionDatabaseException;
import dal.asd.catme.questionmanager.Question;

public class SurveyServiceImpl implements ISurveyService{
	
	public SurveyServiceImpl(ISurveyDao surveyDao)
	{
		this.surveyDao=surveyDao;
	}
	
	ISurveyDao surveyDao;
	
	@Override
	public Rule getSurveyQuestionRule(int questionId) throws QuestionDatabaseException 
	{
		return surveyDao.getSurveyQuestionRule(questionId);
	}

	@Override
	public Survey loadSurvey(Course course)
	{
		Survey survey=null;
		List<SurveyQuestion> surveyQuestionList=new ArrayList<>();
		SurveyQuestion surveyQuestion=null;
		if(course!=null)
		{
			try 
			{
				survey=surveyDao.getSurvey(course.getCourseId());
				List<Question> questionList=surveyDao.fetchCourseSurveyQuestions(course.getCourseId());
				for(Question question:questionList)
				{
					surveyQuestion=new SurveyQuestion();
					Rule rule=surveyDao.getSurveyQuestionRule(question.getQuestionId());
					int priority=surveyDao.getSurveyQuestionPriority(survey, question);
					surveyQuestion.setQuestion(question);
					surveyQuestion.setRule(rule);
					surveyQuestion.setPriority(priority);
					surveyQuestionList.add(surveyQuestion);
				}
				survey.setSurveyQuestions(surveyQuestionList);
				
			} catch (QuestionDatabaseException e) 
			{
				e.printStackTrace();
			}
		}
		
		return survey;
	}

	@Override
	public int addToSurvey(Survey survey, Question question) throws QuestionDatabaseException {
		return surveyDao.addToSurvey(survey, question);
	}

	@Override
	public int saveSurvey(Survey survey) throws QuestionDatabaseException {
		return surveyDao.saveSurvey(survey);
	}

	@Override
	public int deleteSurveyQuestion(Survey survey, Question question) throws QuestionDatabaseException {
		return surveyDao.deleteSurveyQuestion(survey, question);
	}

	@Override
	public boolean isSurveyPublished(Course course) throws QuestionDatabaseException {
		return surveyDao.isSurveyPublished(course);
	}

	@Override
	public int publishSurvey(Survey survey) throws QuestionDatabaseException {
		return surveyDao.publishSurvey(survey);
	}

	@Override
	public Survey getSurvey(String courseId) throws QuestionDatabaseException {
		return surveyDao.getSurvey(courseId);
	}

	@Override
	public List<Question> fetchCourseSurveyQuestions(String courseId) throws QuestionDatabaseException {
		return surveyDao.fetchCourseSurveyQuestions(courseId);
	}

	@Override
	public int getSurveyQuestionPriority(Survey survey, Question question) throws QuestionDatabaseException {
		return surveyDao.getSurveyQuestionPriority(survey, question);
	}


	

}
