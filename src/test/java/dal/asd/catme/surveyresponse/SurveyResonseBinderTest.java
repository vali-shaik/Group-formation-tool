package dal.asd.catme.surveyresponse;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SurveyResonseBinderTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    ISurveyResponseModelAbstractFactory modelAbstractFactory = baseAbstractFactory.makeSurveyResponseModelAbstractFactory();

    @Test
    void getSurveyIdTest()
    {
        ISurveyResponseBinder binder = modelAbstractFactory.makeSurveyResponseBinder();

        binder.setSurveyId("ID1");
        assertEquals("ID1", binder.getSurveyId());
    }

    @Test
    void setSurveyIdTest()
    {
        ISurveyResponseBinder binder = modelAbstractFactory.makeSurveyResponseBinder();

        binder.setSurveyId("ID1");
        assertEquals("ID1", binder.getSurveyId());
    }

    @Test
    void getCourseIdTest()
    {
        ISurveyResponseBinder binder = modelAbstractFactory.makeSurveyResponseBinder();

        binder.setCourseId("ID1");
        assertEquals("ID1", binder.getCourseId());
    }

    @Test
    void setCourseIdTest()
    {
        ISurveyResponseBinder binder = modelAbstractFactory.makeSurveyResponseBinder();

        binder.setCourseId("ID1");
        assertEquals("ID1", binder.getCourseId());
    }


    @Test
    void setQuestionListTest()
    {
        ISurveyResponseBinder binder = modelAbstractFactory.makeSurveyResponseBinder();

        List<ISurveyResponse> list = new ArrayList<>();
        binder.setQuestionList(list);

        assertEquals(list, binder.getQuestionList());
    }

    @Test
    void getQuestionListTest()
    {
        ISurveyResponseBinder binder = modelAbstractFactory.makeSurveyResponseBinder();

        List<ISurveyResponse> list = new ArrayList<>();
        binder.setQuestionList(list);

        assertEquals(list, binder.getQuestionList());
    }
}
