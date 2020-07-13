package dal.asd.catme.surveyresponse;

import java.util.List;

public interface ISurveyResponseBinder
{
    public String getSurveyId();
    public void setSurveyId(String surveyId);
    public String getCourseId();
    public void setCourseId(String courseId);
    public List<ISurveyResponse> getQuestionList();
    public void setQuestionList(List<ISurveyResponse> questionList);
}
