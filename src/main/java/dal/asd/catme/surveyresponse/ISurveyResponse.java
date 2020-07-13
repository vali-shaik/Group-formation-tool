package dal.asd.catme.surveyresponse;

import dal.asd.catme.questionmanager.IQuestion;
import dal.asd.catme.questionmanager.Question;

import java.util.List;

public interface ISurveyResponse
{
    public IQuestion getQuestion();
    public void setQuestion(IQuestion question);
    public List<String> getAnswer();
    public void setAnswer(List<String> answer);
}
