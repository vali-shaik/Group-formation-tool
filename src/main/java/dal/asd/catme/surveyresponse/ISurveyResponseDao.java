package dal.asd.catme.surveyresponse;

import java.util.List;

public interface ISurveyResponseDao
{
    String isSurveyPublished(String courseId);
    List<ISurveyResponse> getSurveyQuestions(String surveyId);
    boolean saveResponses(ISurveyResponseBinder binder, String bannerId);
    boolean saveAttempt(String surveyId, String bannerId);
    boolean isSurveyAttempted(String surveyId, String bannerId);
}
