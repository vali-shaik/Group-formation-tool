package dal.asd.catme.surveyresponse;

import java.util.List;

public interface ISurveyResponseBinder
{
    String getSurveyId();
    void setSurveyId(String surveyId);
    String getCourseId();
    void setCourseId(String courseId);
    List<ISurveyResponse> getQuestionList();
    void setQuestionList(List<ISurveyResponse> questionList);
}
