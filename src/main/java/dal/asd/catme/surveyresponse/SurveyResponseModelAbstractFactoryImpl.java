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
