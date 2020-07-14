package dal.asd.catme.surveyresponse;

public class SurveyResponseModelAbstractFactoryImpl implements ISurveyResponseModelAbstractFactory
{
    @Override
    public ISurveyResponse makeSurveyResponse()
    {
        return new SurveyResponse();
    }

    @Override
    public ISurveyResponseBinder makeSurveyResponseBinder()
    {
        return new SurveyResponseBinder();
    }
}
