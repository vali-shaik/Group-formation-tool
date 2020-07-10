package dal.asd.catme.surveyresponse;

import java.util.List;

public interface ISurveyResponseDao
{
    String isSurveyPublished(String courseId);
    List<SurveyResponse> getSurveyQuestions(String surveyId);
    boolean saveResponses(SurveyResponseBinder binder, String bannerId);
    boolean saveAttempt(String surveyId, String bannerId);
    boolean isSurveyAttempted(String surveyId, String bannerId);
}
