package dal.asd.catme.surveyresponse;

public class SurveyResponseAbstractFactoryMock implements ISurveyResponseAbstractFactory
{
    static ISurveyResponseAbstractFactory abstractFactory = null;
    ISurveyResponseDao surveyResponseDao;
    ISurveyResponseService surveyResponseService;

    public SurveyResponseAbstractFactoryMock()
    {
        surveyResponseDao = new SurveyResponseDaoMock();
        surveyResponseService = new SurveyResponseServiceImpl(surveyResponseDao);
    }

    public static ISurveyResponseAbstractFactory instance()
    {
        if(abstractFactory==null)
        {
            abstractFactory = new SurveyResponseAbstractFactoryMock();
        }
        return abstractFactory;
    }

    @Override
    public ISurveyResponseDao makeSurveyResponseDao()
    {
        return surveyResponseDao;
    }

    @Override
    public ISurveyResponseService makeSurveyResponseService()
    {
        return surveyResponseService;
    }
}
