package dal.asd.catme.surveyresponse;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SurveyResponseServiceImplTest
{
    @Test
    public void isSurveyPublishedTest()
    {
        ISurveyResponseService surveyResponseService = SurveyResponseAbstractFactoryMock.instance().getSurveyResponseService();

        assertNotNull(surveyResponseService.isSurveyPublished("5308"));
        assertNull(surveyResponseService.isSurveyPublished(""));
    }

    @Test
    public void getSurveyQuestionsTest()
    {
        ISurveyResponseService surveyResponseService = SurveyResponseAbstractFactoryMock.instance().getSurveyResponseService();

        assertNotNull(surveyResponseService.getSurveyQuestions("5308"));
        assertNull(surveyResponseService.getSurveyQuestions(""));
    }

    @Test
    public void saveResponsesTest()
    {
        ISurveyResponseService surveyResponseService = SurveyResponseAbstractFactoryMock.instance().getSurveyResponseService();

        ISurveyResponseBinder binder = new SurveyResponseBinder();
        List<ISurveyResponse> questions = new ArrayList<>();

        questions.add(new SurveyResponse());
        binder.setQuestionList(questions);

        assertFalse(surveyResponseService.saveResponses(binder,"B00121212"));
        binder.setSurveyId("1");

        assertTrue(surveyResponseService.saveResponses(binder,"B00121212"));
        assertFalse(surveyResponseService.saveResponses(binder,""));
    }

    @Test
    public void isSurveyAttemptedTest()
    {
        ISurveyResponseService surveyResponseService = SurveyResponseAbstractFactoryMock.instance().getSurveyResponseService();

        assertTrue(surveyResponseService.isSurveyAttempted("5100","B00121212"));
    }
}
