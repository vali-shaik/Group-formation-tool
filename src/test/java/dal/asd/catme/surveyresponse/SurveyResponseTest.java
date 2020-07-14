package dal.asd.catme.surveyresponse;

import dal.asd.catme.BaseAbstractFactoryMock;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.questionmanager.IQuestion;
import dal.asd.catme.questionmanager.IQuestionManagerModelAbstractFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SurveyResponseTest
{
    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryMock.instance();
    IQuestionManagerModelAbstractFactory questionManagerModelAbstractFactory = baseAbstractFactory.makeQuestionManagerModelAbstractFactory();
    ISurveyResponseModelAbstractFactory modelAbstractFactory = baseAbstractFactory.makeSurveyResponseModelAbstractFactory();

    @Test
    void getQuestionTest()
    {
        ISurveyResponse surveyResponse = modelAbstractFactory.makeSurveyResponse();

        IQuestion q = questionManagerModelAbstractFactory.makeQuestion();
        surveyResponse.setQuestion(q);

        assertEquals(q, surveyResponse.getQuestion());
    }

    @Test
    void setQuestionTest()
    {
        ISurveyResponse surveyResponse = modelAbstractFactory.makeSurveyResponse();

        IQuestion q = questionManagerModelAbstractFactory.makeQuestion();
        surveyResponse.setQuestion(q);

        assertEquals(q, surveyResponse.getQuestion());
    }

    @Test
    void getAnswerTest()
    {
        ISurveyResponse surveyResponse = modelAbstractFactory.makeSurveyResponse();

        List<String> answers = new ArrayList<>();
        surveyResponse.setAnswer(answers);

        assertEquals(answers, surveyResponse.getAnswer());
    }

    @Test
    void getSetAnswerTest()
    {
        ISurveyResponse surveyResponse = modelAbstractFactory.makeSurveyResponse();

        List<String> answers = new ArrayList<>();
        surveyResponse.setAnswer(answers);

        assertEquals(answers, surveyResponse.getAnswer());
    }
}
