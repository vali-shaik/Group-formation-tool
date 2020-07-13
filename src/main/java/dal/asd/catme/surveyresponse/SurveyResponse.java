package dal.asd.catme.surveyresponse;

import dal.asd.catme.questionmanager.IQuestion;
import dal.asd.catme.questionmanager.Question;

import java.util.List;

public class SurveyResponse implements ISurveyResponse
{
    private IQuestion question;
    private List<String> answer;

    public IQuestion getQuestion()
    {
        return question;
    }

    public void setQuestion(IQuestion question)
    {
        this.question = question;
    }

    public List<String> getAnswer()
    {
        return answer;
    }

    public void setAnswer(List<String> answer)
    {
        this.answer = answer;
    }
}
