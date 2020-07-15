package dal.asd.catme.survey;

import java.util.List;

import dal.asd.catme.courses.Course;
import dal.asd.catme.questionmanager.Question;

public interface ISurveyService 
{
	public Survey getSurvey(String courseId) throws SurveyException;
	public List<Question> fetchCourseSurveyQuestions(String courseId) throws SurveyException;
	public Rule getSurveyQuestionRule(int questionId) throws SurveyException;
	public Survey loadSurvey(Course course) throws SurveyException;
	public int addToSurvey(Survey survey,Question question) throws SurveyException;
	public int saveSurvey(Survey survey) throws SurveyException;
	public int deleteSurveyQuestion(Survey survey,Question question) throws SurveyException;
	public boolean isSurveyPublished(Course course) throws SurveyException;
	public int publishSurvey(Survey survey) throws SurveyException;
	public int getSurveyQuestionPriority(Survey survey,Question question) throws SurveyException;	
}
