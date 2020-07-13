package dal.asd.catme.surveyresponse;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SurveyResonseBinderTest
{
    @Test
    void getSurveyIdTest()
    {
        ISurveyResponseBinder binder = new SurveyResponseBinder();

        binder.setSurveyId("ID1");
        assertEquals("ID1",binder.getSurveyId());
    }

    @Test
    void setSurveyIdTest()
    {
        ISurveyResponseBinder binder = new SurveyResponseBinder();

        binder.setSurveyId("ID1");
        assertEquals("ID1",binder.getSurveyId());
    }

    @Test
    void getCourseIdTest()
    {
        ISurveyResponseBinder binder = new SurveyResponseBinder();

        binder.setCourseId("ID1");
        assertEquals("ID1",binder.getCourseId());
    }
    @Test
    void setCourseIdTest()
    {
        ISurveyResponseBinder binder = new SurveyResponseBinder();

        binder.setCourseId("ID1");
        assertEquals("ID1",binder.getCourseId());
    }


    @Test
    void setQuestionListTest()
    {
        ISurveyResponseBinder binder = new SurveyResponseBinder();

        List<ISurveyResponse> list = new ArrayList<>();
        binder.setQuestionList(list);

        assertEquals(list,binder.getQuestionList());
    }    @Test
    void getQuestionListTest()
    {
        ISurveyResponseBinder binder = new SurveyResponseBinder();

        List<ISurveyResponse> list = new ArrayList<>();
        binder.setQuestionList(list);

        assertEquals(list,binder.getQuestionList());
    }
}
