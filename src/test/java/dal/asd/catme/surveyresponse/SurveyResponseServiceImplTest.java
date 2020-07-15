package dal.asd.catme.surveyresponse;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SurveyResponseServiceImplTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    ISurveyResponseAbstractFactory surveyResponseAbstractFactory = baseAbstractFactory.makeSurveyResponseAbstractFactory();
    ISurveyResponseModelAbstractFactory modelAbstractFactory = baseAbstractFactory.makeSurveyResponseModelAbstractFactory();

    @Test
    public void isSurveyPublishedTest()
    {
        ISurveyResponseService surveyResponseService = surveyResponseAbstractFactory.makeSurveyResponseService();

        assertNotNull(surveyResponseService.isSurveyPublished("5308"));
        assertNull(surveyResponseService.isSurveyPublished(""));
    }

    @Test
    public void getSurveyQuestionsTest()
    {
        ISurveyResponseService surveyResponseService = surveyResponseAbstractFactory.makeSurveyResponseService();
        assertNotNull(surveyResponseService.getSurveyQuestions("5308"));
        assertNull(surveyResponseService.getSurveyQuestions(""));
    }

    @Test
    public void saveResponsesTest()
    {
        ISurveyResponseService surveyResponseService = surveyResponseAbstractFactory.makeSurveyResponseService();
        SurveyResponseBinder binder = modelAbstractFactory.makeSurveyResponseBinder();
        List<SurveyResponse> questions = new ArrayList<>();

        questions.add(modelAbstractFactory.makeSurveyResponse());
        binder.setQuestionList(questions);

        assertFalse(surveyResponseService.saveResponses(binder, "B00121212"));
        binder.setSurveyId("1");

        assertTrue(surveyResponseService.saveResponses(binder, "B00121212"));
        assertFalse(surveyResponseService.saveResponses(binder, ""));
    }

    @Test
    public void isSurveyAttemptedTest()
    {
        ISurveyResponseService surveyResponseService = surveyResponseAbstractFactory.makeSurveyResponseService();
        assertTrue(surveyResponseService.isSurveyAttempted("5100", "B00121212"));
    }
}
