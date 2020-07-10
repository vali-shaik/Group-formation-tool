package dal.asd.catme.surveyresponse;

import java.util.List;

public class SurveyResponseServiceImpl implements ISurveyResponseService
{
    ISurveyResponseDao surveyDao;

    public SurveyResponseServiceImpl(ISurveyResponseDao surveyDao)
    {
        this.surveyDao = surveyDao;
    }

    public String isSurveyPublished(String courseId)
    {
        if(stringNullOrEmpty(courseId))
        {
            return null;
        }
        return surveyDao.isSurveyPublished(courseId);
    }

    public List<SurveyResponse> getSurveyQuestions(String surveyId)
    {
        if(stringNullOrEmpty(surveyId))
        {
            return null;
        }

        return surveyDao.getSurveyQuestions(surveyId);
    }

    @Override
    public boolean saveResponses(SurveyResponseBinder binder, String bannerId)
    {
        if(stringNullOrEmpty(bannerId))
            return false;
        return surveyDao.saveResponses(binder,bannerId) &&
                surveyDao.saveAttempt(binder.getSurveyId(),bannerId);
    }

    @Override
    public boolean isSurveyAttempted(String suveyId, String bannerId)
    {
        return surveyDao.isSurveyAttempted(suveyId,bannerId);
    }

    private boolean stringNullOrEmpty(String str)
    {
        return (str==null || str.equals(""));
    }
}
