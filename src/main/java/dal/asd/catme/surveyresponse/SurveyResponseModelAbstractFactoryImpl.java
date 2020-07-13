package dal.asd.catme.surveyresponse;

public class SurveyResponseModelAbstractFactoryImpl implements ISurveyResponseModelAbstractFactory
{
    private static ISurveyResponseModelAbstractFactory modelAbstractFactory = null;

    public static ISurveyResponseModelAbstractFactory instance()
    {
        if(modelAbstractFactory==null)
        {
            modelAbstractFactory = new SurveyResponseModelAbstractFactoryImpl();
        }
        return modelAbstractFactory;
    }

    @Override
    public ISurveyResponse createSurveyResponse()
    {
        return new SurveyResponse();
    }

    @Override
    public ISurveyResponseBinder createSurveyResponseBinder()
    {
        return new SurveyResponseBinder();
    }
}
