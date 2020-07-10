package dal.asd.catme.surveyresponse;

import dal.asd.catme.questionmanager.Question;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SurveyResponseTest
{
    @Test
    void getQuestionTest()
    {
        SurveyResponse response = new SurveyResponse();

        Question q = new Question();
        response.setQuestion(q);

        assertEquals(q,response.getQuestion());
    }

    @Test
    void setQuestionTest()
    {
        SurveyResponse response = new SurveyResponse();

        Question q = new Question();
        response.setQuestion(q);

        assertEquals(q,response.getQuestion());
    }

    @Test
    void getAnswerTest()
    {
        SurveyResponse response = new SurveyResponse();

        List<String> answers = new ArrayList<>();
        response.setAnswer(answers);

        assertEquals(answers,response.getAnswer());
    }

    @Test
    void getSetAnswerTest()
    {
        SurveyResponse response = new SurveyResponse();

        List<String> answers = new ArrayList<>();
        response.setAnswer(answers);

        assertEquals(answers,response.getAnswer());
    }
}
