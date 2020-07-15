package dal.asd.catme.surveytest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.courses.Course;
import dal.asd.catme.exception.QuestionDatabaseException;
import dal.asd.catme.survey.ISurveyAbstractFactory;
import dal.asd.catme.survey.ISurveyDao;
import dal.asd.catme.survey.Survey;
import dal.asd.catme.survey.SurveyServiceImpl;


public class SurveyServiceImplTest 
{
	
	IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
	ISurveyAbstractFactory surveyAbstractFactory=baseAbstractFactory.makeSurveyAbstractFactory();
	ISurveyDao surveyDaoMock=surveyAbstractFactory.makeSurveyDao();
	@Test
	public void getSurveyTest() throws QuestionDatabaseException
	{
		Survey survey=FormSurveyMock.formSurvey();
		SurveyServiceImpl surveyService=new SurveyServiceImpl(surveyDaoMock);
		 assertEquals(survey.getSurveyId(), surveyService.getSurvey("CSCI5100").getSurveyId());
	}
	@Test
	public void fetchCourseSurveyQuestionsTest() throws QuestionDatabaseException
	{
		SurveyServiceImpl surveyService=new SurveyServiceImpl(surveyDaoMock);
		 assertEquals(FormSurveyMock.formQuestionsList().get(0).getQuestionId(), surveyService.fetchCourseSurveyQuestions("CSCI5100").get(0).getQuestionId());
		 assertEquals(FormSurveyMock.formQuestionsList().get(1).getQuestionText(), surveyService.fetchCourseSurveyQuestions("CSCI5100").get(1).getQuestionText());
	}
	
	@Test
    public void getSurveyQuestionRuleTest() throws QuestionDatabaseException
    {
		SurveyServiceImpl surveyService=new SurveyServiceImpl(surveyDaoMock);
		 assertEquals(FormSurveyMock.formRule().getRuleId(), surveyService.getSurveyQuestionRule(22).getRuleId());
		 assertNotEquals(FormSurveyMock.formRule().getRuleValue(), surveyService.getSurveyQuestionRule(23).getRuleValue());
    }
	@Test
    public void addToSurveyTest() throws QuestionDatabaseException
    {
		SurveyServiceImpl surveyService=new SurveyServiceImpl(surveyDaoMock);
		assertEquals(1, surveyService.addToSurvey(FormSurveyMock.formSurvey(), FormSurveyMock.formQuestion()));
		assertEquals(0, surveyService.addToSurvey(FormSurveyMock.formSurvey(), null));
    }
	@Test
    public void saveSurveyTest() throws QuestionDatabaseException
    {
    
		SurveyServiceImpl surveyService=new SurveyServiceImpl(surveyDaoMock);
		assertEquals(1, surveyService.saveSurvey(FormSurveyMock.formSurvey()));
		Survey survey=FormSurveyMock.formSurvey();
		survey.setIsPublished(true);
		assertEquals(0, surveyService.saveSurvey(survey));
    }
	@Test
    public void deleteSurveyQuestionTest() throws QuestionDatabaseException
    {
		SurveyServiceImpl surveyService=new SurveyServiceImpl(surveyDaoMock);
		assertEquals(1, surveyService.deleteSurveyQuestion(FormSurveyMock.formSurvey(), FormSurveyMock.formQuestion()));
    }
	@Test
    public void isSurveyPublishedTest() throws QuestionDatabaseException
    {
		SurveyServiceImpl surveyService=new SurveyServiceImpl(surveyDaoMock);
		Course course=new Course();
		course.setCourseId("CSCI5100");
		assertEquals(true, surveyService.isSurveyPublished(course));
    }
	@Test
    public void publishSurveyTest() throws QuestionDatabaseException
    {
    
		SurveyServiceImpl surveyService=new SurveyServiceImpl(surveyDaoMock);
		assertEquals(1, surveyService.publishSurvey(FormSurveyMock.formSurvey()));
		
    }
	@Test
	public void getPriorityTest() throws QuestionDatabaseException
	{
		SurveyServiceImpl surveyService=new SurveyServiceImpl(surveyDaoMock);
		assertEquals(9, surveyService.getSurveyQuestionPriority(FormSurveyMock.formSurvey(), FormSurveyMock.formQuestion()));
	}
}
