package dal.asd.catme.surveyresponse;

import java.util.List;

public interface ISurveyResponseService
{
    String isSurveyPublished(String courseId);

    List<SurveyResponse> getSurveyQuestions(String surveyId);

    boolean saveResponses(SurveyResponseBinder binder, String bannerId);

    boolean isSurveyAttempted(String suveyId, String bannerId);
}
