package dal.asd.catme.surveyresponse;

import java.util.List;

public interface ISurveyResponseService
{
    String isSurveyPublished(String courseId);
    List<ISurveyResponse> getSurveyQuestions(String surveyId);
    boolean saveResponses(ISurveyResponseBinder binder, String bannerId);
    boolean isSurveyAttempted(String suveyId, String bannerId);
}
