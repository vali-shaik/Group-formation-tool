package dal.asd.catme.surveyresponse;

import java.util.List;

public class SurveyResponseBinder implements ISurveyResponseBinder
{
    private String surveyId;
    private String courseId;
    private List<ISurveyResponse> questionList;

    public String getSurveyId()
    {
        return surveyId;
    }

    public void setSurveyId(String surveyId)
    {
        this.surveyId = surveyId;
    }

    public String getCourseId()
    {
        return courseId;
    }

    public void setCourseId(String courseId)
    {
        this.courseId = courseId;
    }

    public List<ISurveyResponse> getQuestionList()
    {
        return questionList;
    }

    public void setQuestionList(List<ISurveyResponse> questionList)
    {
        this.questionList = questionList;
    }
}
