package dal.asd.catme.surveytest;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.courses.Course;
import dal.asd.catme.survey.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class SurveyServiceImplTest
{

    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    ISurveyAbstractFactory surveyAbstractFactory = baseAbstractFactory.makeSurveyAbstractFactory();
    ISurveyDao surveyDaoMock = surveyAbstractFactory.makeSurveyDao();

    @Test
    public void getSurveyTest() throws SurveyException
    {
        Survey survey = FormSurveyMock.formSurvey();
        SurveyServiceImpl surveyService = new SurveyServiceImpl(surveyDaoMock);
        assertEquals(survey.getSurveyId(), surveyService.getSurvey("CSCI5100").getSurveyId());
    }

    @Test
    public void fetchCourseSurveyQuestionsTest() throws SurveyException
    {
        SurveyServiceImpl surveyService = new SurveyServiceImpl(surveyDaoMock);
        assertEquals(FormSurveyMock.formQuestionsList().get(0).getQuestionId(), surveyService.fetchCourseSurveyQuestions("CSCI5100").get(0).getQuestionId());
        assertEquals(FormSurveyMock.formQuestionsList().get(1).getQuestionText(), surveyService.fetchCourseSurveyQuestions("CSCI5100").get(1).getQuestionText());
    }

    @Test
    public void getSurveyQuestionRuleTest() throws SurveyException
    {
        SurveyServiceImpl surveyService = new SurveyServiceImpl(surveyDaoMock);
        assertEquals(FormSurveyMock.formRule().getRuleId(), surveyService.getSurveyQuestionRule(22).getRuleId());
        assertNotEquals(FormSurveyMock.formRule().getRuleValue(), surveyService.getSurveyQuestionRule(23).getRuleValue());
    }

    @Test
    public void addToSurveyTest() throws SurveyException
    {
        SurveyServiceImpl surveyService = new SurveyServiceImpl(surveyDaoMock);
        assertEquals(1, surveyService.addToSurvey(FormSurveyMock.formSurvey(), FormSurveyMock.formQuestion()));
    }

    @Test
    public void saveSurveyTest() throws SurveyException
    {

        SurveyServiceImpl surveyService = new SurveyServiceImpl(surveyDaoMock);
        assertEquals(1, surveyService.saveSurvey(FormSurveyMock.formSurvey()));
        Survey survey = FormSurveyMock.formSurvey();
        survey.setIsPublished(true);
        assertEquals(0, surveyService.saveSurvey(survey));
    }

    @Test
    public void deleteSurveyQuestionTest() throws SurveyException
    {
        SurveyServiceImpl surveyService = new SurveyServiceImpl(surveyDaoMock);
        assertEquals(1, surveyService.deleteSurveyQuestion(FormSurveyMock.formSurvey(), FormSurveyMock.formQuestion()));
    }

    @Test
    public void isSurveyPublishedTest() throws SurveyException
    {
        SurveyServiceImpl surveyService = new SurveyServiceImpl(surveyDaoMock);
        Course course = new Course();
        course.setCourseId("CSCI5100");
        assertEquals(true, surveyService.isSurveyPublished(course));
    }

    @Test
    public void publishSurveyTest() throws SurveyException
    {
        SurveyServiceImpl surveyService = new SurveyServiceImpl(surveyDaoMock);
        assertEquals(1, surveyService.publishSurvey(FormSurveyMock.formSurvey()));

    }

    @Test
    public void getPriorityTest() throws SurveyException
    {
        SurveyServiceImpl surveyService = new SurveyServiceImpl(surveyDaoMock);
        assertEquals(9, surveyService.getSurveyQuestionPriority(FormSurveyMock.formSurvey(), FormSurveyMock.formQuestion()));
    }
}
