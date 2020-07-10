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
        SurveyResponseBinder binder = new SurveyResponseBinder();

        binder.setSurveyId("ID1");
        assertEquals("ID1",binder.getSurveyId());
    }

    @Test
    void setSurveyIdTest()
    {
        SurveyResponseBinder binder = new SurveyResponseBinder();

        binder.setSurveyId("ID1");
        assertEquals("ID1",binder.getSurveyId());
    }

    @Test
    void getCourseIdTest()
    {
        SurveyResponseBinder binder = new SurveyResponseBinder();

        binder.setCourseId("ID1");
        assertEquals("ID1",binder.getCourseId());
    }
    @Test
    void setCourseIdTest()
    {
        SurveyResponseBinder binder = new SurveyResponseBinder();

        binder.setCourseId("ID1");
        assertEquals("ID1",binder.getCourseId());
    }


    @Test
    void setQuestionListTest()
    {
        SurveyResponseBinder binder = new SurveyResponseBinder();

        List<SurveyResponse> list = new ArrayList<>();
        binder.setQuestionList(list);

        assertEquals(list,binder.getQuestionList());
    }    @Test
    void getQuestionListTest()
    {
        SurveyResponseBinder binder = new SurveyResponseBinder();

        List<SurveyResponse> list = new ArrayList<>();
        binder.setQuestionList(list);

        assertEquals(list,binder.getQuestionList());
    }
}
