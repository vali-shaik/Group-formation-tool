package dal.asd.catme.surveyresponse;

import dal.asd.catme.questionmanager.IQuestion;
import dal.asd.catme.questionmanager.Question;

import java.util.List;

public interface ISurveyResponse
{
    IQuestion getQuestion();
    void setQuestion(IQuestion question);
    List<String> getAnswer();
    void setAnswer(List<String> answer);
}
